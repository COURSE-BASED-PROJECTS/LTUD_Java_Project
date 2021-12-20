package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.AccountCurrent;
import model.Order_History;
import model.Payment_History;
import utils.DatabaseConnect;

public class Managed_Payment extends Managed_General{
	public static DefaultTableModel showListHistory(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTHANHTOAN";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				
				for (int i = 1; i <= numberColumn; i++) {
						row.addElement(rs.getString(i));
				}
				
				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTHANHTOAN");
			e1.printStackTrace();
		}
		return tableModel;
	}
	public static DefaultTableModel showHistoryPayment(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTHANHTOAN Where CMND = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				
				for (int i = 1; i <= numberColumn; i++) {
						row.addElement(rs.getString(i));
				}
				
				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTHANHTOAN");
			e1.printStackTrace();
		}
		return tableModel;
	}
	
	public static boolean insertPayment(Payment_History paymentHistory) {
		Connection con = DatabaseConnect.openConnection();
		boolean result = false;
		String sql = "INSERT INTO LSTHANHTOAN(MAGD,CMND,DUNO,SOTIENTRA,THOIGIAN) VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, paymentHistory.getMaGD());
			stmt.setString(2, paymentHistory.getCMND());
			stmt.setDouble(3, paymentHistory.getDebit());
			stmt.setDouble(4, paymentHistory.getMinDebt());
			stmt.setTimestamp(5, paymentHistory.getTime());
			
			result = stmt.executeUpdate() > 0;
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateBalance(Double debit,Double balance,Double minDebit) {
		
		Connection con = DatabaseConnect.openConnection();
		boolean result = false;
		String sql = "UPDATE TAIKHOAN SET SODU=?, DUNO=? "
				+ " WHERE TAIKHOAN=? ";
		
		try {
			PreparedStatement stmt = null;
			
			stmt = con.prepareStatement(sql);
			
			stmt.setDouble(1, balance-minDebit);
			stmt.setDouble(2, debit-minDebit);
			stmt.setString(3, AccountCurrent.getUsernameCurrent());
			
			result = stmt.executeUpdate() > 0;
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;	
	}

	public static Double getTotalPaid() {
		Double totalPaid = 0.0d;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select SUM(SOTIENTRA) From LSTHANHTOAN";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				totalPaid = rs.getDouble(1);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return totalPaid;
	}
}

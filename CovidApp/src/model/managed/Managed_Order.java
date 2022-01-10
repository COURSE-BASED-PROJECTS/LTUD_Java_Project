package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.AccountCurrent;
import model.Admin_Payment;
import model.Order_History;
import utils.DatabaseConnect;

public class Managed_Order extends Managed_General{
	public static DefaultTableModel showListHistory(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSMUAHANG";
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSMUAHANG");
			e1.printStackTrace();
		}
		return tableModel;
	}
	
	public static TableModel showHistoryOrder(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSMUAHANG Where CMND = '" + id + "'";
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSMUAHANG");
			e1.printStackTrace();
		}
		return tableModel;
	}
	
	public static boolean insertBuyPackageHistory(Order_History orderHistory) {
		Connection con = DatabaseConnect.openConnection();
		boolean result = false;
		String sql = "INSERT INTO LSMUAHANG(MAHD,CMND,LOAIHANG,SOLUONG, SOTIEN, THOIGIAN) VALUES(?,?,?,?,?,?)";
		Long money = Managed_Package.getPrice(orderHistory.getType()).longValue()*Long.valueOf(orderHistory.getQuantity());
		
		System.out.println(money);
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, orderHistory.getMaHD());
			stmt.setString(2, orderHistory.getCMND());
			stmt.setString(3, orderHistory.getType());
			stmt.setString(4, String.valueOf(orderHistory.getQuantity()));
			stmt.setString(5, "12345678.9");
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			result = stmt.executeUpdate() > 0;
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getQuantity(String idPackage) throws SQLException {
		String result = "";
		
		Connection con = DatabaseConnect.openConnection();
		String sql = "SELECT GIOIHANSL FROM NHUYEUPHAM WHERE MANYP='"+idPackage+"'";
		ResultSet rs = DatabaseConnect.getResultSet(con, sql);
		System.out.println(rs);
		
		if(rs.next())
			result = rs.getString(1);
		rs.close();
		con.close();
		
		return result;
	}
	
	public static boolean updateQuantityPackage(String idPackage, String ogirinalQuantity,String buyQuantity) {
		
		Connection con = DatabaseConnect.openConnection();
		boolean result = false;
		String sql = "UPDATE NHUYEUPHAM SET GIOIHANSL=? "
				+ "WHERE MANYP=? ";
		
		
		try {
			ogirinalQuantity = getQuantity(idPackage);
			int leftQuantity = Integer.parseInt(ogirinalQuantity)-Integer.parseInt(buyQuantity);
			
			PreparedStatement stmt = null;
			
			if(leftQuantity >=0) {
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, String.valueOf(Integer.parseInt(ogirinalQuantity)-Integer.parseInt(buyQuantity)));
				stmt.setString(2, idPackage);
			}
			
			result = stmt.executeUpdate() > 0;
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return result;
		
	}
	
	public static boolean updateDebitAccount(String quantity, String price) {
		Connection con_Covid = DatabaseConnect.openConnection();
		Connection con_Payment = DatabaseConnect.connectDB_Payment();

		boolean result = false;
		String sql = "UPDATE TAIKHOAN SET DUNO=DUNO+? "
				+ "WHERE TAIKHOAN=? ";
		
		try {
			PreparedStatement stmtC = null;
			PreparedStatement stmtP = null;
			
			stmtC = con_Covid.prepareStatement(sql);
			stmtP = con_Payment.prepareStatement(sql);
			
			stmtC.setDouble(1, Double.valueOf(price)*Double.valueOf(quantity));
			stmtC.setString(2, AccountCurrent.getUsernameCurrent());
			
			stmtP.setDouble(1, Double.valueOf(price)*Double.valueOf(quantity));
			stmtP.setString(2, AccountCurrent.getUsernameCurrent());	
			
			result = stmtC.executeUpdate() > 0;
			stmtP.executeUpdate();
			
			stmtC.close();
			con_Covid.close();
			
			stmtP.close();
			con_Payment.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
}

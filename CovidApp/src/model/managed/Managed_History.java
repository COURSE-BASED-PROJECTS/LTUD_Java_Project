package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.core.util.SystemClock;

import model.AccountCurrent;
import utils.DatabaseConnect;

public class Managed_History {

	public static TableModel showManagerHistory(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSHOATDONGQLQL Where MATK = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			int stt = 0;
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				row.addElement("" + ++stt);
				for (int i = 3; i <= numberColumn; i++) {
					row.addElement(rs.getString(i).trim());
				}

				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSHOATDONGQL");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static TableModel showBuyPackageHistory(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSMUAHANG Where CMND = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
	
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 1; i <= numberColumn; i++) {
					if (i == 2) continue;
					row.addElement(rs.getString(i).trim());
				}

				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSMUAHANG");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static TableModel showPaymentHistory(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTHANHTOAN Where CMND = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
	
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 1; i <= numberColumn; i++) {
					if (i == 2) continue;
					row.addElement(rs.getString(i).trim());
				}

				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTHANHTOAN");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static void addManagerHistory(String action, String table, String id) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "INSERT INTO LSHOATDONGQL(MATK,HANHDONG,BANG,MADT,THOIGIAN) VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, AccountCurrent.getUsernameCurrent());
			stmt.setString(2, action);
			stmt.setString(3, table);
			stmt.setString(4, id);
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}

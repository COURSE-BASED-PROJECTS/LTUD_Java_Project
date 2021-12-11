package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Status_History;
import utils.DatabaseConnect;

public class Managed_Status {
	private HashMap<String, ArrayList<Status_History>> listStatusHistory;

	public static DefaultTableModel showListHistory(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTRANGTHAI";
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTRANGTHAI");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static TableModel showHistoryStatus(String id, DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTRANGTHAI Where CMND = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i).trim());
				}

				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTRANGTHAI");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static String getStatusFromId(String id) {
		String status = null;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select TRANGTHAI From NGUOIDUNG Where CMND ='" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);

			while (rs.next()) {
				status = rs.getString(1).trim();
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTRANGTHAI");
			e1.printStackTrace();
		}
		return status;
	}

	public static void addStatusHistory(String id, String oldStatus, String newStatus) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdfDate.format(new Date());

		Connection con = DatabaseConnect.openConnection();
		String sql = "INSERT INTO LSTRANGTHAI(CMND, TRANGTHAICU, TRANGTHAIMOI, THOIGIAN)" + "	VALUES (?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, id);
			stmt.setString(2, oldStatus);
			stmt.setString(3, newStatus);
			stmt.setString(4, strDate);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi khi thêm vào bảng LSTRANGTHAI");
			e.printStackTrace();
		}

	}

	public static void updateStatusUser(String idUpdate, String newStatus) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "UPDATE NGUOIDUNG SET TRANGTHAI = ? WHERE CMND = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, newStatus);
			stmt.setString(2, idUpdate);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi khi cập nhật TRANGTHAI bảng NGUOIDUNG");
			e.printStackTrace();
		}
	}

	public static void updateStatusRelativeUser(String idUpdated, String oldStatus, String newStatus) {
		if (oldStatus.equals("F2") && newStatus.equals("F1")) {
			Vector<String> idF3 = Managed_User.findListIdRelativeUser(idUpdated);
			for (String f3 : idF3) {
				addStatusHistory(f3, "F3", "F2");
				updateStatusUser(f3, "F2");
			}

		} else if (oldStatus.equals("F2") && newStatus.equals("F0")) {
			Vector<String> idF3 = Managed_User.findListIdRelativeUser(idUpdated);
			for (String f3 : idF3) {
				addStatusHistory(f3, "F3", "F1");
				updateStatusUser(f3, "F1");
			}

		} else if (oldStatus.equals("F1")) {
			Vector<String> idF2 = Managed_User.findListIdRelativeUser(idUpdated);
			for (String f2 : idF2) {
				addStatusHistory(f2, "F2", "F1");
				updateStatusUser(f2, "F1");

				Vector<String> idF3 = Managed_User.findListIdRelativeUser(f2);
				for (String f3 : idF3) {
					addStatusHistory(f3, "F3", "F2");
					updateStatusUser(f3, "F2");
				}
			}
		}
	}
}

package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Zone;
import utils.DatabaseConnect;

public class Managed_Zone {
	private ArrayList<Zone> listZone;

	public Managed_Zone(ArrayList<Zone> listZone) {
		this.listZone = listZone;
	}

	public Managed_Zone() {
		listZone = new ArrayList<Zone>();
	}

	public static DefaultTableModel showZoneTable(DefaultTableModel tabelModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From KHUCACHLY";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));

				}
				tabelModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e1.printStackTrace();
		}
		return tabelModel;
	}

	public static Vector<String> getListZone() {
		Vector<String> zones = new Vector<String>();
		zones.addElement("Nơi điều trị/cách ly");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select TENKCL From KHUCACHLY";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				zones.addElement(rs.getString(1).trim());
			}
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e1.printStackTrace();
		}

		return zones;
	}

	public static String getZoneNameFromId(String id) {
		String zoneName = "";
		Connection con = DatabaseConnect.openConnection();
		String sql = "Select TENKCL From KHUCACHLY  Where MAKCL = '" + id + "'";
		ResultSet rs = DatabaseConnect.getResultSet(con, sql);
		try {
			while (rs.next()) {
				zoneName = rs.getString(1).trim();
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e.printStackTrace();
		}
		return zoneName;
	}

	public static String getIdFromZoneName(String zoneName) {
		String id = null;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT MAKCL FROM KHUCACHLY WHERE TENKCL = N'" + zoneName + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				id = rs.getString(1).trim();
			}
			// System.out.println(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static void addZone(Zone zone) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "INSERT INTO KHUCACHLY(MAKCL,TENKCL,SUCCHUC,DATIEPNHAN)" + "	VALUES  (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, zone.getId());
			stmt.setString(2, zone.getName());
			stmt.setInt(3, zone.getCapacity());
			stmt.setInt(4, zone.getReceivedSlot());

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Thêm mới KHU CÁCH LY không thành công");
			e.printStackTrace();
		}
	}

	public static void updateZone(Zone zone, String idModify) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "UPDATE KHUCACHLY " + "SET MAKCL = ?,TENKCL = ?,SUCCHUC = ?,DATIEPNHAN = ?" + " WHERE MAKCL = ?";
		PreparedStatement stmt;

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, zone.getId());
			stmt.setString(2, zone.getName());
			stmt.setInt(3, zone.getCapacity());
			stmt.setInt(4, zone.getReceivedSlot());
			stmt.setString(5, idModify);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Cập nhật KHU CÁCH LY không thành công");
			e.printStackTrace();
		}
	}

	public static void delZone(String id) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "DELETE FROM KHUCACHLY WHERE MAKCL = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, id);

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Xóa KHU CÁCH LY không thành công");
			e.printStackTrace();
		}
	}

	public static Zone LockDownPlace(String idLockDown) {
		Zone zone = new Zone(idLockDown);

		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From KHUCACHLY WHERE KHUCACHLY.MAKCL='" + idLockDown + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e1.printStackTrace();
		}

		zone.setName(row.get(1));
		zone.setCapacity(Integer.valueOf(row.get(2)));
		zone.setEmptySlot(Integer.valueOf(row.get(3)));

		return zone;

	}

	public static boolean isFull(String zoneName) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select SUCCHUC, DATIEPNHAN From KHUCACHLY Where TENKCL = N'" + zoneName + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int capacity = 0, receivedSlot = 0;
			while (rs.next()) {
				capacity = rs.getInt(1);
				receivedSlot = rs.getInt(2);
			}
			if (capacity == receivedSlot) 
				return true;
			con.close();
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e.printStackTrace();
		}
		return false;
	}
	public static int getReceivedSlot(String zoneId) {
		int receivedSlot = 0;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DATIEPNHAN From KHUCACHLY Where MAKCL = '" + zoneId + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				receivedSlot = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e.printStackTrace();
		}
		return receivedSlot;
	}
	
	public static void updateReceivedSlot(String zoneId, int receivedSlot) {		
		Connection con = DatabaseConnect.openConnection();
		String sql = "UPDATE KHUCACHLY SET DATIEPNHAN = ? WHERE MAKCL = ?";
		PreparedStatement stmt;

		try {
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, receivedSlot);
			stmt.setString(2, zoneId);

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Cập nhật KHU CÁCH LY không thành công");
			e.printStackTrace();
		}
		
	}

	
}

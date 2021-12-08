package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

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
		}catch (SQLException e1) {
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
	public void addZone() {

	}

	public void updateZone(Zone zone) {

	}
}

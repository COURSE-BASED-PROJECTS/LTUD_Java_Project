package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import model.Address;
import model.Zone;
import utils.DatabaseConnect;

public class Managed_Address {
	private ArrayList<Address> listAddress;

	public Managed_Address(ArrayList<Address> listAddress) {
		this.listAddress = listAddress;
	}

	public Managed_Address() {
		listAddress = new ArrayList<Address>();
	}

	public static Vector<String> getListWard() {
		Vector<String> wards = new Vector<String>();
		wards.addElement("Xã/Phường");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DISTINCT XA From DIACHI";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				wards.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng DIACHI");
			e1.printStackTrace();
		}

		return wards;
	}

	public static Vector<String> getListWard(String city, String district) {
		Vector<String> wards = new Vector<String>();
		wards.addElement("Xã/Phường");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DISTINCT XA From DIACHI WHERE TINH = N'" + city + "' AND HUYEN = N'" + district + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				wards.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng DIACHI");
			e1.printStackTrace();
		}

		return wards;
	}

	public static Vector<String> getListDistrict() {
		Vector<String> districts = new Vector<String>();
		districts.addElement("Quận/Huyện");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DISTINCT HUYEN From DIACHI";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				districts.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng DIACHI");
			e1.printStackTrace();
		}

		return districts;
	}

	public static Vector<String> getListDistrict(String city) {
		Vector<String> districts = new Vector<String>();
		districts.addElement("Quận/Huyện");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DISTINCT HUYEN From DIACHI WHERE TINH = N'" + city + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				districts.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng DIACHI");
			e1.printStackTrace();
		}

		return districts;
	}

	public static Vector<String> getListProvice() {
		Vector<String> provices = new Vector<String>();
		provices.addElement("Tỉnh/Thành phố");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select DISTINCT TINH From DIACHI";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				provices.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng DIACHI");
			e1.printStackTrace();
		}

		return provices;
	}
}

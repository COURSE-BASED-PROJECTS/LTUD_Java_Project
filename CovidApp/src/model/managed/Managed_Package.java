package model.managed;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Address;
import model.F;
import model.Package;
import model.User;
import model.Zone;
import utils.DatabaseConnect;

public class Managed_Package extends Managed_General {
	private ArrayList<Package> listPackage;

	public void getAllPackage() {

	}
	
	public static Double getPrice(String id) {
		String price = "";

		String sql = "Select GIATIEN From NHUYEUPHAM  Where MANYP = '" +id+ "'";
		ResultSet rs = DatabaseConnect.getResultSet(DatabaseConnect.openConnection(), sql);
		try {
			while (rs.next()) {
				price = rs.getString(1).trim();
			}
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM từ DB_CovidApp");
			e.printStackTrace();
		}
		return Double.parseDouble(price);
	}
	
	public static DefaultTableModel showPackages(DefaultTableModel tabelModel, boolean ASC, boolean sort) {
		String order = "";
		String sql = "";

		if (ASC) {
			order = "ASC";
			sql = "Select * From NHUYEUPHAM" + " ORDER BY NHUYEUPHAM.GIATIEN " + order;
		} else {
			order = "DESC";
			sql = "Select * From NHUYEUPHAM" + " ORDER BY NHUYEUPHAM.GIATIEN " + order;
		}

		if (!sort) {
			sql = "Select * From NHUYEUPHAM";
		}

		try {
			Connection con = DatabaseConnect.openConnection();
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return tabelModel;
	}

	public static DefaultTableModel filterPackages(DefaultTableModel tabelModel, int type) {
		String sql = "";

		if (type == 1) {
			sql = "Select * From NHUYEUPHAM" + " WHERE NHUYEUPHAM.GIATIEN > 500000";
		} else if (type == 2) {
			sql = "Select * From NHUYEUPHAM" + " WHERE NHUYEUPHAM.GIATIEN >= 250000 "
					+ "AND NHUYEUPHAM.GIATIEN <= 500000";
		} else {
			sql = "Select * From NHUYEUPHAM" + " WHERE NHUYEUPHAM.GIATIEN < 250000";
		}

		try {
			Connection con = DatabaseConnect.openConnection();
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return tabelModel;
	}

	public static void addPackage(Package pk) {
		Connection con = DatabaseConnect.openConnection();
		String timeLimit = new SimpleDateFormat("yyyy-MM-dd").format(pk.getLimitedTime());
		// INSERT INTO NHUYEUPHAM(MANYP,TENNYP,HSD,GIOIHANSL,GIATIEN)
		// VALUES ('NYP01',N'Gói Đồ đóng hộp','2023/01/01', 2, 120000)
		String sql = "INSERT INTO NHUYEUPHAM(MANYP,TENNYP,HSD,GIOIHANSL,GIATIEN)" + " VALUES(?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pk.getId());
			stmt.setString(2, pk.getName());
			stmt.setString(3, timeLimit);
			stmt.setInt(4, pk.getLimitPackages());
			stmt.setDouble(5, pk.getPrice());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Thêm NYP không thành công");
			e.printStackTrace();
		}

	}

	public static void modifyPackage(Package pk) {
		Connection con = DatabaseConnect.openConnection();
		String timeLimit = new SimpleDateFormat("yyyy-MM-dd").format(pk.getLimitedTime());
		// UPDATE NHUYEUPHAM
		// SET TENNYP = 'abc', HSD= '2020-01-01', GIOIHANSL = 0, GIATIEN = 1
		// WHERE MANYP = 'NYP06';
		String sql = "UPDATE NHUYEUPHAM" + " SET TENNYP = ?, HSD= ?, GIOIHANSL = ?, GIATIEN = ?" + " WHERE MANYP = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pk.getName());
			stmt.setString(2, timeLimit);
			stmt.setInt(3, pk.getLimitPackages());
			stmt.setDouble(4, pk.getPrice());
			stmt.setString(5, pk.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Sửa NYP không thành công");
			e.printStackTrace();
		}

	}

	public static DefaultTableModel searchPackage(DefaultTableModel tabelModel, String name) {
		String sql = "SELECT * FROM NHUYEUPHAM " + "WHERE CONTAINS(NHUYEUPHAM.TENNYP,'" + name + "')";

		try {
			Connection con = DatabaseConnect.openConnection();
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return tabelModel;
	}

	public static void delPackage(String id) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "DELETE FROM NHUYEUPHAM WHERE MANYP=?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Xóa NYP không thành công");
			e.printStackTrace();
		}
	}

	public static DefaultTableModel showPackageByName(DefaultTableModel dftm, String keyword) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NHUYEUPHAM Where TENNYP LIKE N'%" + keyword + "%'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static DefaultTableModel sortBy(String col, String type, DefaultTableModel dftm) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT * FROM NHUYEUPHAM ORDER BY" + col + type;
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return dftm;

	}

	public static TableModel filterPackage(DefaultTableModel dftm, String sql) {
		try {
			Connection con = DatabaseConnect.openConnection();
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static Vector<String> getListId() {
		Vector<String> id = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select MANYP From NHUYEUPHAM";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				id.addElement(rs.getString(1).trim());
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NHUYEUPHAM");
			e1.printStackTrace();
		}
		return id;
	}

	public static int getAmountOfConsumption(String id) {
		int count = 0;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select SOLUONG From LSMUAHANG Where LOAIHANG = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				count += rs.getInt(1);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTRANGTHAI");
			e1.printStackTrace();
		}
		return count;
	}

}

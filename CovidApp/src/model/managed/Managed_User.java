package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Address;
import model.F;
import model.User;
import model.Zone;
import utils.DatabaseConnect;

public class Managed_User {
	private ArrayList<User> listUser;

	public Managed_User(ArrayList<User> listUser) {
		this.listUser = listUser;
	}

	public Managed_User() {
		listUser = new ArrayList<User>();
	}

	public static void main(String[] args) {
		//System.out.println(findById("123456789"));
		// test hàm findListId
		Vector<String> id = findListIdRelativeUser("031261418");
		for (String string : id) {
			System.out.println(string);
		}
	}

	public static DefaultTableModel showListUser(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
					if (i >= 6 && i < 8) {
						addr += rs.getString(i) + ",";
					} else if (i == 8) {
						addr += rs.getString(i);
						row.addElement(addr);
					} else
						row.addElement(rs.getString(i));
				}
				tableModel.addRow(row);
			}
		} catch (

		SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static void addUser(User user) {
		Connection con = DatabaseConnect.openConnection();
		// INSERT INTO NGUOIDUNG(CMND,HOTEN,NAMSINH,TRANGTHAI,XA,HUYEN,TINH)
		// VALUES ('0312614186', N'Nguyễn Văn An',1997,'F0',N'Phường 4',N'Quận
		// 5',N'TP.HCM')
		String sql = "INSERT INTO NGUOIDUNG(CMND,HOTEN,NAMSINH,TRANGTHAI,XA,HUYEN,TINH,NGUOILIENQUAN,NOICACHLY)"
				+ "	VALUES  (?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, user.getId());
			stmt.setString(2, user.getName());
			stmt.setInt(3, user.getYearOfBirth());
			stmt.setString(4, user.getStatus().getF());
			stmt.setString(5, user.getAddress().getWard());
			stmt.setString(6, user.getAddress().getDistrict());
			stmt.setString(7, user.getAddress().getProvince());
			stmt.setString(8, user.getRelative().getId());
			stmt.setString(9, user.getPlaceOfTreatment().getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Thêm NGUOIDUNG không thành công");
			e.printStackTrace();
		}
	}

	public static void modifyUser(User user, String idModify) {
		Connection con = DatabaseConnect.openConnection();
		// UPDATE NGUOIDUNG
		// SET CMND = '123456', HOTEN = 'TÈO', NAMSINH = 2003, TRANGTHAI = 'F1',
		// NGUOILIENQUAN = '',XA = 'Phường 10', HUYEN = 'Huyện Bắc Ninh', TINH =
		// 'TP.HCM', NOICACHLY = 'KS002'
		// WHERE CMND = '123';
		String sql = "UPDATE NGUOIDUNG "
				+ "SET CMND = ?, HOTEN = ?, NAMSINH = ?, TRANGTHAI = ?, NGUOILIENQUAN = ?, XA = ?, HUYEN = ?, TINH = ?, NOICACHLY = ?"
				+ " WHERE CMND = ?";
		PreparedStatement stmt;
		
		String oldStatus = Managed_Status.getStatusFromId(idModify);
		String newStatus = user.getStatus().getF();
		
		if (!oldStatus.equals(newStatus)) {
			Managed_Status.addStatusHistory(idModify, oldStatus, newStatus);
			if (!oldStatus.equals("F3")) {
				Managed_Status.updateStatusRelativeUser(idModify, oldStatus, newStatus);
			}
		}
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, user.getId());
			stmt.setString(2, user.getName());
			stmt.setInt(3, user.getYearOfBirth());
			stmt.setString(4, user.getStatus().getF());
			stmt.setString(5, user.getRelative().getId());
			stmt.setString(6, user.getAddress().getWard());
			stmt.setString(7, user.getAddress().getDistrict());
			stmt.setString(8, user.getAddress().getProvince());
			stmt.setString(9, user.getPlaceOfTreatment().getId());
			stmt.setString(10, idModify);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Sửa NGUOIDUNG không thành công");
			e.printStackTrace();
		}

	}

	public static void delUser(String id) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "DELETE FROM NGUOIDUNG WHERE CMND = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Xóa NGUOI DUNG không thành công");
			e.printStackTrace();
		}

	}

	public static DefaultTableModel sortBy(String col, String type, DefaultTableModel dftm) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT * FROM NGUOIDUNG ORDER BY " + col + type;
			System.out.println(sql);
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
					if (i >= 6 && i < 8) {
						addr += rs.getString(i) + ",";
					} else if (i == 8) {
						addr += rs.getString(i);
						row.addElement(addr);
					} else
						row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return dftm;

	}

	public static TableModel sortByName(String type, DefaultTableModel dftm) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT * FROM NGUOIDUNG ORDER BY REVERSE(SUBSTRING(REVERSE(HOTEN), 0, CHARINDEX(' ', REVERSE(HOTEN))))"
					+ type;
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
					if (i >= 6 && i < 8) {
						addr += rs.getString(i) + ",";
					} else if (i == 8) {
						addr += rs.getString(i);
						row.addElement(addr);
					} else
						row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static TableModel showUserByNameAndId(DefaultTableModel dftm, String id, String name) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT * FROM NGUOIDUNG " + "WHERE HOTEN Like N'%" + name + "%' AND CMND Like '%" + id + "%'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
					if (i >= 6 && i < 8) {
						addr += rs.getString(i) + ",";
					} else if (i == 8) {
						addr += rs.getString(i);
						row.addElement(addr);
					} else
						row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static User findById(String Id) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE CMND = " + Id;
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				String id = rs.getString(1).trim();
				String name = rs.getString(2).trim();
				int year = rs.getInt(3);
				String f = rs.getString(4).trim();
				F status = null;
				if (f.equals("F0")) {
					status = F.F0;
				} else if (f.equals("F1")) {
					status = F.F1;
				} else if (f.equals("F2")) {
					status = F.F2;
				} else if (f.equals("F3")) {
					status = F.F3;
				}
				String relative = rs.getString(5).trim();
				User relativeUser = new User(relative);

				String ward = rs.getString(6);
				String district = rs.getString(7);
				String city = rs.getString(8);
				Address addr = new Address(ward, district, city);

				String zoneId = rs.getString(9).trim();
				Zone zone = new Zone(zoneId);
				return new User(name, id, year, addr, status, zone, relativeUser);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return null;
	}

	public static TableModel showListUserRelative(String id, DefaultTableModel dftm) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE NGUOILIENQUAN = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn - 1; i++) {
					if (i == 5) {
						continue;
					}
					if (i >= 6 && i < 8) {
						addr += rs.getString(i) + ",";
					} else if (i == 8) {
						addr += rs.getString(i);
						row.addElement(addr);
					} else
						row.addElement(rs.getString(i));
				}
				dftm.addRow(row);
			}
		} catch (

		SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static Vector<String> findListIdRelativeUser(String id) {
		Vector<String> listId = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select CMND From NGUOIDUNG WHERE NGUOILIENQUAN = '" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				listId.addElement(rs.getString(1).trim());
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return listId;
	}
}

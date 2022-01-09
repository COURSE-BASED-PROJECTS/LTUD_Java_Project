package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.lang.model.type.NullType;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Account;
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

	public static void showPaymentUser(JLabel debitCurrentText, JLabel balanceCurrentText, Account account) {
		debitCurrentText.setText(account.getDebit() == null ? "0" : account.getDebit());
		balanceCurrentText.setText(account.getBalance() == null ? "0" : account.getBalance());
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
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static void addUser(User user) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "INSERT INTO NGUOIDUNG(CMND,HOTEN,NAMSINH,TRANGTHAI,XA,HUYEN,TINH,NGUOILIENQUAN,NOICACHLY)"
				+ "	VALUES  (?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt = null;

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
			stmt.close();
			con.close();
			Managed_Status.addStatusHistory(user.getId(), "", user.getStatus().getF());
		} catch (SQLException e) {
			System.out.println("Thêm NGUOIDUNG không thành công");
			e.printStackTrace();
		}
	}

	public static User setUser(String id) {

		User user = new User(id);
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE NGUOIDUNG.CMND='" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}

		user.setName(row.get(1));
		user.setYearOfBirth(Integer.valueOf(row.get(2)));
		user.setStatus(row.get(3).contains("F2") ? F.F2 : (row.get(3).contains("F1") ? F.F1 : F.F0));
		user.setAddress(new Address(row.get(5), row.get(6), row.get(7)));
		
		System.out.println(row.get(8));
		
		if(row.get(8) != null){
			user.setPlaceOfTreatment(Managed_Zone.LockDownPlace(row.get(8)));
		}else {
			user.setPlaceOfTreatment(new Zone("", "Không có", 0, 0));
		}
		
		if(row.get(4) != null){
			user.setRelativesString(searchNameRelativeById(row.get(4)));
		}else {
			user.setRelativesString("Không có");
		}

		String balance = null, debt = null;
		try {
			String sql = "Select SODU, DUNO From TAIKHOAN  Where TAIKHOAN = '"+ id +"'";
			ResultSet rs = DatabaseConnect.getResultSet(DatabaseConnect.openConnection(), sql);
			while (rs.next()) {
				balance = rs.getString(1).trim();
				debt = rs.getString(2).trim();
			}
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN từ BD_CovidApp");
			e.printStackTrace();
		}
		user.setBalance(Double.parseDouble(balance));
		user.setDebt(Double.parseDouble(debt));
		
		return user;
	}

	public static String searchNameRelativeById(String id) {
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE NGUOIDUNG.CMND='" + id + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}

		return row.get(1);

	}

	public static void modifyUser(User user, String idModify) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "UPDATE NGUOIDUNG "
				+ "SET CMND = ?, HOTEN = ?, NAMSINH = ?, TRANGTHAI = ?, NGUOILIENQUAN = ?, XA = ?, HUYEN = ?, TINH = ?, NOICACHLY = ?"
				+ " WHERE CMND = ?";
		PreparedStatement stmt;

		String oldStatus = Managed_Status.getStatusFromId(idModify);
		String newStatus = user.getStatus().getF();

		if (!oldStatus.equals(newStatus)) {
			Managed_Status.addStatusHistory(idModify, oldStatus, newStatus);
			Managed_Status.updateStatusUser(idModify, newStatus);
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
			if (user.getPlaceOfTreatment().getId().equals("")) {
				stmt.setNull(9, Types.NVARCHAR);
			} else {
				stmt.setString(9, user.getPlaceOfTreatment().getId());	
			}
			
			stmt.setString(10, idModify);

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Sửa NGUOIDUNG không thành công");
			e.printStackTrace();
		}

	}
	public static DefaultTableModel sortBy(String col, String type, DefaultTableModel dftm) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "SELECT * FROM NGUOIDUNG ORDER BY " + col + type;

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
			con.close();
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
			con.close();
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
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return dftm;
	}

	public static User findById(String Id) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE CMND = '" + Id + "'";
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
				} else if (f.equals("Khỏi bệnh")) {
					status = F.CURED;
				}
				String relative;
				if (rs.getString(5) == null) {
					relative = "";
				} else {
					relative = rs.getString(5).trim();
				}
				User relativeUser = new User(relative);

				String ward = rs.getString(6);
				String district = rs.getString(7);
				String city = rs.getString(8);
				Address addr = new Address(ward, district, city);
				
				String zoneId = "";
				if (rs.getString(9) != null) {
					zoneId = rs.getString(9).trim();
				}
				Zone zone = new Zone(zoneId);
				con.close();
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
			con.close();
		} catch (SQLException e1) {
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
			con.close();
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return listId;
	}

	public static Vector<String> getListRelative(String status) {
		Vector<String> relativeId = new Vector<String>();
		relativeId.addElement("");
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select CMND From NGUOIDUNG Where TRANGTHAI = '" + status + "'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				relativeId.addElement(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}

		return relativeId;
	}
}

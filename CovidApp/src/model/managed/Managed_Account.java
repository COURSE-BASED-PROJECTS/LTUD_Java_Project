package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Role;
import model.AccountCurrent;
import utils.DatabaseConnect;
import utils.Password;

public class Managed_Account {
	public static DefaultTableModel showAccountTable(DefaultTableModel tabelModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From TAIKHOAN";
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
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return tabelModel;
	}

	public static void addAccount(Account acc) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "INSERT INTO TAIKHOAN(TAIKHOAN,MATKHAU,PHANHE,TINHTRANG)" + "	VALUES  (?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, acc.getUserName());
			stmt.setString(2, Password.encrypt(acc.getPassword()));

			if (acc.getRole() == Role.MANAGER)
				stmt.setString(3, "QUANLY");
			else if (acc.getRole() == Role.USER)

				switch (acc.getRole()) {
				case MANAGER:
					stmt.setString(3, "QUANLY");
					break;
				case USER:
					stmt.setString(3, "NGUOIDUNG");
					break;
				case ADMIN_COVID:
					stmt.setString(3, "QUANTRI");
					break;
				case ADMIN_PAYMENT:
					stmt.setString(3, "QUANTRI");
					break;
				default:
					stmt.setString(3, "");
					break;
				}

			stmt.setString(4, "1");

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Thêm mới TÀI KHOẢN không thành công");
			e.printStackTrace();
		}
	}

	public static void lockAcc(String id) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "UPDATE TAIKHOAN SET TINHTRANG = 0 WHERE TAIKHOAN = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Khoá tài khoản không thành công");
			e.printStackTrace();
		}
	}
	public static void activeAcc(String id) {
		Connection con = DatabaseConnect.openConnection();

		String sql = "UPDATE TAIKHOAN SET TINHTRANG = 1 WHERE TAIKHOAN = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Kích hoạt tài khoản không thành công");
			e.printStackTrace();
		}
	}

	public static boolean isEmpty() {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select count(*) From TAIKHOAN";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					return true;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return false;
	}

	public static String getRole(String username) {
		String role = "";
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select PHANHE From TAIKHOAN Where TAIKHOAN = '" + username + "'";

			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				role = rs.getString(1).trim();
				return role;
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return role;
	}

	public static boolean isAccount(String username, String password) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select count(*) From TAIKHOAN Where TAIKHOAN = '" + username + "' AND MATKHAU = '" + password
					+ "' AND TINHTRANG = 1";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					return true;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return false;
	}
	
	public static Account setAccount (String id) {
		
		Account account = new Account();
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From TAIKHOAN WHERE TAIKHOAN='"+id+"'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		account.setUserName(id);
		account.setDebit(row.get(4));
		account.setBalance(row.get(3));
		
		return account;
	}

	public static void changePassword(String username, String newPass) {
		Connection con = DatabaseConnect.openConnection();
		String sql = "Update TAIKHOAN" + " Set MATKHAU = ?" + " Where TAIKHOAN = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, newPass);
			stmt.setString(2, username);

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Đổi mật khẩu không thành công");
			e.printStackTrace();
		}

	}

	public static String getPassword(String username) {
		String pass = "";
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select MATKHAU From TAIKHOAN Where TAIKHOAN = '" + username + "'";

			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				pass = rs.getString(1).trim();
				return pass;
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return pass;
	}

	public static Double getTotalDebt() {
		Double totalDebt = 0.0d;
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select SUM(DUNO) From TAIKHOAN";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			while (rs.next()) {
				totalDebt = rs.getDouble(1);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN");
			e1.printStackTrace();
		}
		return totalDebt;
	}
}



		

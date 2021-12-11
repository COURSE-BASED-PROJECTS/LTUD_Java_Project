package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.AccountCurrent;
import utils.DatabaseConnect;

public class Managed_Account {
	public static DefaultTableModel showAccount(DefaultTableModel tabelModel) {
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
	
	static public void changePassword(String id, String password) throws SQLException {
		Connection con = DatabaseConnect.openConnection();
		String sql = "UPDATE TAIKHOAN SET MATKHAU='"+password+"' WHERE TAIKHOAN='"+id+"'";
		DatabaseConnect.query(con, sql);
	}
}

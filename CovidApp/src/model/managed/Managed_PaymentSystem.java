package model.managed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Payment_History;
import utils.DatabaseConnect;

public class Managed_PaymentSystem{
	public static String getBalance(String username) {
		String balance = "";

		String sql = "Select SODU From TAIKHOAN  Where TAIKHOAN = '" +username+ "'";
		ResultSet rs = DatabaseConnect.getResultSet(DatabaseConnect.connectDB_Payment(), sql);
		try {
			while (rs.next()) {
				balance = rs.getString(1).trim();
			}
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN từ DB_Payment");
			e.printStackTrace();
		}
		return balance;
	}
	public static String getDebt(String username) {
		String debt = "";

		String sql = "Select DUNO From TAIKHOAN  Where TAIKHOAN = '"+ username +"'";
		ResultSet rs = DatabaseConnect.getResultSet(DatabaseConnect.connectDB_Payment(), sql);
		try {
			while (rs.next()) {
				debt = rs.getString(1).trim();
			}
		} catch (SQLException e) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng TAIKHOAN từ DB_Payment");
			e.printStackTrace();
		}
		return debt;
	}
	
	public static boolean insertPayment(Payment_History paymentHistory) {

		Connection con_Payment = DatabaseConnect.connectDB_Payment();
		boolean result = false;
		String sql = "INSERT INTO LSTHANHTOAN(MAGD,CMND,DUNO,SOTIENTRA,THOIGIAN) VALUES(?,?,?,?,?)";
		
		try {

			PreparedStatement stmt = con_Payment.prepareStatement(sql);
			stmt.setString(1, paymentHistory.getMaGD());
			stmt.setString(2, paymentHistory.getCMND());
			stmt.setDouble(3, paymentHistory.getDebit());
			stmt.setDouble(4, paymentHistory.getMinDebt());
			stmt.setTimestamp(5, paymentHistory.getTime());

			result = stmt.executeUpdate() > 0;

			stmt.close();
			con_Payment.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updateBalance(Double money, String username) {
		Connection con_Payment = DatabaseConnect.connectDB_Payment();
		
		boolean result = false;
		String sql = "UPDATE TAIKHOAN SET SODU=?"
				+ " WHERE TAIKHOAN=?";
		
		try {
			PreparedStatement stmt = con_Payment.prepareStatement(sql);
			stmt.setDouble(1, money);
			stmt.setString(2, username);

			result = stmt.executeUpdate() > 0;
			stmt.close();
			con_Payment.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	public static boolean updateDebt(Double debt, String username) {
		Connection con_Payment = DatabaseConnect.connectDB_Payment();
		
		boolean result = false;
		String sql = "UPDATE TAIKHOAN SET DUNO=?"
				+ " WHERE TAIKHOAN=?";
		
		try {
			PreparedStatement stmt = con_Payment.prepareStatement(sql);
			stmt.setDouble(1, debt);
			stmt.setString(2, username);

			result = stmt.executeUpdate() > 0;
			stmt.close();
			con_Payment.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;	
	}
}
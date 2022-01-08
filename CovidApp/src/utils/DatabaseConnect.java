package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.SpinnerListModel;

public class DatabaseConnect {
	public static Connection openConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String ConnectionUrl = "jdbc:sqlserver://localhost;databaseName=DB_CovidApp;integratedSecurity=true;";
			
			try {
				con = DriverManager.getConnection(ConnectionUrl);
				System.out.println("Successful Connection DB_CovidApp!");

				
			} catch (SQLException e) {
				System.out.println("Failed Connection!");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed Connection!");
		}
		
		return con;
	}
	
	public static Connection openConnection(String username, String password) {
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String ConnectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DB_CovidApp"
					+ ";username=" + username + ";password=" + password;
			
			try {
				con = DriverManager.getConnection(ConnectionUrl);
				System.out.println("Successful Connection!");
				
			} catch (SQLException e) {
				System.out.println("Failed Connection!");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed Connection!");
		}
		
		return con;
	}
	

	
	public static boolean query(Connection con, String sql) {
		Statement stm = null;
		
		try {
			stm = con.createStatement();
			stm.execute(sql);
			stm.close();
		} catch (SQLException e) {
			System.out.println("Failed Query!");
//			e.printStackTrace();
			return false;
		}
		
		System.out.println("Sucessfull Query!");
		return true;
	}
	
	public static ResultSet getResultSet(Connection con,String query) {
		Statement stm = null;
		ResultSet rs = null;
		
		
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(query);
			
		} catch (SQLException e1) {
			System.out.println("Failed Query!");
			e1.printStackTrace();
		}
		
		return rs;
	}
	
	public static Connection connectDB_Payment() {
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String ConnectionUrl = "jdbc:sqlserver://localhost;databaseName=DB_Payment;integratedSecurity=true;";
			
			try {
				con = DriverManager.getConnection(ConnectionUrl);
				System.out.println("Successful Connection DB_Payment!");
				
			} catch (SQLException e) {
				System.out.println("Failed Connection DB_Payment!");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Failed Connection DB_Payment!");
		}
		return con;
	}
}


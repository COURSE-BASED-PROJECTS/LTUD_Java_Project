package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.Address;
import model.F;
import model.User;
import model.Zone;
import utils.DatabaseConnect;

public class Managed_Package extends Managed_General{
	private ArrayList<Package> listPackage;
	
	public void getAllPackage() {
		
	}
	public static DefaultTableModel showPackages(DefaultTableModel tabelModel,boolean ASC,boolean sort) {
		String order = "";
		String sql="";
		
		if(ASC) {
			order = "ASC";
			sql = "Select * From NHUYEUPHAM" + " ORDER BY NHUYEUPHAM.GIATIEN "+order;
		}
		else {
			order = "DESC";
			sql = "Select * From NHUYEUPHAM" + " ORDER BY NHUYEUPHAM.GIATIEN "+order;
		}
		
		if(!sort) {
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
	
	public static DefaultTableModel filterPackages(DefaultTableModel tabelModel,int type) {
		String sql="";
		
		if(type == 1) {
			sql = "Select * From NHUYEUPHAM" + " WHERE NHUYEUPHAM.GIATIEN > 500000";
		}
		else if(type == 2){
			sql = "Select * From NHUYEUPHAM" + " WHERE NHUYEUPHAM.GIATIEN >= 250000 "
					+ "AND NHUYEUPHAM.GIATIEN <= 500000";
		}
		else {
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
	
	public static DefaultTableModel searchPackage(DefaultTableModel tabelModel,String name) {
		String sql="SELECT * FROM NHUYEUPHAM "
				+ "WHERE NHUYEUPHAM.TENNYP LIKE N'%"+name+"%'";
		
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
	public void sortPackage(String type) {
		
	}
	public void updatePackage(Package pk) {
		
	}
	public void addPackage(Package pk) {
		
	}
}

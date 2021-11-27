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
	public static DefaultTableModel showPackages(DefaultTableModel tabelModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NHUYEUPHAM";
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
	public Package searchPackage(Package pk) {
		return null;
	}
	public void sortPackage(String type) {
		
	}
	public void updatePackage(Package pk) {
		
	}
	public void addPackage(Package pk) {
		
	}
}

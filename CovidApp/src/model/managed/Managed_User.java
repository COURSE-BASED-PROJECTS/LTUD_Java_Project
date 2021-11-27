package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

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

	public DefaultTableModel showListUser(DefaultTableModel tableModel) {
//		for (User user : listUser) {
//			tableModel.addRow(new Object[] { user.getId(), user.getName(), user.getYearOfBirth(), user.getAddress(),
//					user.getStatus(), user.getPlaceOfTreatment().toString(), user.getRelative().getId() });
//		}
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
					if(i>=2&&i<=4) {
						addr += rs.getString(i);
						if(i==4)
						{
							row.addElement(addr);
						}
					}
					else
						row.addElement(rs.getString(i));
				}
				
				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public void viewDetailUser(User user) {

	}

	public User searchById(String id) {
		return null;
	}

	public User searchByName(String name) {
		return null;
	}

	public void sortBy(String type) {

	}

	public void addUser(User u) {
		this.listUser.add(u);
	}

	public void updateUser(User user) {

	}

	public void delUser(User user) {

	}

	public void updateZone(User user, Zone newZone) {

	}

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}
	

}

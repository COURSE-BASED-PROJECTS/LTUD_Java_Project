package model.managed;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.User;
import model.Zone;

public class Managed_User {
	private ArrayList<User> listUser;

	public Managed_User(ArrayList<User> listUser) {
		this.listUser = listUser;
	}

	public Managed_User() {
		listUser = new ArrayList<User>();
	}

	public void showListUser(DefaultTableModel tableModel) {
		for (User user : listUser) {
			tableModel.addRow(new Object[] { user.getId(), user.getName(), user.getYearOfBirth(), user.getAddress(),
					user.getStatus(), user.getPlaceOfTreatment().toString(), user.getRelative().getId() });
		}
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

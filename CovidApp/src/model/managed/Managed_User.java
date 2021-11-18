package model.managed;

import java.util.ArrayList;

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
	
	public void showListUser() {
		
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
	public void addUser() {
		
	}
	public void updateUser(User user) {
		
	}
	public void delUser(User user) {
		
	}
	public void updateZone(User user, Zone newZone) {
		
	}
	
}

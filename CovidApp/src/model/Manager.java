package model;

import model.managed.Managed_Package;
import model.managed.Managed_Status;
import model.managed.Managed_User;

public class Manager {
	private Managed_User users;
	private Managed_Package packages;
	private Managed_Status histories;
	private Account account;
	

	public Manager(Managed_User users, Managed_Package packages, Managed_Status histories, Account account) {
		this.users = users;
		this.packages = packages;
		this.histories = histories;
		this.account = account;
	}
	
	
	public Managed_User getUsers() {
		return users;
	}


	public void setUsers(Managed_User users) {
		this.users = users;
	}


	public Managed_Package getPackages() {
		return packages;
	}


	public void setPackages(Managed_Package packages) {
		this.packages = packages;
	}


	public Managed_Status getHistories() {
		return histories;
	}


	public void setHistories(Managed_Status histories) {
		this.histories = histories;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public void staticsStatus(String type) {

	}

	public void staticsInfor(String type) {

	}

	public void staticsPakage() {

	}

	public void staticsBalance() {

	}
}

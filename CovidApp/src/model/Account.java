package model;

public class Account {
	private String userName;
	private String password;
	private Role role;
	private String balance;
	private String debit;
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public Account(String userName, String password, Role role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public Account() {
		this.userName = "";
		this.password = "";
		this.role = null;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}

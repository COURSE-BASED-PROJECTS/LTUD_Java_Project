package model;

public class Admin_Payment {
	private String id;
	private Double balance;
	private Account account;
	
	public Admin_Payment(String id, Double balance, Account account) {
		this.id = id;
		this.balance = balance;
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void addAccountUser() {
		
	}
}

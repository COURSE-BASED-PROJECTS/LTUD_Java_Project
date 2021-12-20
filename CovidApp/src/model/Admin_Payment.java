package model;

public class Admin_Payment {
	private static String id = "Admin_Payment";
	private Double balance;
	
	public Admin_Payment(String id, Double balance) {
		Admin_Payment.id = id;
		this.balance = balance;
	}

	public static String getId() {
		return id;
	}

	public void setId(String id) {
		Admin_Payment.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}

package model;

import java.util.ArrayList;
import java.util.Date;

import model.managed.Managed_Order;
import model.managed.Managed_Payment;

public class User {
	private String name;
	private String id;
	private Date dayOfBirth;
	private Address address;
	private F status;
	private Zone placeOfTreatment;
	private Double balance;
	private Double debt;
	private User relative;
	private Account account;
	private ArrayList<Status_History> listStatus;
	private Managed_Payment listPayment;
	private Managed_Order listOrder;
	
	public User(String name, String id, Date dayOfBirth, Address address, F status, Zone placeOfTreatment,
			Double balance, Double debt, User relative, Account account, ArrayList<Status_History> listStatus,
			Managed_Payment listPayment, Managed_Order listOrder) {
		this.name = name;
		this.id = id;
		this.dayOfBirth = dayOfBirth;
		this.address = address;
		this.status = status;
		this.placeOfTreatment = placeOfTreatment;
		this.balance = balance;
		this.debt = debt;
		this.relative = relative;
		this.account = account;
		this.listStatus = listStatus;
		this.listPayment = listPayment;
		this.listOrder = listOrder;
	}
	
	public User() {

	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public F getStatus() {
		return status;
	}

	public void setStatus(F status) {
		this.status = status;
	}

	public Zone getPlaceOfTreatment() {
		return placeOfTreatment;
	}

	public void setPlaceOfTreatment(Zone placeOfTreatment) {
		this.placeOfTreatment = placeOfTreatment;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public User getRelative() {
		return relative;
	}

	public void setRelative(User relative) {
		this.relative = relative;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ArrayList<Status_History> getListStatus() {
		return listStatus;
	}

	public void setListStatus(ArrayList<Status_History> listStatus) {
		this.listStatus = listStatus;
	}

	public Managed_Payment getListPayment() {
		return listPayment;
	}

	public void setListPayment(Managed_Payment listPayment) {
		this.listPayment = listPayment;
	}

	public Managed_Order getListOrder() {
		return listOrder;
	}

	public void setListOrder(Managed_Order listOrder) {
		this.listOrder = listOrder;
	}

	public void setDebt(Double debt) {
		this.debt = debt;
	}
	
	public Double getDebt() {
		return debt;
	}

	public void showInfo() {
		
	}
	public void showManagementHistory() {
		
	}
	public void showPaymentHistory() {
		
	}
	
	public void showPackage(ArrayList<Package> list) {
		
	}
	public Double payoffDebt(Double minDebt) {
		return 0.0;
	}
	public Package searchPackage(ArrayList<Package> list) {
		return null;
	}
	public ArrayList<Package> filterPackage(ArrayList<Package> list, String type) {
		return null;
	}
	public void addStatusHistory() {
		
	}
}

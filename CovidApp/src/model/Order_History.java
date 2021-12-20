package model;

import java.util.ArrayList;
import java.util.Date;

public class Order_History extends History{
	
	private String MaHD;
	private String CMND;
	private String type;
	private int quantity;
	private double price;
	
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cmnd) {
		CMND = cmnd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPricey() {
		return price;
	}
	public void setPrice(double pr) {
		this.price = pr;
	}
	
	public static String randomCodeHistory() {
		char letterCode = (char)Math.floor(Math.random()*(90-65+1)+65);
		int numberCode = (int)Math.floor(Math.random()*(9999-1111+1)+1111);
		
		return letterCode + String.valueOf(numberCode);	
	}
}

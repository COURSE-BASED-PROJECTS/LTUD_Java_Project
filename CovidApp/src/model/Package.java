package model;

import java.util.Date;

public class Package {
	private String id;
	private String name;
	private Date limitedTime;
	private Double price;
	private int limitPackages;
		

	public Package() {

	}

	public Package(String id, String name, Date limitedTime, Double price, int limitPackages) {
		this.id = id;
		this.name = name;
		this.limitedTime = limitedTime;
		this.price = price;
		this.limitPackages = limitPackages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(Date limitedTime) {
		this.limitedTime = limitedTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public int getLimitPackages() {
		return limitPackages;
	}
	
	public void setLimitPackages(int limitPackages) {
		this.limitPackages = limitPackages;
	}
	
	public void showPackage() {
		
	}
}

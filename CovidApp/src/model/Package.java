package model;

import java.util.Date;

public class Package {
	private String name;
	private Date limitedTime;
	private Double price;
	private int limitPackages;
	
	public int getLimitPackages() {
		return limitPackages;
	}

	public void setLimitPackages(int limitPackages) {
		this.limitPackages = limitPackages;
	}

	public Package(String name, Date limitedTime, Double price) {
		this.name = name;
		this.limitedTime = limitedTime;
		this.price = price;
	}

	public Package() {

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

	public void showPackage() {
		
	}
}

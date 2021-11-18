package model;

import java.util.ArrayList;
import java.util.Date;

public class Order_History extends History{
	private ArrayList<Package> listOrder;
	
	public Order_History() {
		listOrder = new ArrayList<Package>();
	}

	public Order_History(ArrayList<Package> listOrder) {
		this.listOrder = listOrder;
	}


	public void showOrderHistory() {
		
	}
}

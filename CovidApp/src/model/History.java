package model;

import java.util.Date;

public class History {
	protected Date time;
	public History(Date time) {
		this.time = time;
	}
	public History() {
		
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void showHistory() {
		
	}
}

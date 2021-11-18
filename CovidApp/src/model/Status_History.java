package model;

import java.util.Date;

public class Status_History extends History{
	private User updateStatus;

	public Status_History(User updateStatus) {
		this.updateStatus = updateStatus;
	}

	public User getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(User updateStatus) {
		this.updateStatus = updateStatus;
	}
	
	
}

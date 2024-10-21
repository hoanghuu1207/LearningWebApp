package model;

import java.time.LocalDateTime;

public class NotificationModel {
	private int notificationID;
	private int userID;
	private boolean status;
	private String type;
	private int relatedID;
	private LocalDateTime createdAt;

	public NotificationModel(int userID, boolean status, String type, int relatedID, LocalDateTime createdAt) {
		super();
		this.userID = userID;
		this.status = status;
		this.type = type;
		this.relatedID = relatedID;
		this.createdAt = createdAt;
	}

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRelatedID() {
		return relatedID;
	}

	public void setRelatedID(int relatedID) {
		this.relatedID = relatedID;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}

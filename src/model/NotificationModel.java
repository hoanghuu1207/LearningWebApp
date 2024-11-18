package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NotificationModel {
	private int notificationID;
	private int informedID;
	private int status;
	private String type;
	private int relatedID;

	public NotificationModel(){}

	public int getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public int getInformedID() {
		return informedID;
	}

	public void setInformedID(int informedID) {
		this.informedID = informedID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
}

package model;

import java.time.LocalDateTime;

public class ScheduleModel {
	private int scheduleID;
	private int meetingID;
	private LocalDateTime timeCreate;
	private LocalDateTime timeAccess;
	private String title;

	public ScheduleModel(int meetingID, LocalDateTime timeCreate, LocalDateTime timeAccess, String title) {
		super();
		this.meetingID = meetingID;
		this.timeCreate = timeCreate;
		this.timeAccess = timeAccess;
		this.title = title;
	}

	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public int getMeetingID() {
		return meetingID;
	}

	public void setMeetingID(int meetingID) {
		this.meetingID = meetingID;
	}

	public LocalDateTime getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(LocalDateTime timeCreate) {
		this.timeCreate = timeCreate;
	}

	public LocalDateTime getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(LocalDateTime timeAccess) {
		this.timeAccess = timeAccess;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScheduleModel {
	private int scheduleID;
	private int meetingID;
	private Timestamp timeCreate;
	private Timestamp timeAccess;
	private String title;

	public ScheduleModel() {}
	public ScheduleModel(int meetingID, Timestamp timeCreate, Timestamp timeAccess, String title) {
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

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Timestamp getTimeAccess() {
		return timeAccess;
	}

	public void setTimeAccess(Timestamp timeAccess) {
		this.timeAccess = timeAccess;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

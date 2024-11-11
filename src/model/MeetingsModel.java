package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MeetingsModel {
	private int meetingID;
	private String title;
	private Timestamp startTime;
	private Timestamp endTime;
	private int classroomID;
	private Time duration;
	private int isCancelled;

	public MeetingsModel() {}
	public MeetingsModel(String title, Timestamp startTime, Timestamp endTime, int classroomID, Time duration, int isCancelled) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classroomID = classroomID;
		this.duration = duration;
		this.isCancelled = isCancelled;
	}

	public int getMeetingID() {
		return meetingID;
	}

	public void setMeetingID(int meetingID) {
		this.meetingID = meetingID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public int getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(int isCancelled) {
		this.isCancelled = isCancelled;
	}
}


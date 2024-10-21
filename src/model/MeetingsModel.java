package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MeetingsModel {
	private int meetingID;
	private String title;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int classroomID;
	private LocalTime duration;

	public MeetingsModel(String title, LocalDateTime startTime, LocalDateTime endTime, int classroomID,
			LocalTime duration) {
		super();
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classroomID = classroomID;
		this.duration = duration;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}
}

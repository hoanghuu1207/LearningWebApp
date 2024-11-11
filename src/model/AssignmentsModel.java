package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AssignmentsModel {
	private int assignmentID;
	private String title;
	private String description;
	private Timestamp startTime;
	private Timestamp endTime;
	private int classroomID;

	public AssignmentsModel() {}



	public AssignmentsModel(String description, String title, Timestamp startTime, Timestamp endTime, int classroomID) {
		super();
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classroomID = classroomID;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}

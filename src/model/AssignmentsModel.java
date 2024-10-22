package model;

import java.time.LocalDateTime;

public class AssignmentsModel {
	private int assignmentID;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int classroomID;

	public AssignmentsModel(String description, LocalDateTime startTime, LocalDateTime endTime, int classroomID) {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}

package model;

import java.sql.Timestamp;

public class ClassroomsModel {
	private int classroomID;
	private String title;
	private int teacherID;
	private Timestamp createdAt;

	/*
	 * public ClassroomsModel(String title, int teacherID) { super(); this.title =
	 * title; this.teacherID = teacherID; }
	 */

	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}

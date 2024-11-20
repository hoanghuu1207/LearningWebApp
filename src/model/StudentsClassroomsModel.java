package model;

import java.sql.Timestamp;

public class StudentsClassroomsModel {
	private int id;
	private int studentID;
	private int classroomID;
	private Timestamp createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public StudentsClassroomsModel(int studentID, int classroomID) {
		super();
		this.studentID = studentID;
		this.classroomID = classroomID;
	}

	public StudentsClassroomsModel(){}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}
}

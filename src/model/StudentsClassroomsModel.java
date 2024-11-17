package model;

public class StudentsClassroomsModel {
	private int studentID;
	private int classroomID;

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

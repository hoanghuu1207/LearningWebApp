package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SubmissionsModel {
	private int submissionID;
	private int assignmentID;
	private int studentID;
	private Timestamp submissionDate;
	private int materialID;
	private String titleFile;
	private String filePath;

	public SubmissionsModel(){}

	public SubmissionsModel(int assignmentID, int studentID, Timestamp submissionDate) {
		super();
		this.assignmentID = assignmentID;
		this.studentID = studentID;
		this.submissionDate = submissionDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

	public String getTitleFile() {
		return titleFile;
	}

	public void setTitleFile(String titleFile) {
		this.titleFile = titleFile;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public Timestamp getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Timestamp submissionDate) {
		this.submissionDate = submissionDate;
	}
}

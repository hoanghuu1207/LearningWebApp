package model;

public class MaterialsModel {
	private int materialID;
	private String title;
	private String filePath;
	private int classroomID;

	public MaterialsModel() {}

	public MaterialsModel(String title, String filePath, int classroomID) {
		super();
		this.title = title;
		this.filePath = filePath;
		this.classroomID = classroomID;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}
}

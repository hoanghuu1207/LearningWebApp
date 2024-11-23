package service;

import model.MaterialsModel;

import java.util.ArrayList;

public interface I_MaterialService {
    public ArrayList<MaterialsModel> getFilesByClassroomID(int classroomID);

    public void uploadFile(int classroomID, String fileName, String filePath);

    public int getMaterialAfterUploadFileAssignment(String fileName, String filePath);
}

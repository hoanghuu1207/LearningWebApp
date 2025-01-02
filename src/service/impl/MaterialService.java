package service.impl;

import dao.impl.MaterialDAO;
import model.MaterialsModel;
import service.I_MaterialService;

import java.util.ArrayList;

public class MaterialService implements I_MaterialService {
    private MaterialDAO dao = new MaterialDAO();
    @Override
    public ArrayList<MaterialsModel> getFilesByClassroomID(int classroomID) {
        return dao.getFilesByClassroomID(classroomID);
    }

    @Override
    public void uploadFile(int classroomID, String fileName, String filePath) {
        dao.uploadFile(classroomID, fileName, filePath);
    }

    @Override
    public int getMaterialAfterUploadFileAssignment(String fileName, String filePath) {
        return dao.getMaterialAfterUploadFileAssignment(fileName, filePath);
    }
}

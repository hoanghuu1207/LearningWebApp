package service.impl;

import dao.impl.ClassMessageDAO;
import model.ClassMessageModel;
import service.I_ClassMessageService;

import java.util.ArrayList;

public class ClassMessageService implements I_ClassMessageService {
    private ClassMessageDAO messageDAO = new ClassMessageDAO();
    @Override
    public ArrayList<ClassMessageModel> selectByClassId(int classid) {
        return messageDAO.selectAllByClassId(classid);
    }

    @Override
    public ClassMessageModel insertAndGetMessage(ClassMessageModel classMessageModel) {
        return messageDAO.insertAndGetMessage(classMessageModel);
    }
}

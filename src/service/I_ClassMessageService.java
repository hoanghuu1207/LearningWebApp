package service;

import model.ClassMessageModel;
import model.ClassroomsModel;

import java.util.ArrayList;

public interface I_ClassMessageService {
    public ArrayList<ClassMessageModel> selectByClassId(int classid);

    public ClassMessageModel insertAndGetMessage(ClassMessageModel classMessageModel);
}

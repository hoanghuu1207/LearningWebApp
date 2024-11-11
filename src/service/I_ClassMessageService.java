package service;

import model.ClassMessageModel;

import java.util.ArrayList;

public interface I_ClassMessageService {
    public ArrayList<ClassMessageModel> selectByClassId(int classid);
}

package service;

import model.ClassroomsModel;

public interface I_ClassMemberService {
    public int insertStudentIntoClass(int studentID, int classID);
    public void deleteStudentFromClass(int studentID, int classID);
}

package service;

import model.ClassroomsModel;

public interface I_ClassMemberService {
    public int insertStudentIntoClass(int studentID, int classID);
    public void deleteStudentFromClassRoleTeacher(int studentID, int classID);
    public void deleteStudentFromClassRoleStudent(int studentID, int classID);
}

package service.impl;

import dao.impl.StudentsClassroomsDAO;
import model.ClassroomsModel;
import model.StudentsClassroomsModel;
import service.I_ClassMemberService;

public class ClassMemberService implements I_ClassMemberService {
    private StudentsClassroomsDAO studentsClassroomsDAO = new StudentsClassroomsDAO();
    @Override
    public int insertStudentIntoClass(int studentID, int classID) {
        StudentsClassroomsModel studentsClassroomsModel = new StudentsClassroomsModel();
        studentsClassroomsModel.setStudentID(studentID);
        studentsClassroomsModel.setClassroomID(classID);

        return studentsClassroomsDAO.getIdAfterInsert(studentsClassroomsModel);
    }
}

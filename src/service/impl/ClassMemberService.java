package service.impl;

import ServerEndpoint.ClassMemberEndpoint;
import ServerEndpoint.StudentRemoveClassEndpoint;
import dao.impl.StudentsClassroomsDAO;
import dao.impl.UserDAO;
import model.ClassroomsModel;
import model.StudentsClassroomsModel;
import model.SubNotificationModel;
import service.I_ClassMemberService;

public class ClassMemberService implements I_ClassMemberService {
    private StudentsClassroomsDAO studentsClassroomsDAO = new StudentsClassroomsDAO();
    private NotificationService notificationService = new NotificationService();
    private UserDAO userDAO = new UserDAO();
    @Override
    public int insertStudentIntoClass(int studentID, int classID) {
        StudentsClassroomsModel studentsClassroomsModel = new StudentsClassroomsModel();
        studentsClassroomsModel.setStudentID(studentID);
        studentsClassroomsModel.setClassroomID(classID);

        return studentsClassroomsDAO.getIdAfterInsert(studentsClassroomsModel);
    }

    @Override
    public void deleteStudentFromClassRoleTeacher(int studentID, int classID) {
        StudentsClassroomsModel studentsClassroomsModel = new StudentsClassroomsModel();
        studentsClassroomsModel.setStudentID(studentID);
        studentsClassroomsModel.setClassroomID(classID);

        if(studentsClassroomsDAO.delete(studentsClassroomsModel) != 0){
            ClassMemberEndpoint.removeStudentFromClass(String.valueOf(classID), String.valueOf(studentID));

            SubNotificationModel subNotificationModel = userDAO.getSubNotificationWithClassroom(classID);

            notificationService.sendNotificationRemoveStudentFromClass(classID ,studentID, subNotificationModel.getUrl(), subNotificationModel.getContent());
        }
    }

    @Override
    public void deleteStudentFromClassRoleStudent(int studentID, int classID) {
        StudentsClassroomsModel studentsClassroomsModel = new StudentsClassroomsModel();
        studentsClassroomsModel.setStudentID(studentID);
        studentsClassroomsModel.setClassroomID(classID);

        if(studentsClassroomsDAO.delete(studentsClassroomsModel) != 0){
            StudentRemoveClassEndpoint.removeStudentFromClass(String.valueOf(studentID), String.valueOf(classID));
        }
    }
}

package service.impl;

import ServerEndpoint.NotificationEndpoint;
import dao.impl.ClassMessageDAO;
import dao.impl.NotificationDAO;
import dao.impl.StudentsClassroomsDAO;
import model.*;
import service.I_NotificationService;

import java.util.ArrayList;

public class NotificationService implements I_NotificationService {
    private StudentsClassroomsDAO studentsClassroomsDAO = new StudentsClassroomsDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();
    @Override
    public void sendNotificationPostArticle(int classroomID, int userID, String content, int messageID) {
        ArrayList<StudentsClassroomsModel> studentsClassroomsModels = studentsClassroomsDAO.selectByClassroomID(classroomID);

        studentsClassroomsModels.add(studentsClassroomsDAO.selectTeacherByClassroomID(classroomID));

        for(int i = 0 ; i < studentsClassroomsModels.size() ; i++){
            int userId = studentsClassroomsModels.get(i).getStudentID();
            if(userId != userID){
                NotificationModel notification = new NotificationModel();
                notification.setStatus(0);
                notification.setType("post");
                notification.setRelatedID(messageID);
                notification.setInformedID(userId);

                NotificationModel insertedNotification = notificationDAO.insertAndGetNotification(notification);

                NotificationEndpoint.sendNotificationToUser(String.valueOf(userId), "/class/detail?classID=" + classroomID, content, insertedNotification.getNotificationID());
            }
        }
    }

    @Override
    public void loadNotification(UserModel userModel) {

    }

    @Override
    public void sendNotificationAddStudentIntoClass(int classroomID, int studentID, String url, String content, int student_classroomId) {
        NotificationModel notification = new NotificationModel();
        notification.setStatus(0);
        notification.setType("add_to_class");
        notification.setRelatedID(student_classroomId);
        notification.setInformedID(studentID);

        NotificationModel insertedNotification = notificationDAO.insertAndGetNotification(notification);

        NotificationEndpoint.sendNotificationToUser(String.valueOf(studentID), url, content, insertedNotification.getNotificationID());
    }

    @Override
    public void sendNotificationRemoveStudentFromClass(int classroomID, int studentID, String url, String content) {
        NotificationModel notification = new NotificationModel();
        notification.setStatus(0);
        notification.setType("remove_from_class");
        notification.setRelatedID(classroomID);
        notification.setInformedID(studentID);

        NotificationModel insertedNotification = notificationDAO.insertAndGetNotificationOfRemoveStudentFromClass(notification);

        NotificationEndpoint.sendNotificationToUser(String.valueOf(studentID), url, content, insertedNotification.getNotificationID());
    }

    @Override
    public void sendNotificationAddAssignment(int assignmentID, int classroomID) {
        SubNotificationModel subNotificationModel = notificationDAO.subNotificationAddAssignment(classroomID);

        ArrayList<StudentsClassroomsModel> studentsClassroomsModels = studentsClassroomsDAO.selectByClassroomID(classroomID);

        for(StudentsClassroomsModel studentsClassroomsModel: studentsClassroomsModels){
            NotificationModel notification = new NotificationModel();
            notification.setStatus(0);
            notification.setType("assignment");
            notification.setRelatedID(assignmentID);
            notification.setInformedID(studentsClassroomsModel.getStudentID());

            NotificationModel insertedNotification = notificationDAO.insertAndGetNotificationOfAddAssignmentToClass(notification);

            NotificationEndpoint.sendNotificationToUser(String.valueOf(studentsClassroomsModel.getStudentID()), subNotificationModel.getUrl(), subNotificationModel.getContent(), insertedNotification.getNotificationID());
        }
    }
}

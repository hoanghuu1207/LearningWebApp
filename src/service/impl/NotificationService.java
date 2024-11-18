package service.impl;

import ServerEndpoint.NotificationEndpoint;
import dao.impl.ClassMessageDAO;
import dao.impl.NotificationDAO;
import dao.impl.StudentsClassroomsDAO;
import model.ClassMessageModel;
import model.NotificationModel;
import model.StudentsClassroomsModel;
import model.UserModel;
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
}

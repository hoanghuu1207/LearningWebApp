package service.impl;

import ServerEndpoint.NotificationEndpoint;
import dao.impl.ClassMessageDAO;
import dao.impl.StudentsClassroomsDAO;
import model.ClassMessageModel;
import model.StudentsClassroomsModel;
import model.UserModel;
import service.I_NotificationService;

import java.util.ArrayList;

public class NotificationService implements I_NotificationService {
    private StudentsClassroomsDAO studentsClassroomsDAO = new StudentsClassroomsDAO();
    @Override
    public void sendNotificationPostArticle(int classroomID, int userID, String content) {
        ArrayList<StudentsClassroomsModel> studentsClassroomsModels = studentsClassroomsDAO.selectByClassroomID(classroomID);

        studentsClassroomsModels.add(studentsClassroomsDAO.selectTeacherByClassroomID(classroomID));

        for(int i = 0 ; i < studentsClassroomsModels.size() ; i++){
            int userId = studentsClassroomsModels.get(i).getStudentID();
            if(userId != userID){


                NotificationEndpoint.sendNotificationToUser(String.valueOf(userId), "/class/detail?classID=" + classroomID, content);
            }
        }
    }

    @Override
    public void loadNotification(UserModel userModel) {

    }
}

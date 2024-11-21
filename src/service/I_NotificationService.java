package service;

import model.UserModel;

public interface I_NotificationService {
    void sendNotificationPostArticle(int classroomID, int userID, String content, int messageID);

    void loadNotification(UserModel userModel);

    void sendNotificationAddStudentIntoClass(int classroomID, int studentID, String url, String content, int student_classroomId);

    void sendNotificationRemoveStudentFromClass(int classroomID, int studentID, String url, String content);
}

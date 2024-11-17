package service;

import model.UserModel;

public interface I_NotificationService {
    void sendNotificationPostArticle(int classroomID, int userID, String content);

    void loadNotification(UserModel userModel);
}

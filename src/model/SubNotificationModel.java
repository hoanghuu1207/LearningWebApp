package model;

public class SubNotificationModel {
    private int notificationID;
    private String url;
    private String content;
    private int status;

    public SubNotificationModel(){}

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

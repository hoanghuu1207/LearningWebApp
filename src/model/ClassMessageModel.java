package model;

import java.sql.Timestamp;

public class ClassMessageModel {
    private int messageID;
    private String content;
    private Timestamp createdAt;
    private int userID;
    private int parentMessageID;  // nullable for root messages
    private int classroomID;
    private String senderName;

    // Constructor, getters, and setters
    public ClassMessageModel(int messageID, String content, Timestamp createdAt, int userID, int parentMessageID, int classroomID, String senderName) {
        this.messageID = messageID;
        this.content = content;
        this.createdAt = createdAt;
        this.userID = userID;
        this.parentMessageID = parentMessageID;
        this.classroomID = classroomID;
        this.senderName = senderName;
    }

    public ClassMessageModel(){}

    public Integer getParentMessageID() {
        return parentMessageID;
    }

    public void setParentMessageID(Integer parentMessageID) {
        this.parentMessageID = parentMessageID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setParentMessageID(int parentMessageID) {
        this.parentMessageID = parentMessageID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}

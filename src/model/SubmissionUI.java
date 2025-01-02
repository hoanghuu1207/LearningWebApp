package model;

import java.sql.Timestamp;

public class SubmissionUI {
    private String sendername;
    private Timestamp submissionDate;
    private int materialID;
    private String titleFile;
    private String filePath;

    public SubmissionUI() {
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getTitleFile() {
        return titleFile;
    }

    public void setTitleFile(String titleFile) {
        this.titleFile = titleFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

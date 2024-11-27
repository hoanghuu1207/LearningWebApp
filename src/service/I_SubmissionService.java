package service;

import model.SubmissionUI;
import model.SubmissionsModel;

public interface I_SubmissionService {
    public int insertSubmission(SubmissionsModel submissionsModel);
    public void getSubmissionAssignment(int userID, int assignmentID);
}

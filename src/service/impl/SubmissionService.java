package service.impl;

import ServerEndpoint.TeacherAssignmentEndpoint;
import dao.impl.SubmissionDAO;
import model.SubmissionUI;
import model.SubmissionsModel;
import service.I_SubmissionService;

import java.util.ArrayList;

public class SubmissionService implements I_SubmissionService {
    private SubmissionDAO submissionDAO = new SubmissionDAO();
    @Override
    public int insertSubmission(SubmissionsModel submissionsModel) {
        return submissionDAO.insert(submissionsModel);
    }

    @Override
    public void getSubmissionAssignment(int userID, int assignmentID) {
        ArrayList<SubmissionUI> submissionUIs = submissionDAO.getSubmissionAssignment(assignmentID);

        TeacherAssignmentEndpoint.sendSubmission(String.valueOf(userID), submissionUIs);
    }
}

package service.impl;

import ServerEndpoint.ClassAssignmentEndpoint;
import dao.impl.AssignmentDAO;
import model.AssignmentsModel;
import model.SubmissionsModel;
import service.I_AssignmentService;

import java.util.ArrayList;
import java.util.Map;

public class AssignmentService implements I_AssignmentService {
    private AssignmentDAO assignmentDAO = new AssignmentDAO();

    @Override
    public ArrayList<AssignmentsModel> getNotSubmittedAssignmentsOnTime(int userID, int classroomID) {
        return assignmentDAO.getNotSubmittedAssignmentsOnTime(userID, classroomID);
    }

    @Override
    public Map<AssignmentsModel, SubmissionsModel> getSubmittedAssignments(int userID, int classroomID) {
        return assignmentDAO.getSubmittedAssignments(userID, classroomID);
    }

    @Override
    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID) {
        return assignmentDAO.getOverdueAssignments(userID, classroomID);
    }

    @Override
    public ArrayList<AssignmentsModel> teacherGetAssignments(int classroomID) {
        return assignmentDAO.teacherGetAssignments(classroomID);
    }

    @Override
    public int getIdInsertAssignment(AssignmentsModel assignmentsModel) {
        return assignmentDAO.getIdAfterInsertAssignment(assignmentsModel);
    }

    @Override
    public void changeAssignmentLoop(int userID, int classroomID) {
        ArrayList<AssignmentsModel> notSubmitted = assignmentDAO.getNotSubmittedAssignmentsOnTime(userID, classroomID);
        Map<AssignmentsModel, SubmissionsModel> submitted = assignmentDAO.getSubmittedAssignments(userID, classroomID);
        ArrayList<AssignmentsModel> overdue = assignmentDAO.getOverdueAssignments(userID, classroomID);

        ClassAssignmentEndpoint.sendAssignment(String.valueOf(userID), notSubmitted, submitted, overdue);
    }
}

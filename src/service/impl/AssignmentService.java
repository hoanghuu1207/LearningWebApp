package service.impl;

import dao.impl.AssignmentDAO;
import model.AssignmentsModel;
import service.I_AssignmentService;

import java.util.ArrayList;

public class AssignmentService implements I_AssignmentService {
    private AssignmentDAO assignmentDAO = new AssignmentDAO();

    @Override
    public ArrayList<AssignmentsModel> getNotSubmittedAssignmentsOnTime(int userID, int classroomID) {
        return assignmentDAO.getNotSubmittedAssignmentsOnTime(userID, classroomID);
    }

    @Override
    public ArrayList<AssignmentsModel> getSubmittedAssignments(int userID, int classroomID) {
        return assignmentDAO.getSubmittedAssignments(userID, classroomID);
    }

    @Override
    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID) {
        return assignmentDAO.getOverdueAssignments(userID, classroomID);
    }
}

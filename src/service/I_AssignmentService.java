package service;

import model.AssignmentsModel;

import java.util.ArrayList;

public interface I_AssignmentService {
    public ArrayList<AssignmentsModel> getNotSubmittedAssignmentsOnTime(int userID, int classroomID);

    public ArrayList<AssignmentsModel> getSubmittedAssignments(int userID, int classroomID);

    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID);
}

package service;

import model.AssignmentsModel;
import model.SubmissionsModel;

import java.util.ArrayList;
import java.util.Map;

public interface I_AssignmentService {
    public ArrayList<AssignmentsModel> getNotSubmittedAssignmentsOnTime(int userID, int classroomID);

    public Map<AssignmentsModel, SubmissionsModel> getSubmittedAssignments(int userID, int classroomID);

    public ArrayList<AssignmentsModel> getOverdueAssignments(int userID, int classroomID);

    public ArrayList<AssignmentsModel> teacherGetAssignments(int classroomID);

    public int getIdInsertAssignment(AssignmentsModel assignmentsModel);

    public void changeAssignmentLoop(int userID, int classroomID);
}

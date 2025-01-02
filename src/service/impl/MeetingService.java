package service.impl;

import dao.impl.MeetingDAO;
import model.MeetingsModel;
import service.I_MeetingService;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MeetingService implements I_MeetingService {
    private MeetingDAO meetingDAO = new MeetingDAO();
    @Override
    public ArrayList<MeetingsModel> getUpcomingMeetings(int id) {
        return meetingDAO.getUpcomingMeetings(id);
    }

    @Override
    public ArrayList<MeetingsModel> getPastMeetings(int id) {
        return meetingDAO.getPastMeetings(id);
    }

    @Override
    public ArrayList<MeetingsModel> getCanceledMeetings(int id) {
        return meetingDAO.getCanceledMeetings(id);
    }

    @Override
    public ArrayList<MeetingsModel> getOngoingMeetings(int id) {
        return meetingDAO.getOngoingMeetings(id);
    }

    @Override
    public MeetingsModel selectById(int id) {
        return meetingDAO.selectById(id);
    }

    @Override
    public int createNewMeeting(String title, int classroomID) {
        return meetingDAO.createNewMeeting(title, classroomID);
    }

    @Override
    public int createNewSchedule_Meeting(String title, Timestamp startTime, int classroomID) {
        return meetingDAO.createNewSchedule_Meeting(title, startTime, classroomID);
    }

    @Override
    public void ModifierEndMeeting(int meetingID) {
        meetingDAO.ModifierEndMeeting(meetingID);
    }

    @Override
    public void cancelMeeting(int meetingID) {
        meetingDAO.cancelMeeting(meetingID);
    }
}
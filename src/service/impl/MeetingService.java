package service.impl;

import dao.impl.MeetingDAO;
import model.MeetingsModel;
import service.I_MeetingService;

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
    public MeetingsModel selectById(int id) {
        return meetingDAO.selectById(id);
    }
}

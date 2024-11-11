package service;

import model.MeetingsModel;

import java.util.ArrayList;

public interface I_MeetingService {
    public ArrayList<MeetingsModel> getUpcomingMeetings(int id);

    public ArrayList<MeetingsModel> getPastMeetings(int id);

    public ArrayList<MeetingsModel> getCanceledMeetings(int id);

    public MeetingsModel selectById(int id);
}

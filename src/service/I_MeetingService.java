package service;

import model.MeetingsModel;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface I_MeetingService {
    public ArrayList<MeetingsModel> getUpcomingMeetings(int id);

    public ArrayList<MeetingsModel> getPastMeetings(int id);

    public ArrayList<MeetingsModel> getCanceledMeetings(int id);

    public ArrayList<MeetingsModel> getOngoingMeetings(int id);

    public MeetingsModel selectById(int id);

    public int createNewMeeting(String title, int classroomID);

    public int createNewSchedule_Meeting(String title, Timestamp startTime, int classroomID );

    public void ModifierEndMeeting(int meetingID);

    public void cancelMeeting(int meetingID);
}

package service;

import model.ScheduleModel;

import java.util.ArrayList;

public interface I_ScheduleService {
    public ScheduleModel selectByMeetingID(int meetingID);

    public ArrayList<ScheduleModel> getUpcomingSchedules(int classroomID);

    public void createSchedule(ScheduleModel schedule);
}

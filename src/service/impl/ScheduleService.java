package service.impl;

import dao.impl.ScheduleDAO;
import model.ScheduleModel;
import service.I_ScheduleService;

import java.util.ArrayList;

public class ScheduleService implements I_ScheduleService {
    private ScheduleDAO dao = new ScheduleDAO();
    @Override
    public ScheduleModel selectByMeetingID(int meetingID) {
        return dao.selectByMeetingID(meetingID);
    }

    @Override
    public ArrayList<ScheduleModel> getUpcomingSchedules(int classroomID) {
        return dao.getUpcomingSchedules(classroomID);
    }
}

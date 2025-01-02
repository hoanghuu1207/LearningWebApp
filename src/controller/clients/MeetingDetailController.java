package controller.clients;

import model.ClassroomsModel;
import model.MeetingsModel;
import model.ScheduleModel;
import model.UserModel;
import service.impl.ClassroomsService;
import service.impl.MeetingService;
import service.impl.ScheduleService;
import service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/meetingDetail"})
public class MeetingDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();
    private ScheduleService scheduleService = new ScheduleService();
    private UserService userService = new UserService();
    private ClassroomsService classroomsService = new ClassroomsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int meetingID = Integer.parseInt(request.getParameter("id"));
        int type = Integer.parseInt(request.getParameter("type"));
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        ScheduleModel scheduleModel = scheduleService.selectByMeetingID(meetingID);
        ClassroomsModel classroom = classroomsService.selectById(classroomID);
        if (type == 1)
        {
            ArrayList<UserModel> teachers = userService.getTeachersByClassId(classroomID);
            request.setAttribute("teachers", teachers);
            if (scheduleModel != null)
                request.setAttribute("schedule", scheduleModel);
        }
        if (type == 2)
        {
            //
        }
        if(type == 3)
        {
            // Ko
        }
        if(type == 4)
        {
            ArrayList<UserModel> teachers = userService.getTeachersByClassId(classroomID);
            request.setAttribute("teachers", teachers);
            if (scheduleModel != null)
                request.setAttribute("schedule", scheduleModel);
        }

        MeetingsModel meeting = meetingService.selectById(meetingID);
        request.setAttribute("meeting", meeting);
        request.setAttribute("classroom", classroom);
        request.setAttribute("type", type);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/meetingDetail.jsp");
        dispatcher.forward(request, response);
    }
}

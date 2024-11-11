package controller.clients;

import model.ScheduleModel;
import service.impl.ScheduleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/teacher/schedules"})
public class Teacher_ScheduleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScheduleService scheduleService = new ScheduleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));

        ArrayList<ScheduleModel> upcomingSchedules = scheduleService.getUpcomingSchedules(classroomID);

        request.setAttribute("upcomingSchedules", upcomingSchedules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/teacher_schedules.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startTime = request.getParameter("datetime");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));

        //Meeting newMeeting = new Meeting(title, Timestamp.valueOf(startTime), content, classroomID);
        //ScheduleDAO.createMeeting(newMeeting);

        //response.sendRedirect("MeetingScheduleServlet?classroomID=" + classroomID);
    }
}

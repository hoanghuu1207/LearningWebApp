package controller.clients;

import model.MeetingsModel;
import service.impl.MeetingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/meetings"})
public class ClassMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        ArrayList<MeetingsModel> upcomingMeetings = meetingService.getUpcomingMeetings(classroomID);
        ArrayList<MeetingsModel> pastMeetings = meetingService.getPastMeetings(classroomID);
        ArrayList<MeetingsModel> canceledMeetings = meetingService.getCanceledMeetings(classroomID);

        request.setAttribute("upcomingMeetings", upcomingMeetings);
        request.setAttribute("pastMeetings", pastMeetings);
        request.setAttribute("canceledMeetings", canceledMeetings);
        request.setAttribute("classroomID", classroomID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_meetings.jsp");
        dispatcher.forward(request, response);
    }
}

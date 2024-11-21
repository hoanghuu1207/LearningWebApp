package controller.clients;

import model.ClassroomsModel;
import model.MeetingsModel;
import model.UserModel;
import service.impl.ClassroomsService;
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
    private ClassroomsService classroomsService = new ClassroomsService();
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        UserModel user = (UserModel) request.getAttribute("user");

        int studentId = user.getUserID();

        ClassroomsModel classroom = classroomsService.selectByIdAndStudentID(classroomID, studentId);

        ArrayList<MeetingsModel> upcomingMeetings = meetingService.getUpcomingMeetings(classroomID);
        ArrayList<MeetingsModel> pastMeetings = meetingService.getPastMeetings(classroomID);
        ArrayList<MeetingsModel> canceledMeetings = meetingService.getCanceledMeetings(classroomID);
        ArrayList<MeetingsModel> ongoingMeetings = meetingService.getOngoingMeetings(classroomID);
for(MeetingsModel m : ongoingMeetings){
    System.out.println(m.getMeetingID());
    System.out.println(m.getStartTime());
}
        request.setAttribute("upcomingMeetings", upcomingMeetings);
        request.setAttribute("pastMeetings", pastMeetings);
        request.setAttribute("canceledMeetings", canceledMeetings);
        request.setAttribute("ongoingMeetings", ongoingMeetings);
        request.setAttribute("classroom", classroom);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_meetings.jsp");
        dispatcher.forward(request, response);
    }
}

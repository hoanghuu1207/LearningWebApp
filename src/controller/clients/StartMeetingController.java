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

@WebServlet(urlPatterns = {"/StartMeeting"})
public class StartMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoEnabled = request.getParameter("videoEnabled");
        String audioEnabled = request.getParameter("audioEnabled");
        String isPermittedvideo = request.getParameter("isPermittedvideo");
        String isPermittedaudio = request.getParameter("isPermittedaudio");
        int meetingId = Integer.parseInt(request.getParameter("meetingId"));
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (meetingId == 0){
//            String meetingTitle = request.getParameter("meetingTitle");
//            if (meetingTitle == null || meetingTitle.trim().isEmpty()) {
//                request.setAttribute("error", "Meeting title is required!");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/prepareMeeting?classroomID=" + classroomID);
//                dispatcher.forward(request, response);
//                return;
//            }
//            meetingId = meetingService.createNewMeeting(meetingTitle, classroomID);
        }

        System.out.println(isPermittedvideo);
        System.out.println(isPermittedaudio);
        request.setAttribute("videoEnabled", videoEnabled);
        request.setAttribute("audioEnabled", audioEnabled);
        request.setAttribute("isPermittedvideo", isPermittedvideo);
        request.setAttribute("isPermittedaudio", isPermittedaudio);
        request.setAttribute("meetingId", meetingId);
        request.setAttribute("classroomID", classroomID);
        request.setAttribute("userId", userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/meeting.jsp");
        dispatcher.forward(request, response);
    }
}
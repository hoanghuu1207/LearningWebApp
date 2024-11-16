package controller.clients;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoEnabled = request.getParameter("videoEnabled");
        String audioEnabled = request.getParameter("audioEnabled");
        String isPermittedvideo = request.getParameter("isPermittedvideo");
        String isPermittedaudio = request.getParameter("isPermittedaudio");
        String meetingId = request.getParameter("meetingId");

        System.out.println(isPermittedvideo);
        System.out.println(isPermittedaudio);
        request.setAttribute("videoEnabled", videoEnabled);
        request.setAttribute("audioEnabled", audioEnabled);
        request.setAttribute("isPermittedvideo", isPermittedvideo);
        request.setAttribute("isPermittedaudio", isPermittedaudio);
        request.setAttribute("meetingId", meetingId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/meeting.jsp");
        dispatcher.forward(request, response);
    }
}

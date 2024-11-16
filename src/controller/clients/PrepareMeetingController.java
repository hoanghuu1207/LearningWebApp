package controller.clients;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/prepareMeeting"})
public class PrepareMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String meetingId = UUID.randomUUID().toString();
        System.out.println("meetingId: " + meetingId);
        request.setAttribute("meetingId", meetingId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/prepare_meeting.jsp");
        dispatcher.forward(request, response);
    }
}

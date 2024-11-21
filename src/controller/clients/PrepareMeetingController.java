package controller.clients;

import model.UserModel;

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
        //String meetingId = UUID.randomUUID().toString();
        int meetingId = 0;
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        UserModel user = (UserModel) request.getAttribute("user");
        int userId = user.getUserID();

        System.out.println("meetingId: " + meetingId);
        request.setAttribute("meetingId", meetingId);
        request.setAttribute("classroomID", classroomID);
        request.setAttribute("userId", userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/prepare_meeting.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int meetingID = Integer.parseInt(request.getParameter("meetingID"));
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        UserModel user = (UserModel) request.getAttribute("user");
        int userId = user.getUserID();

        request.setAttribute("meetingId", meetingID);
        request.setAttribute("classroomID", classroomID);
        request.setAttribute("userId", userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/prepare_meeting.jsp");
        dispatcher.forward(request, response);
    }
}

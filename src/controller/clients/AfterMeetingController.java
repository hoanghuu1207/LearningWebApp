package controller.clients;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/afterMeeting"})
public class AfterMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classID = Integer.parseInt(request.getParameter("classID"));
        request.setAttribute("classID", classID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/aftermeeting.jsp");
        dispatcher.forward(request, response);
    }
}

package controller.clients;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassroomsModel;
import service.impl.ClassroomsService;
@WebServlet(urlPatterns = {"/class/detail"})
public class ClassDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassroomsService classroomsService = new ClassroomsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classID = request.getParameter("classID");
        ClassroomsModel classroom = classroomsService.selectById(Integer.parseInt(classID));

        if (classroom != null) {
            request.setAttribute("classroom", classroom);
            request.getRequestDispatcher("/views/clients/pages/class/class_detail.jsp").forward(request, response);
        } else {
            response.sendRedirect("/views/clients/pages/class/class.jsp");
        }
    }
}

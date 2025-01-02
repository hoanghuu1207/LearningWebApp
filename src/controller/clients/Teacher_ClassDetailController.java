package controller.clients;

import model.ClassMessageModel;
import model.ClassroomsModel;
import model.UserModel;
import service.impl.ClassMessageService;
import service.impl.ClassroomsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/teacher/class/detail"})
public class Teacher_ClassDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassroomsService classroomsService = new ClassroomsService();
    private ClassMessageService messageService = new ClassMessageService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classID = request.getParameter("classID");

        UserModel user = (UserModel) request.getAttribute("user");

        int teacherId = user.getUserID();
        int userRoleID = user.getRoleID();
        ClassroomsModel classroom = classroomsService.selectByIdAndTeacherID(Integer.parseInt(classID), teacherId);

        if (classroom != null) {
            request.setAttribute("classroom", classroom);
            request.getSession().setAttribute("classID", classID);

            ArrayList<ClassMessageModel> messages = messageService.selectByClassId(Integer.parseInt(classID));

            request.setAttribute("messages", messages);
            request.setAttribute("roleID", userRoleID);

            request.getRequestDispatcher("/views/clients/pages/class/class_detail.jsp").forward(request, response);
        } else {
            response.sendRedirect("/teacher/class");
        }
    }
}

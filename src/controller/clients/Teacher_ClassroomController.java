package controller.clients;

import model.ClassroomsModel;
import model.UserModel;
import service.impl.ClassroomsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/teacher/class"})
public class Teacher_ClassroomController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassroomsService classroomsService = new ClassroomsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        UserModel user = (UserModel) request.getAttribute("user");

        int teacherId = user.getUserID();
        // Retrieve the list of classes for the teacher
        ArrayList<ClassroomsModel> teacherClasses = classroomsService.getClassroomsByTeacherId(teacherId);

        // Set data in request attributes for the JSP page
        request.setAttribute("teacherId", teacherId);
        request.setAttribute("teacherClasses", teacherClasses);

        // Forward to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/teacherClasses.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String title = req.getParameter("title");
        UserModel user = (UserModel) req.getAttribute("user");
        int teacherId = user.getUserID();

        ClassroomsModel classroomsModel = new ClassroomsModel();
        classroomsModel.setTitle(title);
        classroomsModel.setTeacherID(teacherId);

        classroomsService.insertClassroom(classroomsModel);

        resp.sendRedirect("/teacher/class");
    }
}

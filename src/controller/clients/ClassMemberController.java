package controller.clients;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassroomsModel;
import model.UserModel;
import service.impl.ClassroomsService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/class_members"})
public class ClassMemberController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClassroomsService classroomsService = new ClassroomsService();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");
        UserModel user = (UserModel) request.getAttribute("user");

        int studentId = user.getUserID();

        ClassroomsModel classroom = classroomsService.selectByIdAndStudentID(Integer.parseInt(classId), studentId);

        ArrayList<UserModel> teachers = userService.getTeachersByClassId(Integer.parseInt(classId));
        ArrayList<UserModel> students = userService.getStudentsByClassId(Integer.parseInt(classId));

        request.setAttribute("teachers", teachers);
        request.setAttribute("students", students);
        request.setAttribute("classroom", classroom);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_members.jsp");
        dispatcher.forward(request, response);
    }
}

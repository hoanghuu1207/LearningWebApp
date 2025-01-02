package controller.clients;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.ClassroomDAO;
import dao.impl.NotificationDAO;
import model.ClassMessageModel;
import model.ClassroomsModel;
import model.SubNotificationModel;
import model.UserModel;
import service.impl.ClassMemberService;
import service.impl.ClassroomsService;
import service.impl.NotificationService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/teacher/class_members"})
public class Teacher_ClassMemberController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
    private ClassroomsService classroomsService = new ClassroomsService();
    private ClassMemberService classMemberService = new ClassMemberService();
    private ClassroomDAO classroomDAO = new ClassroomDAO();
    private NotificationService notificationService = new NotificationService();
    private NotificationDAO notificationDAO = new NotificationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");

        UserModel user = (UserModel) request.getAttribute("user");

        int teacherId = user.getUserID();

        ClassroomsModel classroom = classroomsService.selectByIdAndTeacherID(Integer.parseInt(classId), teacherId);

        if (classroom != null) {
            request.setAttribute("classroom", classroom);
            request.getSession().setAttribute("classID", classId);

            ArrayList<UserModel> teachers = userService.getTeachersByClassId(Integer.parseInt(classId));
            ArrayList<UserModel> students = userService.getStudentsByClassId(Integer.parseInt(classId));
            ArrayList<UserModel> studentsSearch = userService.getStudentsOutofClassAndExceptTeacher(Integer.parseInt(classId));

            request.setAttribute("teachers", teachers);
            request.setAttribute("students", students);
            request.setAttribute("studentsSearch", studentsSearch);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_members.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/teacher/class");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedCheckboxes = req.getParameterValues("checkbox");

        if (selectedCheckboxes != null) {
            int classID = Integer.parseInt((String) req.getSession().getAttribute("classID"));
            for (String value : selectedCheckboxes) {
                int student_classroomId =  classMemberService.insertStudentIntoClass(Integer.parseInt(value), classID);
                SubNotificationModel subNotificationModel = notificationDAO.getSubNotificationWithStudent_ClassroomId(student_classroomId);

                notificationService.sendNotificationAddStudentIntoClass(classID, Integer.parseInt(value), subNotificationModel.getUrl(), subNotificationModel.getContent(), student_classroomId);
            }
        } else {
            System.out.println("No checkboxes were selected.");
        }
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}

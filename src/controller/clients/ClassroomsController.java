package controller.clients;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.ClassroomDAO;
import model.ClassroomsModel;
import service.impl.ClassroomsService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/class"})
public class ClassroomsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassroomsService classroomsService = new ClassroomsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int studentId = Integer.parseInt(request.getParameter("studentId")); // Lấy studentId từ request
		int studentId = 3;
        
        // Lấy danh sách classroom mà học sinh tham gia
        ArrayList<ClassroomsModel> classrooms = classroomsService.getClassroomsByStudentId(studentId);
        
        for (ClassroomsModel classroom : classrooms) {
            System.out.println("Classroom ID: " + classroom.getClassroomID());
            System.out.println("Title: " + classroom.getTitle());
            System.out.println("Teacher ID: " + classroom.getTeacherID());
        }
        // Gửi danh sách classroom tới JSP
        request.setAttribute("classrooms", classrooms);
        
        // Chuyển tiếp yêu cầu tới trang JSP để hiển thị
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class.jsp");
        dispatcher.forward(request, response);
    }
}

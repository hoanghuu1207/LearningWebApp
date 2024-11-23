package controller.clients;

import model.AssignmentsModel;
import model.ClassroomsModel;
import model.UserModel;
import service.impl.AssignmentService;
import service.impl.ClassroomsService;
import service.impl.MaterialService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/teacher/class_assignments"})
@MultipartConfig
public class Teacher_AssignmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AssignmentService assignmentService = new AssignmentService();
    private ClassroomsService classroomsService = new ClassroomsService();
    private MaterialService materialService = new MaterialService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));

        UserModel user = (UserModel) request.getAttribute("user");

        int teacherId = user.getUserID();

        ClassroomsModel classroom = classroomsService.selectByIdAndTeacherID(classroomID, teacherId);

        if (classroom != null) {
            request.setAttribute("classroom", classroom);
            request.getSession().setAttribute("classID", classroomID);

            ArrayList<AssignmentsModel> assignments = assignmentService.teacherGetAssignments(classroomID);

            request.setAttribute("assignments", assignments);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/teacher_assignments.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/teacher/class");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        int classroomID = Integer.parseInt(req.getParameter("classID"));
        String datetime = req.getParameter("datetime");
        if (datetime != null && !datetime.isEmpty()) {
            datetime = datetime.replace('T', ' ') + ":00"; // Thêm giây vào cuối
        }
        Timestamp time = Timestamp.valueOf(datetime);
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        String classroomUploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + classroomID;
        File classroomDir = new File(classroomUploadPath);
        if (!classroomDir.exists()) {
            classroomDir.mkdirs(); // Create classroom-specific directory if it doesn't exist
        }

        String uploadPath = classroomUploadPath + File.separator + fileName;
        filePart.write(uploadPath);

        AssignmentsModel assignmentsModel = new AssignmentsModel();
        assignmentsModel.setTitle(title);
        assignmentsModel.setDescription(description);
        assignmentsModel.setEndTime(time);
        assignmentsModel.setClassroomID(classroomID);
        assignmentsModel.setMaterialID(materialService.getMaterialAfterUploadFileAssignment(fileName, uploadPath));

        int assignmentId = assignmentService.getIdInsertAssignment(assignmentsModel);

        // notification assignment

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}

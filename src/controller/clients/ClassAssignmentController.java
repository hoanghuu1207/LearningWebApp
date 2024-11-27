package controller.clients;

import model.AssignmentsModel;
import model.ClassroomsModel;
import model.SubmissionsModel;
import model.UserModel;
import service.impl.AssignmentService;
import service.impl.ClassroomsService;
import service.impl.MaterialService;
import service.impl.SubmissionService;

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
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = {"/class_assignments"})
@MultipartConfig
public class ClassAssignmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AssignmentService assignmentService = new AssignmentService();
    private ClassroomsService classroomsService = new ClassroomsService();
    private MaterialService materialService = new MaterialService();
    private SubmissionService submissionService = new SubmissionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));

        UserModel user = (UserModel) request.getAttribute("user");

        int studentId = user.getUserID();

        ClassroomsModel classroom = classroomsService.selectByIdAndStudentID(classroomID, studentId);

        if (classroom != null) {
            request.setAttribute("classroom", classroom);
            request.getSession().setAttribute("classID", classroomID);

            ArrayList<AssignmentsModel> notSubmitted = assignmentService.getNotSubmittedAssignmentsOnTime(studentId, classroomID);
            Map<AssignmentsModel, SubmissionsModel> submitted = assignmentService.getSubmittedAssignments(studentId, classroomID);
            ArrayList<AssignmentsModel> overdue = assignmentService.getOverdueAssignments(studentId, classroomID);

            request.setAttribute("notSubmitted", notSubmitted);
            request.setAttribute("submitted", submitted);
            request.setAttribute("overdue", overdue);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_assignments.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/class");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        int classroomID = Integer.parseInt(req.getParameter("classID"));
        int assignmentID = Integer.parseInt(req.getParameter("assignmentID"));

        UserModel user = (UserModel) req.getAttribute("user");

        int studentId = user.getUserID();

        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        String classroomUploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + classroomID;
        File classroomDir = new File(classroomUploadPath);
        if (!classroomDir.exists()) {
            classroomDir.mkdirs(); // Create classroom-specific directory if it doesn't exist
        }

        String uploadPath = classroomUploadPath + File.separator + fileName;
        filePart.write(uploadPath);

        SubmissionsModel submissionsModel = new SubmissionsModel();
        submissionsModel.setAssignmentID(assignmentID);
        submissionsModel.setStudentID(studentId);
        submissionsModel.setMaterialID(materialService.getMaterialAfterUploadFileAssignment(fileName, uploadPath));

        submissionService.insertSubmission(submissionsModel);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}

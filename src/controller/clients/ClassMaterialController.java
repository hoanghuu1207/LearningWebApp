package controller.clients;

import model.ClassroomsModel;
import model.MaterialsModel;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/materials", "/downloadFile"})
@MultipartConfig
public class ClassMaterialController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MaterialService materialService = new MaterialService();
    private ClassroomsService classroomsService = new ClassroomsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/materials")) {
            //System.out.println("abc111");
            int classroomID = Integer.parseInt(request.getParameter("classroomID"));
            ClassroomsModel classroomsModel = classroomsService.selectById(classroomID);
            ArrayList<MaterialsModel> materials = materialService.getFilesByClassroomID(classroomID);
            for(MaterialsModel m : materials) {
                String encodedFilePath = URLEncoder.encode(m.getFilePath(), "UTF-8");
                m.setFilePath(encodedFilePath);
            }
            for(MaterialsModel m : materials) {
                System.out.println(m.getFilePath());
            }
            request.setAttribute("materials", materials);
            request.setAttribute("classroom", classroomsModel);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/class_materials.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("/downloadFile")) {
            System.out.println("abc");
            String filePath = URLDecoder.decode(request.getParameter("filePath"), "UTF-8");
            // Handle file download
            File file = new File(filePath);

            if (file.exists()) {
                response.setContentType("application/octet-stream");
                String encodedFileName = URLEncoder.encode(file.getName(), "UTF-8").replace("+", "%20");
                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomID = Integer.parseInt(request.getParameter("classroomID"));
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        // Define the file path where the file will be saved
//        String uploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + fileName;
//
//        File fileSaveDir = new File(getServletContext().getRealPath("/") + "uploads");
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
//        }
//
//        System.out.println(uploadPath);
//        filePart.write(uploadPath);


        String classroomUploadPath = getServletContext().getRealPath("/") + "uploads" + File.separator + classroomID;
        File classroomDir = new File(classroomUploadPath);
        if (!classroomDir.exists()) {
            classroomDir.mkdirs(); // Create classroom-specific directory if it doesn't exist
        }

        String uploadPath = classroomUploadPath + File.separator + fileName;
        filePart.write(uploadPath);
        // Save the file path in the database
        materialService.uploadFile(classroomID, fileName, uploadPath);

        response.sendRedirect(request.getContextPath() + "/materials?classroomID=" + classroomID);
    }
}

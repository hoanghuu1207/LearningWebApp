package controller.clients;

import model.ClassMessageModel;
import service.impl.ClassMessageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/class_post"})
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClassMessageService messageService = new ClassMessageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classroomId = request.getParameter("classroomID");
        ArrayList<ClassMessageModel> messages = messageService.selectByClassId(Integer.parseInt(classroomId));
        request.setAttribute("messages", messages);
//        for (ClassMessage message : messages) {
//            System.out.println("MesageID: " + message.getMessageID());
//            System.out.println("Name: " + message.getSenderName());
//            System.out.println("Content: " + message.getContent());
//            System.out.println("Content: " + message.getParentMessageID());
//            System.out.println("Time: " + message.getCreatedAt());
//        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/messages.jsp");
        dispatcher.forward(request, response);
    }


    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String classroomId = request.getParameter("classroomID");
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/post.jsp");
//        dispatcher.forward(request, response);
//    }
}
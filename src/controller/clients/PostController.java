package controller.clients;

import ServerEndpoint.PostEndpoint;
import model.ClassMessageModel;
import model.UserModel;
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

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String classroomId = request.getParameter("classroomID");
//        ArrayList<ClassMessageModel> messages = messageService.selectByClassId(Integer.parseInt(classroomId));
//
//        request.setAttribute("messages", messages);
//        for (ClassMessageModel message : messages) {
//            System.out.println("MesageID: " + message.getMessageID());
//            System.out.println("Name: " + message.getSenderName());
//            System.out.println("Content: " + message.getContent());
//            System.out.println("ParrentMessageID: " + message.getParentMessageID());
//            System.out.println("Time: " + message.getCreatedAt());
//            System.out.println("UserID: " + message.getUserID());
//        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clients/pages/class/messages.jsp");
//        dispatcher.forward(request, response);
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String content = (String) req.getParameter("post");
        int classroomID = Integer.parseInt((String) req.getSession().getAttribute("classID"));
        UserModel user = (UserModel) req.getAttribute("user");
        int userId = user.getUserID();

        System.out.println(content);
        System.out.println(classroomID);
        System.out.println(userId);

        ClassMessageModel classMessageModel = new ClassMessageModel();
        classMessageModel.setContent(content);
        classMessageModel.setUserID(userId);
        classMessageModel.setParentMessageID(0); // Đổi parentMessageID khi làm reply
        classMessageModel.setClassroomID(classroomID);

        ClassMessageModel insertedMessage = messageService.insertAndGetMessage(classMessageModel);

        PostEndpoint.sendMessageToClass(String.valueOf(classroomID), insertedMessage);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}
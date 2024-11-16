package controller.admin;

import model.ClassroomsModel;
import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/home/*" })
public class HomeController extends HttpServlet {
    private I_UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<UserModel> users = userService.getAllUsers();

        req.setAttribute("users", users);

        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/home/index.jsp");
        dispactcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String[] arrOfStr = pathInfo.split("/");
            int userId = Integer.parseInt(arrOfStr[1]);
            int roleId = Integer.parseInt(arrOfStr[2]);

            resp.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JSONObject obj = new JSONObject();

            obj.put("code", 200);

            if(roleId == 2){
                if(!userService.updateUserToStudent(userId)) return;
                obj.put("role", "Student");
                obj.put("role_id", 3);
                obj.put("remove_class", "active");
                obj.put("add_class", "waiting");
            }else if(roleId == 3){
                if(!userService.updateUserToTeacher(userId)) return;
                obj.put("role", "Teacher");
                obj.put("role_id", 2);
                obj.put("remove_class", "waiting");
                obj.put("add_class", "active");
            }else return;

            out.print(obj);
        }
    }
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String method = req.getMethod();
//        if (method.equals("GET")) {
//            doGet(req, resp);
//        }else if(method.equals("PATCH")){
//            doPatch(req, resp);
//        } else {
//            super.service(req, resp);
//        }
//    }
}

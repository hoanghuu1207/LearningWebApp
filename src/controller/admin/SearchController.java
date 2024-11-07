package controller.admin;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.spi.db.MethodGetter;
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

@WebServlet(urlPatterns = { "/admin/search/*" })
public class SearchController extends HttpServlet {
    private I_UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null) {
            String value = pathInfo.substring(1);

            resp.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            JSONObject obj = new JSONObject();

            obj.put("code", 200);

            ArrayList<UserModel> users = null;

            if(value.equals("")){
                users = userService.getAllUsers();
            }else{
                users = userService.searchUsers(value);
            }

            Gson gson = new Gson();
            obj.put("users", gson.toJson(users));

            out.print(obj);
        }
    }
}

package controller.clients;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;
import service.I_UserService;
import service.impl.UserService;

@WebServlet(urlPatterns = { "/user/account" })
public class UserAccountController extends HttpServlet {
    private I_UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel loggedInUser = (UserModel) req.getSession().getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            resp.sendRedirect("/user/login");
//            return;
//        }

        req.setAttribute("user", loggedInUser);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clients/pages/user/account.jsp");
        dispatcher.forward(req, resp);
    }
}

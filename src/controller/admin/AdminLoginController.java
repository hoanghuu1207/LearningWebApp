package controller.admin;

import model.AdminModel;
import model.UserModel;
import org.mindrot.jbcrypt.BCrypt;
import service.I_AdminService;
import service.I_UserService;
import service.impl.AdminService;
import service.impl.UserService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/login" })
public class AdminLoginController extends HttpServlet {
    private I_AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispactcher = req.getRequestDispatcher("/views/admin/pages/user/login.jsp");
        dispactcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AdminModel adminModel = new AdminModel();
        adminModel.setUsername(username);
        adminModel.setPassword(password);

        AdminModel admin = adminService.loginAdmin(adminModel);

        String referer = req.getHeader("Referer");

        if (admin == null) {
//			resp.sendRedirect("register-failed.jsp");
            System.out.println("Dang nhap that bai");
            if (referer != null) {
                resp.sendRedirect(referer);
            } else {
                resp.sendRedirect("/admin/login");
            }

            return;
        } else {
//			resp.sendRedirect("register-failed.jsp");
            System.out.println("Dang nhap thanh cong");

            Cookie cookie = new Cookie("token", admin.getToken());

            cookie.setPath("/admin");
            cookie.setMaxAge(1 * 60 * 60); // set coookie trong 1h

            resp.addCookie(cookie);

            resp.sendRedirect("/admin/home");
        }
    }
}

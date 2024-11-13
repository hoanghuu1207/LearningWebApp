package ServerEndpoint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/postArticle"})
public class WS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<form method='post'>");
        out.println("<input type='text' name='title'>");
        out.println("<input type='text' name='content'>");
        out.println("<input type='submit' value='ok'>");
        out.println("</form>");

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // Gửi thông báo cho học sinh
        NotificationWebSocket.sendNotification("New post from teacher: " + title + "  content: " + content);
    }
}

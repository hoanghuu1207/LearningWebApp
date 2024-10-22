package validate.clients;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/user/password/forgot" })
public class ForgotPasswordValidate implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String referer = httpRequest.getHeader("Referer");

		// Kiểm tra nếu phương thức là POST
		if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {

			String email = httpRequest.getParameter("email");

			if (email.equals("")) {
				// Trở lại trang quên mật khẩu
				System.out.println("Email khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/password/forgot");
				}
				return;
			}
		}

		// Chuyển tiếp request và response
		chain.doFilter(request, response);
	}
}

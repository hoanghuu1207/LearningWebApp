package validate.clients;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/user/password/reset" })
public class ResetPasswordValidate implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Kiểm tra nếu phương thức là POST
		if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {

			String password = httpRequest.getParameter("password");
			String confirmPassword = httpRequest.getParameter("confirmPassword");

			String referer = httpRequest.getHeader("Referer");

			if (password.equals("")) {
				// Trở lại trang quên mật khẩu
				System.out.println("Mat khau khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/password/reset");
				}
				return;
			}

			if (confirmPassword.equals("")) {
				// Trở lại trang quên mật khẩu
				System.out.println("Vui long xac nhan mat khau");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/password/reset");
				}
				return;
			}

			if (!confirmPassword.equals(password)) {
				// Trở lại trang quên mật khẩu
				System.out.println("Mat khau khong trung khop");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/password/reset");
				}
				return;
			}
		}

		// Chuyển tiếp request và response
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}

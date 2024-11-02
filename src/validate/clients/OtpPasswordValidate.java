package validate.clients;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/user/password/otp" })
public class OtpPasswordValidate implements Filter {
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

			String email = httpRequest.getParameter("email");

			if (email.equals("")) {
				// Trở lại trang quên mật khẩu
				System.out.println("Email khong duoc bo trong");

				httpResponse.sendRedirect("/user/password/forgot");
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

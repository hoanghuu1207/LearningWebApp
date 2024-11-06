package validate.clients;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/user/register" })
public class RegisterValidate implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String referer = httpRequest.getHeader("Referer");

		// Kiểm tra nếu phương thức là POST
		if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {

			String firstName = httpRequest.getParameter("firstName");
			String lastName = httpRequest.getParameter("lastName");
			String email = httpRequest.getParameter("email");
			String password = httpRequest.getParameter("password");

			if (firstName.equals("")) {
				// Trở lại trang đăng ký
				System.out.println("Ten khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/register");
				}
				return;
			}

			if (lastName.equals("")) {
				// Trở lại trang đăng ký
				System.out.println("Ten khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/register");
				}
				return;
			}

			if (email.equals("")) {
				// Trở lại trang đăng ký
				System.out.println("Email khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/register");
				}
				return;
			}

			if (password.equals("")) {
				// Trở lại trang đăng ký
				System.out.println("Password khong duoc bo trong");
				if (referer != null) {
					httpResponse.sendRedirect(referer);
				} else {
					httpResponse.sendRedirect("/user/register");
				}
			}
		}

		// Chuyển tiếp request và response
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}

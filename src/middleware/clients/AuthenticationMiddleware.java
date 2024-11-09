//package middleware.clients;
//
//import java.io.IOException;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebFilter(urlPatterns = { "/home" })
//public class AuthenticationMiddleware implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		Cookie[] cookies = httpRequest.getCookies();
//
//		boolean hasTokenUser = false;
//
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if ("tokenUser".equals(cookie.getName())) {
//					hasTokenUser = true;
//					break;
//				}
//			}
//		}
//
//		if (!hasTokenUser) {
//			httpResponse.sendRedirect("/user/login");
//			return;
//		}
//
//		System.out.println("Filter authen");
//
//		chain.doFilter(request, response);
//	}
//
//    @Override
//    public void destroy() {
//
//    }
//
//}

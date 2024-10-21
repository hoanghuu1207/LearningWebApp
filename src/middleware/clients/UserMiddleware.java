//package middleware.clients;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//
//@WebFilter(urlPatterns = { "/user/register", "/user/login", "/user/password/forgot" })
//public class UserMiddleware implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
////		HttpServletRequest httpRequest = (HttpServletRequest) request;
////		HttpServletResponse httpResponse = (HttpServletResponse) response;
////
////		Cookie[] cookies = httpRequest.getCookies();
////
////		boolean hasTokenUser = false;
////
////		if (cookies != null) {
////			for (Cookie cookie : cookies) {
////				if ("tokenUser".equals(cookie.getName())) {
////					hasTokenUser = true;
////					break;
////				}
////			}
////		}
////
////		if (!hasTokenUser) {
////			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
////			return;
////		}
//
//		System.out.println("Filter");
//
//		chain.doFilter(request, response);
//	}
//
//}

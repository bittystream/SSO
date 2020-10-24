package cn.edu.cqu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String service = req.getRequestURI();
//		System.out.println(service);
		if (service.endsWith("login.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		
		
		// session中存有用户信息，放行
		if (req.getSession().getAttribute("CAS_TGC")!=null) {
			System.out.println("Session OK. AuthenticationFilter let go.");
			chain.doFilter(request, response);
			return;
		}
		
		// 检测请求信息中有ST，放行（交给了TicketValidationFilter继续过滤）
		if (req.getParameter("ticket")!=null) {
			System.out.println("Ticket param OK. AuthenticationFilter let go.");
			chain.doFilter(request, response);
			return;
		}
		
		// 都没有，重定向到登陆界面，即CAS_Server
		Cookie cookie = new Cookie("redirect_url",service);
		resp.addCookie(cookie);
		System.out.println("AuthenticationFilter won't let go.");
		resp.sendRedirect("login.jsp?redirect_url="+service);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

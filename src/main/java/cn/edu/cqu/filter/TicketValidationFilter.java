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
import cn.edu.cqu.model.*;
/**
 * Servlet Filter implementation class TicketValidationFilter
 */
public class TicketValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TicketValidationFilter() {
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
		// 验证ST的有效性——是否与数据库中存储的数据的ST相同
		String CAS_ST = req.getParameter("ticket");
		String service = req.getRequestURI();
		System.out.println(service);
		
		if (service.endsWith("login.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		if (req.getSession().getAttribute("CAS_TGC")!=null) {
			System.out.println("Session OK. TicketValidationFilter let go.");
			chain.doFilter(request, response);
			return;
		}
		if (CAS_ST == null || !CAS_ST.equals(ServiceTicket.getServiceTicket())) {
			Cookie cookie = new Cookie("redirect_url",service);
			resp.addCookie(cookie);
			System.out.println("TicketValidationFilter won't let go.");
			resp.sendRedirect("login.jsp?redirect_url="+service);
			return;
		}
		
		
		if (CAS_ST.equals(ServiceTicket.getServiceTicket())) {
			System.out.println("Ticket OK. TicketValidationFilter let go.");
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

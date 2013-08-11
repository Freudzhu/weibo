package com.zhuhaihuan.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * 用来验证用户登录状态和权限的Servlet Filter。
 */

public class SecurityWebFilter implements Filter {
	private Logger logger = Logger.getLogger(getClass());
	private final static String LOGIN_PATH = "/weibo";
    public SecurityWebFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestName = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1);
        System.out.println(req.getRequestURI());
		if (!req.getRequestURI().equals("/weibo/")&& !requestName.equals("login.do") && !SessionHelper.isLogin((req.getSession())) ){
			res.sendRedirect(LOGIN_PATH);
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

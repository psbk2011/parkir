package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LoginController loginController = (LoginController) ((HttpServletRequest) request)
				.getSession().getAttribute("LoginController");

		if (loginController == null || !loginController.isLoggedIn()) {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/ViewLogin.jsf");
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}

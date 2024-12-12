package vn.starshopvn.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Object attr = session.getAttribute("account");
		Account acc = (Account) attr;
		if (acc == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}else if (acc.getRole().getRoleid() == 1) {
			chain.doFilter(request, response);
			return;
		}else {
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}
}
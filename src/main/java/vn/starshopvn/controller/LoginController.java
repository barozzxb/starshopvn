package vn.starshopvn.controller;

import java.io.IOException;

import vn.starshopvn.ultis.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	AccountService aServ = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/loading");
			return;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					session = req.getSession(true);
					session.setAttribute("userid", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/loading");
					return;
				}
			}
		}
		req.getRequestDispatcher("views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");
		if ("true".equals(remember)) {
			isRememberMe = true;
		}
		
		String alert = "";
		
		if (userid.isEmpty() || password.isEmpty()) {
			alert = "Please fill out the given fields";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
		Account acc = aServ.login(userid, password);
		if (acc != null) {
			//Start Session
			HttpSession session = req.getSession(true);
			session.setAttribute("account", acc);
			
			if (isRememberMe) {
				saveRemeberMe(resp, userid);
			}
			
			resp.sendRedirect(req.getContextPath() + "/loading");
		}else {
			alert = "Wrong username or password";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}
	private void saveRemeberMe(HttpServletResponse response, String userid) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, userid);
		cookie.setMaxAge(30 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}

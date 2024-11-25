package vn.starshopvn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;

@WebServlet(urlPatterns = {"/loading"})
public class LoadController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
			Account u = (Account)session.getAttribute("account");
			req.setAttribute("userid", u.getName());
		if(u.getRole().getRoleid() == Integer.parseInt("1")) {
			resp.sendRedirect(req.getContextPath()+"/admin");
		}else if(u.getRole().getRoleid() == Integer.parseInt("2")){
			resp.sendRedirect(req.getContextPath()+"/user");
		}else {
			resp.sendRedirect(req.getContextPath()+"/home");
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}
}
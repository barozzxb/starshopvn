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
	    HttpSession session = req.getSession();
	    if (session != null && session.getAttribute("account") != null) {
	        Account u = (Account) session.getAttribute("account");
	        req.setAttribute("userid", u.getName());

	        // Kiểm tra xem Role có null không
	        if (u.getRole() != null) {
	            int roleId = u.getRole().getRoleid();
	            if (roleId == Integer.parseInt("1")) {
	                resp.sendRedirect(req.getContextPath() + "/admin");
	            } else if (roleId == Integer.parseInt("2")) {
	                resp.sendRedirect(req.getContextPath() + "/user");
	            } else {
	                resp.sendRedirect(req.getContextPath() + "/home");
	            }
	        } else {
	            // Nếu Role null, có thể xử lý lỗi hoặc redirect
	            resp.sendRedirect(req.getContextPath() + "/login"); // Hoặc xử lý tùy thích
	        }
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/login");
	    }
	}

}
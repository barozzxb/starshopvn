package vn.starshopvn.controller;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;



@WebServlet(urlPatterns = { "/forgot"})
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    AccountService aSer = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		
		String alert = "";
    	if (aSer.findByEmail(email) == null) {
            alert = "Account not found. Try again";
            req.setAttribute("alert", alert);
    		req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    		return;
    	} else {	
    		req.getSession().setAttribute("email", email);
    		req.getSession().setAttribute("otpType", "forgot-password");
    		req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    		return;
    	}
    	
	}
}
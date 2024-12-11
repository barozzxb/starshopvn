package vn.starshopvn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.dao.AccountDAO;
import vn.starshopvn.dao.impl.AccountDAOImpl;
import vn.starshopvn.entity.Account;
import vn.starshopvn.ultis.PasswordUltis;

@WebServlet(urlPatterns = "/reset-password")
public class ResetPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newPassword = req.getParameter("new-password");
		String cNewPassword = req.getParameter("c-password");
		String alert = "";
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");

		AccountDAO accountDAO = new AccountDAOImpl();
		Account account = accountDAO.findByEmail(email);
        if (newPassword.isEmpty() || cNewPassword.isEmpty()) {
            alert = "Please fill out all the required fields";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        } else
		if (cNewPassword.equals(newPassword)) {
			String hashedPassword = PasswordUltis.hashPassword(newPassword);
			account.setPassword(hashedPassword);
			accountDAO.update(account);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		} else {
        	alert = "Confirm password does not match the password. Try again";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
		}
	}
}
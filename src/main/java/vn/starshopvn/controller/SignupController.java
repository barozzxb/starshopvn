package vn.starshopvn.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.CartService;
import vn.starshopvn.service.RoleService;
import vn.starshopvn.service.impl.AccountServiceImpl;
import vn.starshopvn.service.impl.CartServiceImpl;
import vn.starshopvn.service.impl.RoleServiceImpl;
import vn.starshopvn.ultis.PasswordUltis;

@WebServlet(urlPatterns = { "/signup" })
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	AccountService aServ = new AccountServiceImpl();
	RoleService rServ = new RoleServiceImpl();
	CartService cServ = new CartServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// encoding
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		// get attribute from view
		String userid = req.getParameter("userid");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmpass = req.getParameter("c-password");
		String alert = "";

		// process
		if (userid.isEmpty() || password.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
			alert = "Please fill out the given fields";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
		} else

		if (!confirmpass.equals(password)) {
			alert = "Confirm password does not match the password. Try again";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
		} else

		if (aServ.findById(userid) != null) {
			alert = "This account is already exist. Try another username or Login";
			req.setAttribute("alert", alert);
			req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
		} else {
			Timestamp createddate = new Timestamp(new Date().getTime());
			String hashedPassword = PasswordUltis.hashPassword(password);
			Account acc = new Account(userid, hashedPassword, email, true, rServ.findById(2), createddate);
			boolean signup_done = aServ.insert(acc);
			if (signup_done) {
				Cart cart = new Cart();
				cart.setCartId("cart_" + acc.getUserid());
				cart.setAccount(acc);
				cServ.createCart(cart);
				
				req.getSession().setAttribute("tempAccount", acc);
				req.getSession().setAttribute("email", email);
                req.getSession().setAttribute("otpType", "signup");
                req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
			} else {
				alert = "Something went wrong. Try again";
				req.setAttribute("alert", alert);
				req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
			}
		}
	}
}
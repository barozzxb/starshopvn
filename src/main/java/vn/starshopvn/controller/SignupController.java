package vn.starshopvn.controller;

import java.io.IOException;

import java.time.LocalDateTime;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.RoleService;
import vn.starshopvn.service.impl.AccountServiceImpl;
import vn.starshopvn.service.impl.RoleServiceImpl;


@WebServlet(urlPatterns = { "/signup" })
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	AccountService aServ = new AccountServiceImpl();
	RoleService rServ = new RoleServiceImpl();
	
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

        // Get form data
        String userid = req.getParameter("userid");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("c-password");
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        String phonenum = req.getParameter("phonenum");
        String address = req.getParameter("address");
        
        String alert = "";

        // Validate input
        if (userid.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || dob.isEmpty() || phonenum.isEmpty() || address.isEmpty()) {
            alert = "Please fill out all the required fields";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
            return;
        } else

        if (!confirmPassword.equals(password)) {
            alert = "Confirm password does not match the password. Try again";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
            return;
        } else

        if (aServ.findById(userid) != null) {
            alert = "This account already exists. Try another username or log in";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
            return;
        } else {
        	// Save the account in the database
        	// Create temporary Account object
            Account tempAccount = new Account(userid, password, email, false, rServ.findById(2), LocalDateTime.now());
            tempAccount.setName(name);
            tempAccount.setDob(dob);
            tempAccount.setGender(gender);
            tempAccount.setPhonenum(phonenum);
            tempAccount.setAddress(address);
            boolean signupDone = aServ.insert(tempAccount);
            if (signupDone) {
                // Store the account object temporarily in session
                req.getSession().setAttribute("tempAccount", tempAccount);
                // Redirect to OTP verification page
                req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
            } else {
                alert = "Something went wrong. Try again";
                req.setAttribute("alert", alert);
                req.getRequestDispatcher("/views/signup.jsp").forward(req, resp);
            }
        }

      
    }
}
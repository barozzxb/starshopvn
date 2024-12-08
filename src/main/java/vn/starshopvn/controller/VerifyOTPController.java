package vn.starshopvn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

@WebServlet(urlPatterns = { "/verify-otp" })
public class VerifyOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;
	AccountService aServ = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the OTP entered by the user
        String otp = req.getParameter("otp");
        String alert = "";

        // Get the OTP stored in session
        String sessionOtp = (String) req.getSession().getAttribute("otp");

        // Check if the OTP entered by the user matches the session OTP
        if (otp == null || otp.isEmpty()) {
            alert = "Please enter the OTP.";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        } else if (sessionOtp == null || !sessionOtp.equals(otp)) {
            alert = "Invalid OTP. Try again.";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        } else {
            // OTP is valid, now process the account registration or password reset
            String url = req.getRequestURL().toString();

            if (url.contains("/signup")) {
                // Account registration process
                Account account = (Account) req.getSession().getAttribute("tempAccount");
                boolean otpValid = true; // OTP is valid as it was checked already
                if (otpValid) {
                    aServ.insert(account);  // Save account to DB
                    req.getSession().removeAttribute("otp");  // Remove OTP after success
                    resp.sendRedirect("/login");  // Redirect to login page
                }
            } else if (url.contains("/forgotpassword")) {
                // Forgot password process
                // Handle password reset process here
            }
        }
    }
}



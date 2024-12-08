package vn.starshopvn.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.ultis.EmailUtils;

@WebServlet(urlPatterns = { "/send-otp" })
public class OTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get email from request
        String email = req.getReader().readLine();
        String otp = generateOTP();  // Generate OTP
        boolean sent = sendOTPEmail(email, otp);  // Send OTP to email

        // Save OTP in session for later verification
        if (sent) {
            req.getSession().setAttribute("otp", otp);
        }

        // Send response back to client (JSON response)
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"success\": " + sent + "}");
        out.flush();
    }

    // Generate OTP
    private String generateOTP() {
        // Simple 6-digit random OTP generation
        int otp = (int)(Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    // Send OTP email
    private boolean sendOTPEmail(String email, String otp) {
        try {
            // Your logic to send OTP email (same as in previous examples)
            EmailUtils.sendOTPEmail(email, otp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



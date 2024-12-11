package vn.starshopvn.controller.onlinepayment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.config.Config;

@WebServlet("/vnpay_ipn")
public class VNPayIPN extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> fields = new HashMap<>();
            req.getParameterMap().forEach((key, value) -> fields.put(key, value[0]));

            String vnp_SecureHash = fields.remove("vnp_SecureHash");
            String computedHash = Config.hashAllFields(fields);

            if (vnp_SecureHash.equals(computedHash)) {
                String responseCode = fields.get("vnp_ResponseCode");
                if ("00".equals(responseCode)) {
                    // Xử lý thanh toán thành công
                    resp.getWriter().write("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
                } else {
                    // Xử lý thanh toán thất bại
                    resp.getWriter().write("{\"RspCode\":\"02\",\"Message\":\"Payment Failed\"}");
                }
            } else {
                resp.getWriter().write("{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("{\"RspCode\":\"99\",\"Message\":\"Unknown Error\"}");
        }
    }
}


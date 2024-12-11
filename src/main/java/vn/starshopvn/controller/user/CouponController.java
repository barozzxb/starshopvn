package vn.starshopvn.controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Coupon;
import vn.starshopvn.service.CouponService;
import vn.starshopvn.service.impl.CouponServiceImpl;

@WebServlet(urlPatterns = { "/user/coupon/list", "/user/coupon/apply", "/user/coupon/remove" })
public class CouponController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    CouponService couponService = new CouponServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        // Hiển thị danh sách các mã giảm giá còn thời hạn
        if (url.contains("list")) {
            List<Coupon> allCoupons = couponService.findAll();
            LocalDateTime now = LocalDateTime.now();

            // Lọc mã giảm giá còn thời hạn
            List<Coupon> validCoupons = allCoupons.stream()
                    .filter(c -> c.getStart().isBefore(now) && c.getEnd().isAfter(now))
                    .toList();

            // Đưa danh sách mã giảm giá vào request để hiển thị trong JSP
            req.setAttribute("coupons", validCoupons);
            req.getRequestDispatcher("/views/user/coupon/coupon-list.jsp").forward(req, resp);
        }

        // Xóa mã giảm giá đã áp dụng
        else if (url.contains("remove")) {
            session.removeAttribute("appliedCoupon");
            req.setAttribute("message", "Coupon removed successfully!");
            resp.sendRedirect(req.getContextPath() + "/user/cart"); // Redirect về giỏ hàng
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();

        // Áp dụng mã giảm giá
        if (url.contains("apply")) {
            String couponId = req.getParameter("cid");
            String totalAmountStr = req.getParameter("totalAmount");
            double totalAmount = Double.parseDouble(totalAmountStr);

            // Kiểm tra mã giảm giá
            Coupon coupon = couponService.findById(couponId);
            if (coupon == null) {
                req.setAttribute("error", "Coupon not found.");
                resp.sendRedirect(req.getContextPath() + "/user/coupon/list");
                return;
            }

            // Kiểm tra thời hạn mã giảm giá
            LocalDateTime now = LocalDateTime.now();
            if (coupon.getStart().isAfter(now) || coupon.getEnd().isBefore(now)) {
                req.setAttribute("error", "Coupon is not valid.");
                resp.sendRedirect(req.getContextPath() + "/user/coupon/list");
                return;
            }

            // Tính toán số tiền giảm giá
            double discountPercent = Double.parseDouble(coupon.getCpercent());
            double discountAmount = totalAmount * discountPercent / 100;
            double finalAmount = totalAmount - discountAmount;

            // Lưu mã giảm giá vào session
            session.setAttribute("appliedCoupon", coupon);
            session.setAttribute("discountAmount", discountAmount);
            session.setAttribute("finalAmount", finalAmount);

            req.setAttribute("message", "Coupon applied successfully!");
            resp.sendRedirect(req.getContextPath() + "/user/cart"); // Redirect về giỏ hàng
        }
    }
}

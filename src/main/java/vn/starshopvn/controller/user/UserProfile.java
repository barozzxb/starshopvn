package vn.starshopvn.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

<<<<<<< Updated upstream
@WebServlet(urlPatterns = {"/user/profile", "/user/my"})
=======
@WebServlet(urlPatterns = {"/user/profile","/user/my"})
>>>>>>> Stashed changes
public class UserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AccountService aServ = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra session để lấy thông tin người dùng đã đăng nhập
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            // Nếu người dùng đã đăng nhập, hiển thị thông tin tài khoản
            req.setAttribute("account", account);
            req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
        } else {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin từ form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phonenum = req.getParameter("phonenum");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");

        // Lấy thông tin tài khoản từ session
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            // Cập nhật thông tin tài khoản
            account.setName(name);
            account.setEmail(email);
            account.setPassword(password);  // Cẩn thận với việc lưu trữ mật khẩu (nên mã hóa mật khẩu trước khi lưu)
            account.setPhonenum(phonenum);
            account.setDob(dob);
            account.setGender(gender);

            // Gọi phương thức cập nhật tài khoản từ AccountService
            boolean isUpdated = aServ.updateAccount(account);

            if (isUpdated) {
                req.setAttribute("alert", "Account updated successfully.");  // Thông báo thành công
                session.setAttribute("account", account); // Cập nhật thông tin vào session
            } else {
                req.setAttribute("alert", "Failed to update account.");  // Thông báo thất bại
            }

            // Quay lại trang profile với thông báo
            req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
        } else {
            // Nếu không có tài khoản trong session, chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

}

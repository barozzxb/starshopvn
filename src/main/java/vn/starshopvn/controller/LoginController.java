package vn.starshopvn.controller;

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

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AccountService aServ = new AccountServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        
        String alert = "";
        
        // Kiểm tra xem người dùng đã điền đầy đủ thông tin chưa
        if (userid.isEmpty() || password.isEmpty()) {
            alert = "Please fill out the given fields";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Xác thực thông tin đăng nhập
        Account acc = aServ.login(userid, password);

        if (acc != null) {
            // Đăng nhập thành công, bắt đầu phiên làm việc (session)
            HttpSession session = req.getSession(true);
            session.setAttribute("account", acc);

            // Chuyển hướng người dùng đến trang chủ
            resp.sendRedirect(req.getContextPath() + "/user/home");  // Chuyển hướng tới UserController
        } else {
            // Đăng nhập thất bại, thông báo lỗi
            alert = "Wrong username or password";
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}



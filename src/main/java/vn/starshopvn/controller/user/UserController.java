package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/home", "/user"})
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

<<<<<<< Updated upstream
=======
    // Khai báo dịch vụ Genre và Product
>>>>>>> Stashed changes
    GenreService genreServ = new GenreServiceImpl();
    ProductService prodServ = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Kiểm tra nếu session không có tài khoản người dùng thì chuyển về trang login
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login"); // Chuyển hướng về trang đăng nhập
            return;
        }

        // Nếu đã đăng nhập, lấy tài khoản từ session và hiển thị thông tin
        Account u = (Account) session.getAttribute("account");
        req.setAttribute("account", u);

<<<<<<< Updated upstream
        List<Genre> genres = genreServ.findAll();
        req.setAttribute("genres", genres);

        List<Product> newprods = prodServ.top3new();
        req.setAttribute("topprod", newprods);

=======
        // Lấy danh sách thể loại (genre)
        List<Genre> genres = genreServ.findAll();
        req.setAttribute("genres", genres);

        // Lấy 3 sản phẩm mới nhất
        List<Product> newprods = prodServ.top3new();
        req.setAttribute("topprod", newprods);



        // Chuyển hướng đến trang home.jsp để hiển thị
>>>>>>> Stashed changes
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}

package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/user/genres", "/user/genres/products" })
public class GenreController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    GenreService genreServ = new GenreServiceImpl();
    ProductService productServ = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/user/genres")) {
            // Hiển thị danh sách danh mục (genres)
            List<Genre> listgenre = genreServ.findAll();
            req.setAttribute("listgenre", listgenre);
            req.getRequestDispatcher("/views/user/genres.jsp").forward(req, resp);
        } else if (url.contains("/user/genres/products")) {
            // Hiển thị danh sách sản phẩm theo danh mục
            String gid = req.getParameter("gid"); // Sử dụng gid kiểu String (như trong bảng Genre)
            if (gid != null && !gid.isEmpty()) {
                try {
                    Genre genre = genreServ.findById(gid); // Tìm genre theo gid
                    if (genre != null) {
                        List<Product> products = productServ.findByGenre(gid); // Lấy sản phẩm theo gid
                        req.setAttribute("genre", genre);
                        req.setAttribute("products", products);
                        req.getRequestDispatcher("/views/user/product_by_genre.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/user/genres");
                    }
                } catch (Exception e) {
                    resp.sendRedirect(req.getContextPath() + "/user/genres");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/genres");
            }
        }
    }
}

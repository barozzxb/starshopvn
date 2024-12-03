package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Favorites;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.FavoriteService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.FavoriteServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/user/product/like", "/user/product/favorites" })
public class FavoriteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService proSer = new ProductServiceImpl();
    FavoriteService favSer = new FavoriteServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("currentUser");

        // Hiển thị danh sách sản phẩm đã thích
        if (url.contains("favorites")) {
            if (account != null) {
                List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
                List<Product> likedProducts = likedFavorites.stream().map(Favorites::getProduct).toList();
                req.setAttribute("likedProducts", likedProducts);
                System.out.println("Liked products: " + likedProducts);
            } else {
                req.setAttribute("likedProducts", new ArrayList<Product>());
            }
            req.getRequestDispatcher("/views/user/product/like.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("currentUser");

        // Xử lý yêu cầu "like" sản phẩm
        if (url.contains("like")) {
            String pid = req.getParameter("pid");
            Product likedProducts = proSer.findById(pid);

            if (account != null && likedProducts != null) {
                // Tạo ID ngẫu nhiên cho Favorite
                String fid = UUID.randomUUID().toString();
                Favorites favorite = new Favorites(fid, account, likedProducts);
                favSer.insert(favorite);

                // Cập nhật session
                List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
                session.setAttribute("likedProducts", likedFavorites.stream().map(Favorites::getProduct).toList());
                System.out.println("Updated liked products in session: " + session.getAttribute("likedProducts"));
            }

            // Chuyển hướng về trang chi tiết sản phẩm
            resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + pid);
        }
        else if (url.contains("favorites")) {
            if (account != null) {
                List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
                List<Product> likedProducts = likedFavorites.stream().map(Favorites::getProduct).toList();
                req.setAttribute("likedProducts", likedProducts);

            } else {
                req.setAttribute("likedProducts", new ArrayList<Product>());
            }
            req.getRequestDispatcher("/views/user/product/like.jsp").forward(req, resp);
        }
    }
}

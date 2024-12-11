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
        System.out.println("Request URL: " + url); // Log URL nhận được

        
        if (url.contains("/user/genres/products")) {
            String gid = req.getParameter("gid");  // Lấy gid từ URL

            if (gid != null && !gid.isEmpty()) {
                try {
                    Genre genre = genreServ.findById(gid); // Tìm genre theo gid
                    System.out.println("Genre found: " + genre); // Log genre

                    if (genre != null) {
                        List<Product> products = productServ.findByGenre(gid); // Lấy sản phẩm theo genre
                        System.out.println("Products found: " + products); // Log products

                        req.setAttribute("genre", genre);
                        req.setAttribute("products", products);
                        req.getRequestDispatcher("/views/user/product_by_genre.jsp").forward(req, resp);
                    } else {
                        System.out.println("Genre not found, redirecting to /user/genres");
                        resp.sendRedirect(req.getContextPath() + "/user/genres");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendRedirect(req.getContextPath() + "/user/genres");
                }
            } else {
                System.out.println("Invalid gid, redirecting to /user/genres");
                resp.sendRedirect(req.getContextPath() + "/user/genres");
            }
        }
        else if (url.contains("/user/genres")) {
            System.out.println("Fetching all genres...");
            List<Genre> listgenre = genreServ.findAll(); // Get all genres
            System.out.println("Genres fetched: " + listgenre);
            req.setAttribute("listgenre", listgenre);

            List<Product> products = productServ.findAll(); // Get all products
            System.out.println("All products fetched: " + products);
            req.setAttribute("products", products);

            req.getRequestDispatcher("/views/user/product/genres.jsp").forward(req, resp);
        }
    
    }
}

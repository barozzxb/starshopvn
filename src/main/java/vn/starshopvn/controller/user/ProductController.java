package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Post;
import vn.starshopvn.entity.Product;
import vn.starshopvn.entity.Review;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.PostService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.ReviewService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.PostServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.service.impl.ReviewServiceImpl;
import vn.starshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/user/products", "/user/product/detail", "/user/product/search" })
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
   ProductService proSer = new ProductServiceImpl();
   ReviewService reviewSer = new ReviewServiceImpl(); 
   GenreService gereSer=new GenreServiceImpl();
   PostService postSer=new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();

        if (url.contains("products")) {
            String upPath = Constant.upDir;
            req.setAttribute("upPath", upPath);
            // Lấy danh sách thể loại
            List<Genre> listgenre = gereSer.findAll(); 
            req.setAttribute("listgenre", listgenre);
            
            List<Post> posts=postSer.findAll();
            posts.forEach(post -> {
                if (post.getContent() != null && post.getContent().length() > 100) {
                    post.setContent(post.getContent().substring(0, 100) + "...");
                }
            });
            req.setAttribute("posts", posts);

            // Lấy danh sách sản phẩm
            List<Product> products = proSer.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/user/product/product-list.jsp").forward(req, resp);
        }

        else if (url.contains("detail")) {
            String pid = req.getParameter("pid");

            if (pid == null || pid.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
                return;
            }

            String upPath = Constant.upDir;
            req.setAttribute("upPath", upPath);

            Product product = proSer.findById(pid);
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
            req.setAttribute("product", product);

            // **Logic lấy danh sách đánh giá sản phẩm**
            List<Review> reviews = reviewSer.findByProductId(pid);
            req.setAttribute("reviews", reviews);

            // Kiểm tra xem mediaUrl có phải là video không
            for (Review review : reviews) {
                if (review.getMediaUrl() != null) {
                    String mediaUrl = review.getMediaUrl();
                    if (mediaUrl.endsWith(".mp4") || mediaUrl.endsWith(".webm") || mediaUrl.endsWith(".ogg")) {
                        review.setVideo(true);  // Thêm thuộc tính cho review
                    } else {
                        review.setVideo(false);
                    }
                }
            }

            // Lưu sản phẩm đã xem vào session và chỉ giữ 3 sản phẩm gần nhất
            List<Product> viewedProducts = (List<Product>) session.getAttribute("viewedProducts");
            if (viewedProducts == null) {
                viewedProducts = new ArrayList<>();
            } else {
                viewedProducts = viewedProducts.stream()
                                               .filter(Objects::nonNull)
                                               .collect(Collectors.toList());
            }

            if (!viewedProducts.stream().anyMatch(v -> v.getPid().equals(pid))) {
                viewedProducts.add(0, product);
            }

            if (viewedProducts.size() > 3) {
                viewedProducts = viewedProducts.subList(0, 3);
            }

            session.setAttribute("viewedProducts", viewedProducts);

            req.getRequestDispatcher("/views/user/product/product-detail.jsp").forward(req, resp);
        }


        else if (url.contains("/user/product/search")) {
            String search = req.getParameter("search");
            if (search != null && !search.strip().isEmpty()) {
                List<Product> products = proSer.findByName(search);
                req.setAttribute("products", products);
            } else {
                List<Product> products = proSer.findAll();
                req.setAttribute("products", products);
            }

            req.getRequestDispatcher("/views/user/product/product-list.jsp").forward(req, resp);
        }
    }
}

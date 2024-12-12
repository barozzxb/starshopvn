package vn.starshopvn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import vn.starshopvn.entity.Product;
import vn.starshopvn.entity.Review;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.ReviewService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.service.impl.ReviewServiceImpl;
import vn.starshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/products", "/product/detail", "/product/search", "/product/filter"})
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService proSer = new ProductServiceImpl();
    GenreService genreServ = new GenreServiceImpl();
    ReviewService reviewSer = new ReviewServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        List<Genre> genres = genreServ.findAll();
		req.setAttribute("genres", genres);
        
		if (url.contains("products")) {
			
			List<Product> products = proSer.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/common-views/product/product-list.jsp").forward(req, resp);
        }

        else if (url.contains("detail")) {
            String pid = req.getParameter("pid");

            if (pid == null || pid.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing");
                return;
            }

            
            String referer = req.getHeader("Referer");
            String userRole = (String) req.getSession().getAttribute("role");
            if (referer == null || referer.isEmpty()) {
                if ("admin".equals(userRole)) {
                    referer = req.getContextPath() + "/admin/home";
                } else {
                    referer = req.getContextPath() + "/home/product";
                }
            }
            req.setAttribute("backUrl", referer);

            Product p = proSer.findById(pid);
            if (p == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
            req.setAttribute("product", p);

            List<Review> reviews = reviewSer.findByProductId(pid);
            req.setAttribute("reviews", reviews);
			
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
            
            req.getRequestDispatcher("/views/common-views/product/product-info.jsp").forward(req, resp);
        }

        // Tìm kiếm sản phẩm
        else if (url.contains("search")) {
			String keyword = req.getParameter("search");
			
			req.setAttribute("products", "products");
			req.getRequestDispatcher("/views/product/product-list.jsp").forward(req, resp);
		} else if (url.contains("filter")) {
			String[] genresFilter = req.getParameterValues("genres");
			List<String> gFilter = null;
			if (genresFilter != null && genresFilter.length > 0) {
			    gFilter = Arrays.asList(genresFilter);
			}
			int price = Integer.parseInt(req.getParameter("price"));
			List<Product> products = proSer.searchAndFilter(gFilter, price);
			req.setAttribute("products", products);
			req.getRequestDispatcher("/views/product/product-list.jsp").forward(req, resp);
		} else if (url.contains("reset")) {
			List<Product> products = proSer.findAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/product/product-list.jsp").forward(req, resp);
		}
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = req.getRequestURI();
        HttpSession session = req.getSession();
        List<Genre> genres = genreServ.findAll();
		req.setAttribute("genres", genres);
		
    }
}
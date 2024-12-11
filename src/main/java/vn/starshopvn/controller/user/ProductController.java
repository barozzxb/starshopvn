package vn.starshopvn.controller.user;

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
import vn.starshopvn.entity.Post;
import vn.starshopvn.entity.Product;
import vn.starshopvn.entity.Review;
import vn.starshopvn.service.PostService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.ReviewService;
import vn.starshopvn.service.impl.PostServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.service.impl.ReviewServiceImpl;
import vn.starshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/user/products", "/user/product/detail", "/user/product/search", "/user/shop" })
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProductService proSer = new ProductServiceImpl();
	PostService postSer=new PostServiceImpl();
	ReviewService reviewSer = new ReviewServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		
		if (url.contains("products")) {
			String upPath = Constant.upDir;
			req.setAttribute("upPath", upPath);
			List<Product> products = proSer.findAll();
			req.setAttribute("products", products);
			List<Post> posts = postSer.findAll();
	        	posts.forEach(post -> {
	            	if (post.getContent() != null && post.getContent().length() > 100) {
	                    post.setContent(post.getContent().substring(0, 100) + "...");
	                }
	            });
	            req.setAttribute("posts", posts);

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
                viewedProducts.add(0, p);
            }

            if (viewedProducts.size() > 3) {
                viewedProducts = viewedProducts.subList(0, 3);
            }

            session.setAttribute("viewedProducts", viewedProducts);

            req.getRequestDispatcher("/views/user/product/product-detail.jsp").forward(req, resp);
        }

		else if (url.contains("search")) {
			String keyword = req.getParameter("search");
			List<Product> products = proSer.searchProducts(keyword);
			req.setAttribute("products", products);
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
}
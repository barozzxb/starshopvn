package vn.starshopvn.controller.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Product;
import vn.starshopvn.entity.Review;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.ReviewService;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.service.impl.ReviewServiceImpl;

@WebServlet(urlPatterns = { "/user/product/review", "/user/product/comment", "/user/product/review/delete" })
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50   // 50MB
	)
public class ReviewController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductService proSer = new ProductServiceImpl();
    private ReviewService reviewSer = new ReviewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            req.setAttribute("message", "Bạn cần đăng nhập để đánh giá sản phẩm.");
            req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
            return;
        }
        
        if (url.contains("delete")) {
            String reviewId = req.getParameter("reviewId");
            if (reviewId != null) {
                try {
                    reviewSer.deleteById(reviewId);
                    req.setAttribute("message", "Review deleted successfully!");

                    // Cập nhật danh sách đánh giá sau khi xóa
                    List<Review> reviews = reviewSer.findByProductId(req.getParameter("pid"));
                    req.setAttribute("reviews", reviews);
                } catch (Exception e) {
                    System.err.println("Error deleting review with ID: " + reviewId);
                    e.printStackTrace();
                    req.setAttribute("message", "Failed to delete review!");
                }
            }
            req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
        } else if (url.contains("review")) {
            String pid = req.getParameter("pid");
            if (pid != null) {
                Product product = proSer.findById(pid);
                if (product != null) {
                    List<Review> reviews = reviewSer.findByProductId(pid);
                    req.setAttribute("product", product);
                    req.setAttribute("reviews", reviews);
                }
            }
            req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            req.setAttribute("message", "Bạn cần đăng nhập để đánh giá sản phẩm.");
            req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
            return;
        }

        if (url.contains("review")) {
            String pid = req.getParameter("pid");
            String ratingStr = req.getParameter("rating");
            String comment = req.getParameter("comment");

            if (comment != null && comment.length() >= 50) {
                Product product = proSer.findById(pid);
                if (product != null) {
                    int rating = Integer.parseInt(ratingStr);
                    Review review = new Review();
                    review.setReviewId(UUID.randomUUID().toString());
                    review.setProduct(product);
                    review.setAccount(account);
                    review.setRating(rating);
                    review.setComment(comment);

                    // Xử lý tệp đính kèm
                    Part mediaPart = req.getPart("mediaFile");
                    if (mediaPart != null && mediaPart.getSize() > 0) {
                        String fileName = Paths.get(mediaPart.getSubmittedFileName()).getFileName().toString();
                        String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator;
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }
                        String filePath = uploadPath + review.getReviewId() + "_" + fileName;
                        mediaPart.write(filePath);
                        review.setMediaUrl("/uploads/" + review.getReviewId() + "_" + fileName);
                    }

                    try {
                        reviewSer.insert(review); // Lưu vào cơ sở dữ liệu
                        // Cập nhật lại danh sách đánh giá sau khi thêm mới
                        List<Review> reviews = reviewSer.findByProductId(pid);
                        req.setAttribute("reviews", reviews);
                        resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + pid);
                    } catch (Exception e) {
                        System.err.println("Error inserting review for product ID: " + pid);
                        e.printStackTrace();
                        req.setAttribute("message", "Failed to insert review!");
                        req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
                    }
                }
            } else {
                req.setAttribute("message", "Comment must be at least 50 characters.");
                req.getRequestDispatcher("/views/user/product/review.jsp").forward(req, resp);
            }
        }
    }
}
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
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
			req.getRequestDispatcher("/views/common-views/product/review.jsp").forward(req, resp);
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
			req.getRequestDispatcher("/views/common-views/product/review.jsp").forward(req, resp);
		}
	}

	@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        String url = req.getRequestURI();
	        HttpSession session = req.getSession();
	        
	        // Lấy thông tin người dùng từ session
	        Account account = (Account) session.getAttribute("account");
	        
	        if (account != null) {
	            // Người dùng đã đăng nhập
	            String pid = req.getParameter("pid");
	            String ratingStr = req.getParameter("rating");
	            String comment = req.getParameter("comment");
	            
	            // Kiểm tra nếu thông tin sản phẩm và đánh giá hợp lệ
	            if (pid != null && !pid.isEmpty() && ratingStr != null && !ratingStr.isEmpty() && comment != null && comment.length() >= 50) {
	                Product product = proSer.findById(pid);
	                
	                if (product != null) {
	                    int rating = Integer.parseInt(ratingStr);
	                    Review review = new Review();
	                    
	                    // Thiết lập các thuộc tính cho Review
	                    review.setReviewId(UUID.randomUUID().toString());
	                    review.setProduct(product);
	                    review.setAccount(account);
	                    review.setRating(rating);
	                    review.setComment(comment);
	                    
	                    // Xử lý tệp đính kèm (media)
	                    Part mediaPart = req.getPart("mediaFile");
	                    if (mediaPart != null && mediaPart.getSize() > 0) {
	                        String fileName = Paths.get(mediaPart.getSubmittedFileName()).getFileName().toString();
	                        fileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
	                        String uploadPath = getServletContext().getRealPath("") + "upload";
	                        File uploadDir = new File(uploadPath);
	                        if (!uploadDir.exists()) {
	                            uploadDir.mkdirs();
	                        }
	                        String filePath = uploadPath + File.separator + review.getReviewId() + "_" + fileName;
	                        mediaPart.write(filePath);
	                        review.setMediaUrl("/upload/" + review.getReviewId() + "_" + fileName);
	                        
	                        // Kiểm tra và xác định xem có phải video không
	                        String mediaUrl = review.getMediaUrl();
	                        if (mediaUrl != null && (mediaUrl.endsWith(".mp4") || mediaUrl.endsWith(".webm") || mediaUrl.endsWith(".ogg"))) {
	                            review.setVideo(true);
	                        } else {
	                            review.setVideo(false);
	                        }
	                    }
// Thêm đánh giá vào cơ sở dữ liệu
	                    reviewSer.insert(review);
	                    
	                    // Chuyển hướng về trang chi tiết sản phẩm
	                    resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + pid);
	                } else {
	                    req.setAttribute("message", "Sản phẩm không tồn tại!");
	                    req.getRequestDispatcher("/views/common-views/product/review.jsp").forward(req, resp);
	                }
	            } else {
	                req.setAttribute("message", "Comment phải có ít nhất 50 ký tự.");
	                req.getRequestDispatcher("/views/common-views/product/review.jsp").forward(req, resp);
	            }
	        } else {
	            // Người dùng chưa đăng nhập
	            req.setAttribute("message", "Bạn cần đăng nhập để đánh giá sản phẩm.");
	            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("message", "Có lỗi xảy ra khi xử lý yêu cầu.");
	        req.getRequestDispatcher("/views/common-views/product/review.jsp").forward(req, resp);
	    }
	}
}

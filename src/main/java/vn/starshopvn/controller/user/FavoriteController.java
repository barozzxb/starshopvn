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

@WebServlet(urlPatterns = { "/user/product/like", "/user/product/favorites", "/user/product/delete" })
public class FavoriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ProductService proSer = new ProductServiceImpl();
	FavoriteService favSer = new FavoriteServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");

		// Hiển thị danh sách sản phẩm đã thích
		if (url.contains("favorites")) {
			if (account != null) {
				// Lấy danh sách sản phẩm yêu thích từ dịch vụ
				List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
				List<Product> likedProducts = likedFavorites.stream().map(Favorites::getProduct).toList();
				req.setAttribute("likedProducts", likedProducts); // Truyền danh sách sản phẩm yêu thích vào JSP
				req.setAttribute("likedFavorites", likedFavorites); // Truyền danh sách Favorites vào JSP
			} else {
				req.setAttribute("likedProducts", new ArrayList<Product>());
				req.setAttribute("likedFavorites", new ArrayList<Favorites>());
			}
			req.getRequestDispatcher("/views/common-views/product/like.jsp").forward(req, resp);
		}

		// Xóa sản phẩm khỏi danh sách yêu thích
		else if (url.contains("delete")) {
			String fid = req.getParameter("fid"); // Lấy fid từ request
			if (fid != null) {
				// Gọi service để xóa bằng fid
				boolean isDeleted = favSer.delete(fid);
				if (isDeleted) {
					req.setAttribute("message", "Product removed from favorites successfully!");
				} else {
					req.setAttribute("message", "Failed to remove product from favorites!");
				}
			}

			// Cập nhật danh sách sản phẩm yêu thích sau khi xóa
			if (account != null) {
				List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
				req.setAttribute("likedFavorites", likedFavorites); // Truyền lại danh sách Favorites
				List<Product> likedProducts = likedFavorites.stream().map(Favorites::getProduct).toList();
				req.setAttribute("likedProducts", likedProducts); // Truyền lại danh sách sản phẩm yêu thích
			} else {
				req.setAttribute("likedProducts", new ArrayList<Product>());
				req.setAttribute("likedFavorites", new ArrayList<Favorites>());
			}

			req.getRequestDispatcher("/views/common-views/product/like.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");

		// Xử lý yêu cầu "like" sản phẩm
		if (url.contains("like")) {
			String pid = req.getParameter("pid");
			Product likedProduct = proSer.findById(pid);

			if (account != null && likedProduct != null) {
				// Tạo ID ngẫu nhiên cho Favorite
				String fid = UUID.randomUUID().toString();
				Favorites favorite = new Favorites(fid, account, likedProduct);
				favSer.insert(favorite);

				// Cập nhật session
				List<Favorites> likedFavorites = favSer.findByUserId(account.getUserid());
				session.setAttribute("likedProducts", likedFavorites.stream().map(Favorites::getProduct).toList());
			}

			// Chuyển hướng về trang chi tiết sản phẩm
			resp.sendRedirect(req.getContextPath() + "/user/product/detail?pid=" + pid);
		}
	}

}
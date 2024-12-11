package vn.starshopvn.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet("/user/product/search")
public class ProductSearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các tham số từ form tìm kiếm
        String query = req.getParameter("query");
        Integer rating = req.getParameter("rating") != null ? Integer.parseInt(req.getParameter("rating")) : null;
        LocalDateTime createdAtFrom = req.getParameter("createdAtFrom") != null ? LocalDateTime.parse(req.getParameter("createdAtFrom")) : null;
        LocalDateTime createdAtTo = req.getParameter("createdAtTo") != null ? LocalDateTime.parse(req.getParameter("createdAtTo")) : null;
        Integer minPrice = req.getParameter("minPrice") != null ? Integer.parseInt(req.getParameter("minPrice")) : null;
        Integer maxPrice = req.getParameter("maxPrice") != null ? Integer.parseInt(req.getParameter("maxPrice")) : null;
        Integer minQuantity = req.getParameter("minQuantity") != null ? Integer.parseInt(req.getParameter("minQuantity")) : null;
        Integer maxQuantity = req.getParameter("maxQuantity") != null ? Integer.parseInt(req.getParameter("maxQuantity")) : null;
        String sortBy = req.getParameter("sortBy");
        boolean ascending = Boolean.parseBoolean(req.getParameter("ascending"));

        // Tìm kiếm sản phẩm
        List<Product> products = productService.searchProducts(query, rating, createdAtFrom, createdAtTo, minPrice, maxPrice, minQuantity, maxQuantity, sortBy, ascending);

        // Gửi kết quả về phía người dùng
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/productList.jsp").forward(req, resp);
    }
}


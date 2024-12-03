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
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/user/products", "/user/product/detail", "/user/product/search" })
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService proSer = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        HttpSession session = req.getSession();

        // Hiển thị danh sách tất cả sản phẩm
        if (url.contains("products")) {
            String upPath = Constant.upDir;
            req.setAttribute("upPath", upPath);
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

            Product p = proSer.findById(pid);
            if (p == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
            req.setAttribute("product", p);

            // Lưu sản phẩm đã xem vào session và chỉ giữ 3 sản phẩm gần nhất
            List<Product> viewedProducts = (List<Product>) session.getAttribute("viewedProducts");
            if (viewedProducts == null) {
                viewedProducts = new ArrayList<>();
            } else {
                // Lọc bỏ phần tử null
                viewedProducts = viewedProducts.stream()
                                               .filter(Objects::nonNull)
                                               .collect(Collectors.toList());
            }

            // Thêm sản phẩm vào danh sách đã xem nếu chưa có
            if (!viewedProducts.stream().anyMatch(v -> v.getPid().equals(pid))) {
                viewedProducts.add(0, p); // Thêm vào đầu danh sách
            }

            // Giới hạn chỉ giữ 3 sản phẩm đã xem gần nhất
            if (viewedProducts.size() > 3) {
                viewedProducts = viewedProducts.subList(0, 3); // Giữ lại 3 sản phẩm gần nhất
            }

            session.setAttribute("viewedProducts", viewedProducts);

            req.getRequestDispatcher("/views/user/product/product-detail.jsp").forward(req, resp);
        }

        // Tìm kiếm sản phẩm
        else if (url.contains("search")) {
            String search = req.getParameter("search");
            if (search != null && !search.strip().isEmpty()) {
                List<Product> list = proSer.findByName(search);
                req.setAttribute("products", list);
                req.getRequestDispatcher("/views/user/product/product-search.jsp").forward(req, resp);
                return;
            }
            List<Product> list = proSer.findAll();
            req.setAttribute("products", list);
            req.getRequestDispatcher("/views/user/product/product-list.jsp").forward(req, resp);
        }
    }
}
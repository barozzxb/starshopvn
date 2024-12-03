package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.ultis.Constant;

@WebServlet(urlPatterns = { "/user/products", "/user/product/detail", "/user/product/search" })
public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ProductService proSer=new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String url = req.getRequestURI();

	        // Hiển thị danh sách tất cả sản phẩm
	        if (url.contains("products")) {
	            String upPath = Constant.upDir;
	            req.setAttribute("upPath", upPath);
	            List<Product> products = proSer.findAll();
	            req.setAttribute("products", products);
	            req.getRequestDispatcher("/views/user/product/product-list.jsp").forward(req, resp);
	        }

	        // Xem chi tiết sản phẩm
	        else if (url.contains("detail")) {
	            String pid = req.getParameter("pid");
	            String upPath = Constant.upDir;
	            req.setAttribute("upPath", upPath);
	            Product p = proSer.findById(pid);
	            req.setAttribute("product", p);
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

package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Product;
import vn.starshopvn.model.TopSellingProduct;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/home"})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	GenreService genreServ = new GenreServiceImpl();
	ProductService prodServ = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			Account u = (Account) session.getAttribute("account");
			req.setAttribute("account", u);
			
			List<Genre> genres = genreServ.findAll();
			req.setAttribute("genres", genres);
			
			List<Product> newprods = prodServ.top3new();
			req.setAttribute("topprod", newprods);
			
			List<TopSellingProduct> topSelling = prodServ.topSellingProducts();
			req.setAttribute("topSelling", topSelling);
			
			req.setAttribute("username", u.getUserid());
			req.setAttribute("password", u.getPassword());
			req.setAttribute("email", u.getEmail());
			req.setAttribute("name", u.getName());
			req.setAttribute("phone", u.getPhonenum());
			req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
		}
	}
}

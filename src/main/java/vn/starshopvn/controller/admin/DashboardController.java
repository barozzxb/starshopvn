package vn.starshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.AccountServiceImpl;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	ProductService pServ = new ProductServiceImpl();
	GenreService gServ = new GenreServiceImpl();
	AccountService aServ = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int acc_num = aServ.count();
		req.setAttribute("acc_num", acc_num);
		
		int genres_num = gServ.countGenres();
		req.setAttribute("genres_num", genres_num);
		
		int prod_num = pServ.countProducts();
		req.setAttribute("prod_num", prod_num);
		
		List<Account> newacc = aServ.top5Account();
		req.setAttribute("newest_acc", newacc);
		
		req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
	}
}
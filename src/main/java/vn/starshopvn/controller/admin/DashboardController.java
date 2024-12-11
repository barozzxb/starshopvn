package vn.starshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.model.OrderSummary;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.OrderService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.AccountServiceImpl;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.OrderServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	ProductService pServ = new ProductServiceImpl();
	GenreService gServ = new GenreServiceImpl();
	AccountService aServ = new AccountServiceImpl();
	OrderService oServ = new OrderServiceImpl();
	
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
		
		int orders_num = oServ.getTotalOrder();
		req.setAttribute("orders_num", orders_num);
		
		double revenue_sum = oServ.getTotalRevenue();
		req.setAttribute("revenue_sum", revenue_sum);
		
		List<OrderSummary> monthlyOrderSummaryRaw = oServ.getMonthlyOrderSummary();
		List<OrderSummary> yearlyOrderSummaryRaw = oServ.getYearlyOrderSummary();
		
		Gson gson = new Gson();
		String monthlyOrderSummary = gson.toJson(monthlyOrderSummaryRaw);
		String yearlyOrderSummary = gson.toJson(yearlyOrderSummaryRaw);
		
		req.setAttribute("monthlyOrderSummary", monthlyOrderSummary);
		
		
		req.setAttribute("yearlyOrderSummary", yearlyOrderSummary);
		
		
		
		req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
	}
}
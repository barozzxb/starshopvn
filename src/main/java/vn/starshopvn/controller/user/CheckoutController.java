package vn.starshopvn.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Order;
import vn.starshopvn.service.OrderService;
import vn.starshopvn.service.impl.OrderServiceImpl;

@WebServlet(urlPatterns = {"/user/checkout-confirm", "/user/checkout-completed"})
public class CheckoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	OrderService oServ = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("confirm")) {
			String oid = req.getParameter("oid");
			
			Order order = oServ.getOrder(oid);
			
			req.setAttribute("order", order);
			req.getRequestDispatcher("/views/user/checkout/check-out.jsp").forward(req, resp);
		}
		
		if (url.contains("completed")) {
			req.getRequestDispatcher("/views/user/checkout/check-out-completed.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

package vn.starshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Order;
import vn.starshopvn.service.OrderService;
import vn.starshopvn.service.impl.OrderServiceImpl;
import vn.starshopvn.ultis.OrderStatus;

@WebServlet(urlPatterns = {"/admin/orders"})
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	OrderService oServ = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Order> orders = oServ.getAllOrders();
		OrderStatus odStatus = new OrderStatus();
		req.setAttribute("status", odStatus.getOrderStatus());
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("/views/admin/orders.jsp").forward(req, resp);
	}
}

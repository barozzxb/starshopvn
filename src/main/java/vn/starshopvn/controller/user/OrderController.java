package vn.starshopvn.controller.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;
import vn.starshopvn.entity.DeliveryInfo;
import vn.starshopvn.entity.Order;
import vn.starshopvn.entity.OrderDetail;
import vn.starshopvn.entity.OrderDetailId;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.CartService;
import vn.starshopvn.service.OrderService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.CartServiceImpl;
import vn.starshopvn.service.impl.OrderServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.ultis.OrderStatus;

@WebServlet(urlPatterns = {"/user/orders", "/user/order/make", "/user/order/detail"})
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	CartService cServ = new CartServiceImpl();
	ProductService prodServ = new ProductServiceImpl();
	OrderService oServ = new OrderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		HttpSession session = req.getSession();
		if (session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			Account acc = (Account) session.getAttribute("account");
			if (url.contains("orders")) {
				List<Order> orders = oServ.getAllOrdersByUserId(acc.getUserid());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher("/views/user/order/orders.jsp").forward(req, resp);
			} else if (url.contains("make")) {
				
				String cid = req.getParameter("cid");
				
				List<CartItem> cartItems = cServ.getCartItemsByCartId(cid);
				double totalPrice = 0;

				if (cartItems != null && !cartItems.isEmpty()) {
					totalPrice = cartItems.stream()
							.mapToDouble(item -> item.getQuantity() * item.getProduct().getPprice()).sum();
				}
				req.setAttribute("totalPrice", totalPrice);
				req.setAttribute("cartItems", cartItems);
				req.setAttribute("listDeliveryInfos", null);
				
				req.getRequestDispatcher("/views/user/order/make-order.jsp").forward(req, resp);
			} else if (url.contains("detail")) {
				String oid = req.getParameter("oid");
				Order order = oServ.getOrder(oid);
				req.setAttribute("order", order);
				req.getRequestDispatcher("/views/user/order/order-detail.jsp").forward(req, resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String url = req.getRequestURI();
		HttpSession session = req.getSession();
		if (session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
		} else {
			Account acc = (Account) session.getAttribute("account");

			Order order = new Order();
			
			String addressChoice = req.getParameter("address_choice");
			DeliveryInfo dinfo = new DeliveryInfo();
			
			if (addressChoice.equals("existing_address")) {
				dinfo = new DeliveryInfo();
			} else {
				String userid = req.getParameter("userid");
		        String deinfoid = req.getParameter("deinfoid");
		        String daddress = req.getParameter("daddress");
		        String ddistrict = req.getParameter("ddistrict");
		        String dcity = req.getParameter("dcity");
		        String dprovince = req.getParameter("dprovince");
		        String dcountry = req.getParameter("dcountry");
		        String dzipcode = req.getParameter("dzipcode");
		        String dtype = req.getParameter("dtype");
		        
		        dinfo = new DeliveryInfo(deinfoid, daddress, ddistrict, dcity, dprovince, dcountry, dzipcode, dtype, acc);
     
			}
			
			String paymentMethod = req.getParameter("payment_method");
			
			Cart cart = cServ.getCartByUserId(acc.getUserid());
			
			List<CartItem> cartItems = cServ.getCartItemsByCartId(cart.getCartId());
			int totalPrice = 0;

			if (cartItems != null && !cartItems.isEmpty()) {
				totalPrice = cartItems.stream()
						.mapToInt(item -> item.getQuantity() * item.getProduct().getPprice()).sum();
			}
			
			String note = req.getParameter("note");
	        
			Timestamp odate = new Timestamp(new Date().getTime());
			
			order.setOid("order_" + acc.getUserid() + "_" + odate.getTime());
			order.setAccount(acc);
			order.setCost(totalPrice);
			order.setDeliveryinfo(dinfo.toString());
			order.setPayment(paymentMethod);
			order.setNote(note);
	        order.setOdate(odate);
	        order.setOstatus(OrderStatus.notPaidYet);
	        
	        oServ.addOrder(order);
	        
	        List<OrderDetail> orderDetails = new ArrayList<>();
	        for (CartItem item : cartItems) {
	            OrderDetailId orderDetailId = new OrderDetailId(order.getOid(), item.getProduct().getPid());
	            OrderDetail orderDetail = new OrderDetail();
	            orderDetail.setOdid(orderDetailId);
	            orderDetail.setOrder(order);
	            orderDetail.setProduct(item.getProduct());
	            orderDetail.setDquantity(item.getQuantity());
	            orderDetail.setDprice(item.getProduct().getPprice() * item.getQuantity());
	            
	            Product prod = item.getProduct();
	            prod.setPquantity(prod.getPquantity() - item.getQuantity());
	            if (prodServ.update(prod)) {
	            	orderDetails.add(orderDetail);
		            try {
		            	oServ.addOrderDetail(orderDetail);
		            } catch (Exception e) {
		            	e.printStackTrace();
		            }
	            }
	        }

	        // Thiết lập quan hệ hai chiều giữa Order và OrderDetail
	        order.setOrderDetails(orderDetails);
	        
	        oServ.updateOrder(order);
	        resp.sendRedirect(req.getContextPath() + "/user/checkout-confirm?oid=" + order.getOid());
		}
	}
}

package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Order;
import vn.starshopvn.entity.OrderDetail;
import vn.starshopvn.model.OrderSummary;

public interface OrderService {

	boolean addOrder(Order order);
	
	void setStatus(Order order, String status);
	
	void addOrderDetail(OrderDetail oDetail);

	void updateOrder(Order order);
	
	Order getOrder(String oid);
	
	public List<Order> getAllOrdersByUserId(String userid);
	
	public List<Order> getAllOrders();
	
	List<OrderSummary> getYearlyOrderSummary();

	List<OrderSummary> getMonthlyOrderSummary();

	int getTotalOrder();

	double getTotalRevenue();
}

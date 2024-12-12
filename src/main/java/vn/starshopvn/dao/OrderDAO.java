package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Order;
import vn.starshopvn.entity.OrderDetail;
import vn.starshopvn.model.OrderSummary;

public interface OrderDAO {

	void addOrder(Order order);
	
	void setStatus(Order order, String status);
	
	void addOrderDetail(OrderDetail oDetail);

	void updateOrder(Order order);

	Order getOrder(String oid);

	List<Order> getAllOrders();

	List<Order> getAllOrdersByUserId(String userid);

	List<OrderSummary> getYearlyOrderSummary();

	List<OrderSummary> getMonthlyOrderSummary();

	int getTotalOrder();

	double getTotalRevenue();

	List<OrderDetail> getOrderItems(String oid);
}

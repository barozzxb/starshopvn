package vn.starshopvn.service.impl;

import java.util.List;

import vn.starshopvn.dao.OrderDAO;
import vn.starshopvn.dao.impl.OrderDAOImpl;
import vn.starshopvn.entity.Order;
import vn.starshopvn.entity.OrderDetail;
import vn.starshopvn.model.OrderSummary;
import vn.starshopvn.service.OrderService;

public class OrderServiceImpl implements OrderService{

	OrderDAO oDAO = new OrderDAOImpl();
	
	@Override
	public boolean addOrder(Order order) {
		try {
			oDAO.addOrder(order);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void setStatus(Order order, String status) {
		oDAO.setStatus(order, status);
	}

	@Override
	public void addOrderDetail(OrderDetail oDetail) {
		oDAO.addOrderDetail(oDetail);
	}

	@Override
	public void updateOrder(Order order) {
		oDAO.updateOrder(order);
	}

	@Override
	public Order getOrder(String oid) {
		return oDAO.getOrder(oid);
	}

	@Override
	public List<Order> getAllOrdersByUserId(String userid) {
		return oDAO.getAllOrdersByUserId(userid);
	}

	@Override
	public List<Order> getAllOrders() {
		return oDAO.getAllOrders();
	}

	@Override
	public List<OrderSummary> getYearlyOrderSummary() {
		return oDAO.getYearlyOrderSummary();
	}

	@Override
	public List<OrderSummary> getMonthlyOrderSummary() {
		return oDAO.getMonthlyOrderSummary();
	}

	@Override
	public int getTotalOrder() {
		return oDAO.getTotalOrder();
	}

	@Override
	public double getTotalRevenue() {
		return oDAO.getTotalRevenue();
	}
}

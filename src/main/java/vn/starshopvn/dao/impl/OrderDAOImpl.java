package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.OrderDAO;
import vn.starshopvn.entity.Order;
import vn.starshopvn.entity.OrderDetail;
import vn.starshopvn.entity.OrderDetailId;
import vn.starshopvn.model.OrderSummary;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public void updateOrder(Order order) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(order);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	@Override
	public void addOrder(Order order) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}
	
	@Override
	public void setStatus(Order order, String status) {
		order.setOstatus(status);
		this.updateOrder(order);
	}

	@Override
	public void addOrderDetail(OrderDetail oDetail) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(oDetail);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
	}

	@Override
	public Order getOrder(String oid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Order order = enma.find(Order.class, oid);
		return order != null? order : null;
	}
	
	@Override
	public List<Order> getAllOrdersByUserId(String userid){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select od from Order od where od.account.userid = :userid";
		TypedQuery<Order> query = enma.createQuery(jpql, Order.class);
		query.setParameter("userid", userid);
		return query.getResultList();
	}
	
	@Override
	public List<Order> getAllOrders(){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select od from Order od";
		TypedQuery<Order> query = enma.createQuery(jpql, Order.class);
		return query.getResultList();
	}
	
	@Override
	public double getTotalRevenue() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select sum(od.cost) from Order od";
		TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}
	
	@Override
	public int getTotalOrder() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select count(od) from Order od";
		TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
		return query.getSingleResult().intValue();
	}
	
	@Override
	public List<OrderSummary> getMonthlyOrderSummary() {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT " +
                      "FUNCTION('YEAR', o.odate) AS year, " +
                      "FUNCTION('MONTH', o.odate) AS month, " +
                      "SUM(o.cost) AS totalCost " +
                      "FROM Order o " +
                      "GROUP BY FUNCTION('YEAR', o.odate), FUNCTION('MONTH', o.odate) " +
                      "ORDER BY year, month";

        TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
        List<Object[]> results = query.getResultList();

        return results.stream()
                      .map(row -> new OrderSummary(
                              (int) row[0],  // year
                              (int) row[1],  // month
                              (long) row[2]  // totalCost
                      ))
                      .toList();
    }
	
	@Override
	public List<OrderSummary> getYearlyOrderSummary() {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT " +
                      "FUNCTION('YEAR', o.odate) AS year, " +
                      "SUM(o.cost) AS totalCost " +
                      "FROM Order o " +
                      "GROUP BY FUNCTION('YEAR', o.odate) " +
                      "ORDER BY year";

        TypedQuery<Object[]> query = enma.createQuery(jpql, Object[].class);
        List<Object[]> results = query.getResultList();

        return results.stream()
                      .map(row -> new OrderSummary(
                              (int) row[0],  // year
                              (int) 0,
                              (long) row[1]  // total cost

                      ))
                      .toList();
    }
}

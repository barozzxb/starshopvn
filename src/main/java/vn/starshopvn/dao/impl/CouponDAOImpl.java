package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.CouponDAO;
import vn.starshopvn.entity.Coupon;


public class CouponDAOImpl implements CouponDAO{

	@Override
	public List<Coupon> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Coupon> cou = enma.createNamedQuery("coupon.findAll", Coupon.class);
		return cou.getResultList();
	}

	@Override
	public Coupon findById(String cid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Coupon coup = enma.find(Coupon.class, cid);
		return coup;
	}

	@Override
	public void addCoupon(Coupon coupon) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(coupon);
			trans.commit();
			System.out.println("Favorite added: " +coupon);
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(coupon); 
			trans.commit();
	
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public void deleteCoupon(String cid) throws Exception{
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Coupon coupon = enma.find(Coupon.class, cid);
			if (coupon !=null) {
				enma.remove(coupon);
			}
			else {
				throw new Exception("Khong tim thay");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}	
		
	}

}

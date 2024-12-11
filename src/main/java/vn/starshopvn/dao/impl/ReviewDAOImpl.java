package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.ReviewDAO;
import vn.starshopvn.entity.Review;

public class ReviewDAOImpl implements ReviewDAO {

	@Override
	public void insert(Review review) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(review);
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
	public void deleteById(String reviewId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Review review = enma.find(Review.class, reviewId);
			if (review !=null) {
				enma.remove(review);
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

	@Override
	public void update(Review review) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(review);
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
	public Review findById(String reviewId) {
		EntityManager enma = JPAConfig.getEntityManager();
		return enma.find(Review.class, reviewId);
	}

	@Override
	public List<Review> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Review> query = enma.createQuery("SELECT r FROM Review r", Review.class);
		return query.getResultList();
	}

	@Override
	public List<Review> findByProductId(String pid) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Review> query = enma.createQuery("SELECT r FROM Review r WHERE r.product.pid = :pid", Review.class);
		query.setParameter("pid", pid);
		return query.getResultList();
	}

	@Override
	public List<Review> findByUserId(String userid) {
		EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Review> query = enma.createQuery(
                "SELECT r FROM Review r WHERE r.account.userid = :userid", Review.class);
            query.setParameter("userid", userid);
            return query.getResultList();
	}

	@Override
	public double averageRatingByProductId(String pid) {
		EntityManager enma = JPAConfig.getEntityManager();
        Query query = enma.createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.pid = :pid");
        query.setParameter("pid", pid); // Tính điểm trung bình theo sản phẩm
        return (double) query.getSingleResult();
	}

}
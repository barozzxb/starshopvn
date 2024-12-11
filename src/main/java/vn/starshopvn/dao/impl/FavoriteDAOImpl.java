package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.FavoriteDAO;
import vn.starshopvn.entity.Favorite;


public class FavoriteDAOImpl implements FavoriteDAO{

	@Override
	public List<Favorite> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Favorite> fav = enma.createNamedQuery("favorite.findAll", Favorite.class);
		return fav.getResultList();
	}

	@Override
	public Favorite findById(String fid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Favorite favo = enma.find(Favorite.class, fid);
		return favo;
	}

	@Override
	public void insert(Favorite favorite) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(favorite);
			trans.commit();
			System.out.println("Favorite added: " +favorite);
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public void delete(String fid) throws Exception
	{
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Favorite favorite = enma.find(Favorite.class, fid);
			if (favorite !=null) {
				enma.remove(favorite);
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
	public List<Favorite> findByUserId(String userid) {
		EntityManager enma = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT f FROM Favorites f WHERE f.account.userid = :userid";
	        TypedQuery<Favorite> query = enma.createQuery(jpql, Favorite.class);
	        query.setParameter("userid", userid); 

	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (enma != null && enma.isOpen()) {
	            enma.close();
	        }
	    }

	}
}
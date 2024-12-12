package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.FavoriteDAO;
import vn.starshopvn.entity.Favorites;


public class FavoriteDAOImpl implements FavoriteDAO{

	@Override
	public List<Favorites> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Favorites> fav = enma.createNamedQuery("favorite.findAll", Favorites.class);
		return fav.getResultList();
	}

	@Override
	public Favorites findById(String fid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Favorites favo = enma.find(Favorites.class, fid);
		return favo;
	}

	@Override
	public void insert(Favorites favorite) {
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
			Favorites favorite = enma.find(Favorites.class, fid);
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
	public List<Favorites> findByUserId(String userid) {
		EntityManager enma = JPAConfig.getEntityManager();
	    try {
	        // JPQL truy vấn danh sách sản phẩm thuộc thể loại cụ thể
	        String jpql = "SELECT f FROM Favorites f WHERE f.account.userid = :userid";
	        TypedQuery<Favorites> query = enma.createQuery(jpql, Favorites.class);
	        query.setParameter("userid", userid); // Thiết lập giá trị gid cho tham số trong JPQL

	        return query.getResultList(); // Trả về danh sách sản phẩm
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Trả về null nếu xảy ra lỗi
	    } finally {
	        if (enma != null && enma.isOpen()) {
	            enma.close(); // Đảm bảo đóng EntityManager
	        }
	    }

	}



}
package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.ProductDAO;
import vn.starshopvn.entity.Product;

public class ProductDAOImpl implements ProductDAO{

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> pros = enma.createNamedQuery("product.findAll", Product.class);
		return pros.getResultList();
	}

	@Override
	public Product findById(String pid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Product pro = enma.find(Product.class, pid);
		return pro;
	}

	@Override
	public void insert(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
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
	public void update(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(product); // edit
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
	public List<Product> findByName(String pname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.pname like : pname";
		TypedQuery<Product> query= enma.createQuery(jpql, Product.class);
		query.setParameter("pname", "%" + pname +"%");
		return query.getResultList();
	}

	@Override
	public void delete(String pid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Product product = enma.find(Product.class, pid);
			if (product !=null) {
				enma.remove(product);
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
	public int countProducts() {
		 EntityManager em = JPAConfig.getEntityManager();
		 String jpql = "SELECT COUNT(p) FROM Product p";
		 TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		 return query.getSingleResult().intValue();
	}

	@Override
	public List<Product> top3new() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ORDER BY p.createdat DESC";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		query.setMaxResults(6);
		return query.getResultList();
	}

	
	@Override
	public List<Product> findByGenre(String gid) {
		 EntityManager enma = JPAConfig.getEntityManager();
		    try {
		        // JPQL truy vấn danh sách sản phẩm thuộc thể loại cụ thể
		        String jpql = "SELECT p FROM Product p WHERE p.genre.gid = :gid";
		        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		        query.setParameter("gid", gid); // Thiết lập giá trị gid cho tham số trong JPQL

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

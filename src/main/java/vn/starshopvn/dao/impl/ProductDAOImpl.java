package vn.starshopvn.dao.impl;

import java.util.List;
<<<<<<< Updated upstream

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
=======
import jakarta.persistence.EntityManager;
>>>>>>> Stashed changes
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.ProductDAO;
import vn.starshopvn.entity.Product;

<<<<<<< Updated upstream
public class ProductDAOImpl implements ProductDAO{

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> pros = enma.createNamedQuery("product.findAll", Product.class);
		return pros.getResultList();
	}

	@Override
	public Product findById(String productId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Product pro = enma.find(Product.class, productId);
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
		String jpql = "SELECT p FROM Product p WHERE p.productName like : pname";
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
=======
public class ProductDAOImpl implements ProductDAO {


    /**
     * Lấy 3 sản phẩm mới nhất, sắp xếp theo ngày tạo giảm dần
     */
    @Override
    public List<Product> top3new() {
        EntityManager em = JPAConfig.getEntityManager();
        // JPQL để lấy 3 sản phẩm mới nhất theo ngày tạo
        String jpql = "SELECT p FROM Product p ORDER BY p.createdat DESC";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        
        // Giới hạn lấy 3 sản phẩm
        query.setMaxResults(3);
        
        return query.getResultList();
    }

    @Override
    public int countProducts() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(p) FROM Product p";
        return em.createQuery(jpql, Long.class).getSingleResult().intValue();
    }

    @Override
    public void delete(String pid) throws Exception {
        EntityManager em = JPAConfig.getEntityManager();
        var trans = em.getTransaction();
        try {
            trans.begin();
            Product product = em.find(Product.class, pid);
            if (product != null) {
                em.remove(product);
            } else {
                throw new Exception("Không tìm thấy sản phẩm để xóa.");
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findByName(String pname) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.productName LIKE :pname";
        return em.createQuery(jpql, Product.class)
                .setParameter("pname", "%" + pname + "%")
                .getResultList();
    }

    @Override
    public void update(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        var trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        var trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Product findById(String productId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Product.class, productId);
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }
>>>>>>> Stashed changes
}

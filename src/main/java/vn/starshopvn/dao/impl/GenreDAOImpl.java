package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.GenreDAO;
import vn.starshopvn.entity.Genre;

public class GenreDAOImpl implements GenreDAO{
	
	@Override
	public void insert(Genre genre) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(genre);
			trans.commit();
		}catch(Exception e) {
	        e.printStackTrace();
	        trans.rollback();
	        throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public void update(Genre genre) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(genre);
			trans.commit();
		}catch(Exception e) {
	        e.printStackTrace();
	        trans.rollback();
	        throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public void delete(String gid) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Genre genre = enma.find(Genre.class, gid);
			if (genre != null) {
				enma.remove(genre);
			} else {
				throw new Exception("THe genre doesn't exist!");
			}
			trans.commit();
		}catch(Exception e) {
	        e.printStackTrace();
	        trans.rollback();
		}finally {
			enma.close();
		}
	}

	@Override
	public List<Genre> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Genre> list = enma.createNamedQuery("genre.findAll", Genre.class);
		return list.getResultList();
	}

	@Override
	public Genre findById(String gid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Genre genre = enma.find(Genre.class, gid);
		return genre;
	}

	@Override
	public Genre findByName(String gname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select g from Genre g where gname like :gname";
		TypedQuery<Genre> list = enma.createQuery(jpql, Genre.class);
		list.setParameter("gname", gname);
		return list.getSingleResult();
	}

	@Override
	public int countProducts(String gid) {
//		EntityManager enma = JPAConfig.getEntityManager();
//		String jpql = "select g from Genre g where gname like :gname";
//		TypedQuery<Genre> list = enma.createQuery(jpql, Genre.class);
//		list.setParameter("gname", gname);
//		return list.getSingleResult();
		return 0;
	}

	@Override
	public int countGenres() {
		 EntityManager em = JPAConfig.getEntityManager();
		 String jpql = "SELECT COUNT(g) FROM Genre g";
		 TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		 return query.getSingleResult().intValue();
	}
}

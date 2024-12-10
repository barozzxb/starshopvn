package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.AccountDAO;
import vn.starshopvn.entity.Account;

public class AccountDAOImpl implements AccountDAO{

	@Override
	public void insert(Account account) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(account);
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
	public void deleteById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Account acc = enma.find(Account.class, id);
			if (acc != null) {
				enma.remove(acc);
			}else {
				throw new Exception("Doesn't exist!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Account account) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(account);
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
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		Query listacc = enma.createQuery("select count(a) from Account a where a.role.roleid = 2");
		return ((Long)listacc.getSingleResult()).intValue();
	}

	@Override
	public Account findById(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Account acc = enma.find(Account.class, id);
		return acc;
	}

	@Override
	public List<Account> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Account> listacc = enma.createNamedQuery("Account.findAll", Account.class);
		return listacc.getResultList();
	}

	@Override
	public List<Account> top5Account() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT a FROM Account a WHERE a.role.roleid = 2 ORDER BY a.createdat DESC";
		TypedQuery<Account> query = em.createQuery(jpql, Account.class);
		query.setMaxResults(5);
		return query.getResultList();
	}
	
	@Override
	public void setDeactive(String userid) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			Account acc = enma.find(Account.class, userid);
			acc.setDeactivated(true);
			trans.begin();
			enma.merge(acc);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}

	@Override
	public void setActive(String userid) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			Account acc = enma.find(Account.class, userid);
			acc.setDeactivated(false);
			trans.begin();
			enma.merge(acc);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
	
	@Override
	public Account findByEmail(String email) {
	    EntityManager enma = JPAConfig.getEntityManager();
	    try {
	    	String jpql = "SELECT a FROM Account a WHERE a.email = :email AND a.isDeactivated = false";
	        TypedQuery<Account> query = enma.createQuery(jpql, Account.class);
	        query.setParameter("email", email);
	        return query.getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;  
	    } finally {
	        enma.close();
	    }
	}

}

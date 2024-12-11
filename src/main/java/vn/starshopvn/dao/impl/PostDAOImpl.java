package vn.starshopvn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.PostDAO;
import vn.starshopvn.entity.Post;

public class PostDAOImpl implements PostDAO{

	@Override
	public List<Post> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Post> query = enma.createNamedQuery("post.findAll", Post.class);
		return query.getResultList();
	}

	@Override
	public List<Post> findAllCensoredPost(){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select po from Post po where po.iscensored = true";
		TypedQuery<Post> query = enma.createQuery(jpql, Post.class);
		return query.getResultList();
	}
	
	@Override
	public List<Post> findAllUncensoredPost(){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select po from Post po where po.iscensored = false";
		TypedQuery<Post> query = enma.createQuery(jpql, Post.class);
		return query.getResultList();
	}
	@Override
	public void censorPost(String poid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uncensorPost(String poid) {
		// TODO Auto-generated method stub
		
	}

}

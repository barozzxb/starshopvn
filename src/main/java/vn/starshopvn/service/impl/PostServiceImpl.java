package vn.starshopvn.service.impl;

import java.util.List;

import vn.starshopvn.dao.PostDAO;
import vn.starshopvn.dao.impl.PostDAOImpl;
import vn.starshopvn.entity.Post;
import vn.starshopvn.service.PostService;

public class PostServiceImpl implements PostService{

	PostDAO poDAO=new PostDAOImpl();
	@Override
	public List<Post> findAll() {
		return poDAO.findAll();
	}

}

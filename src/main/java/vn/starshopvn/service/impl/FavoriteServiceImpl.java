package vn.starshopvn.service.impl;

import java.util.List;

import vn.starshopvn.dao.FavoriteDAO;
import vn.starshopvn.dao.impl.FavoriteDAOImpl;
import vn.starshopvn.entity.Favorite;
import vn.starshopvn.service.FavoriteService;


public class FavoriteServiceImpl implements FavoriteService{

	FavoriteDAO favDao=new FavoriteDAOImpl();
	@Override
	public List<Favorite> findAll() {
		return favDao.findAll();
	}

	@Override
	public Favorite findById(String fid) {
		return favDao.findById(fid);
	}

	@Override
	public boolean insert(Favorite favorite) {
		if (favDao.findById(favorite.getFid()) != null) {
			return false;
		}
		try {
			favDao.insert(favorite);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(String fid) {
		try {
			favDao.delete(fid);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Favorite> findByUserId(String userid) {
		return favDao.findByUserId(userid);
	}

}
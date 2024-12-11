package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Favorite;

public interface FavoriteDAO {

	List<Favorite> findByUserId(String userid);

	void delete(String fid) throws Exception;

	void insert(Favorite favorite);

	Favorite findById(String fid);

	List<Favorite> findAll();

}

package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Favorite;

public interface FavoriteService {

	List<Favorite> findByUserId(String userid);

	boolean delete(String fid);

	boolean insert(Favorite favorite);

	Favorite findById(String fid);

	List<Favorite> findAll();

}

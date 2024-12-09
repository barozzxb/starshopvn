package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Favorites;

public interface FavoriteService {
	List<Favorites> findAll(); 
	Favorites findById(String fid); 
	boolean insert(Favorites favorite);  
	boolean delete (String fid); 
	List<Favorites> findByUserId(String userid);

}

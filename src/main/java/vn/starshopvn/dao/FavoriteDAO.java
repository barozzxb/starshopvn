package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Favorites;

public interface FavoriteDAO {
	List<Favorites> findAll(); 
	Favorites findById(String fid); 
	void insert(Favorites favorite);  
	void delete (String fid)throws Exception; 
	List<Favorites> findByUserId(String userid);
	

}
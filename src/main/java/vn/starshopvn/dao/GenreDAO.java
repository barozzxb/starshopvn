package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Genre;

public interface GenreDAO {

	int countGenres();

	int countProducts(String gid);

	Genre findByName(String gname);

	Genre findById(String gid);

	List<Genre> findAll();

	void delete(String gid);

	void update(Genre genre);

	void insert(Genre genre);

}

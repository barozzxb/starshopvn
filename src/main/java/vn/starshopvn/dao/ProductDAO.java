package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Product;

public interface ProductDAO {

	List<Product> top3new();

	int countProducts();

	void delete(String pid) throws Exception;

	List<Product> findByName(String pname);

	void update(Product product);

	void insert(Product product);

	Product findById(String productId);

	List<Product> findAll();
	
	List<Product> findByGenre(String gid);

	
}

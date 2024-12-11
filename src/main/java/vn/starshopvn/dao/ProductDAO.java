package vn.starshopvn.dao;

import java.time.LocalDateTime;
import java.util.List;

import vn.starshopvn.entity.Product;

public interface ProductDAO {

	List<Product> top3new();

	int countProducts();

	void delete(String pid) throws Exception;

	List<Product> findByName(String pname);

	void update(Product product);

	void insert(Product product);

	Product findById(String pid);

	List<Product> findAll();
	
	List<Product> findByGenre(String gid);

	List<Product> searchProducts(String query, Integer rating, LocalDateTime createdAtFrom, LocalDateTime createdAtTo,
			Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy,
			boolean ascending);

	
}

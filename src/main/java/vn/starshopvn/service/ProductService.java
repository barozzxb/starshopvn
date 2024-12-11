package vn.starshopvn.service;

import java.time.LocalDateTime;
import java.util.List;

import vn.starshopvn.entity.Product;

public interface ProductService {

	List<Product> top3new();

	int countProducts();

	boolean update(Product product);

	boolean delete(String pid);

	boolean insert(Product product);

	List<Product> findByName(String pname);

	Product findById(String pid);

	List<Product> findAll();

	List<Product> findByGenre(String gid);

	List<Product> searchProducts(String query, Integer rating, LocalDateTime createdAtFrom, LocalDateTime createdAtTo,
			Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy,
			boolean ascending);
}

package vn.starshopvn.service;

import java.sql.Timestamp;
import java.util.List;

import vn.starshopvn.entity.Product;
import vn.starshopvn.model.TopSellingProduct;

public interface ProductService {

	List<Product> top3new();

	int countProducts();

	boolean update(Product product);

	boolean delete(String pid);

	boolean insert(Product product);

	List<Product> findByName(String pname);

	Product findById(String pid);

	List<Product> findAll();
	
	List<Product> findAll(int page, int pagesize);

	List<Product> searchAndFilter(List<String> genreIds, Integer maxPrice);

	List<Product> filterByPrice(int maxPrice);

	List<Product> filterByGenre(List<String> genreIds);


	List<TopSellingProduct> topSellingProducts();

	List<Product> findByGenre(String gid);

	List<Product> searchProducts(String query, Integer rating, Timestamp createdAtFrom, Timestamp createdAtTo,
			Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy,
			boolean ascending);

}

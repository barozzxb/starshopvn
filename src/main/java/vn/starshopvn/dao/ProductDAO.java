package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Product;
import vn.starshopvn.model.TopSellingProduct;

public interface ProductDAO {

	List<Product> top3new();

	int countProducts();

	void delete(String pid) throws Exception;

	List<Product> findByName(String pname);

	void update(Product product);

	void insert(Product product);

	Product findById(String productId);

	List<Product> findAll();

	List<Product> searchAndFilter(List<String> genreIds, Integer maxPrice);

	List<Product> filterByPrice(int maxPrice);

	List<Product> filterByGenre(List<String> genreIds);

	List<Product> searchProducts(String keyword);

	List<TopSellingProduct> topSellingProducts();

	List<Product> findAll(int page, int pagesize);

	List<Product> findByGenre(String gid);

}

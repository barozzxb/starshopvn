package vn.starshopvn.service.impl;

import java.sql.Timestamp;
import java.util.List;

import vn.starshopvn.dao.ProductDAO;
import vn.starshopvn.dao.impl.ProductDAOImpl;
import vn.starshopvn.entity.Product;
import vn.starshopvn.model.TopSellingProduct;
import vn.starshopvn.service.ProductService;

public class ProductServiceImpl implements ProductService{

	ProductDAO pDAO = new ProductDAOImpl();
	
	@Override
	public List<Product> findAll() {
		return pDAO.findAll();
	}

	@Override
	public Product findById(String pid) {
		return pDAO.findById(pid);
	}

	@Override
	public List<Product> findByName(String pname) {
		return pDAO.findByName(pname);
	}

	@Override
	public boolean insert(Product product) {
		if (pDAO.findById(product.getPid()) != null) {
			return false;
		}
		try {
			pDAO.insert(product);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String pid){
		try {
			pDAO.delete(pid);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			pDAO.update(product);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int countProducts() {
		return pDAO.countProducts();
	}

	@Override
	public List<Product> top3new() {
		return pDAO.top3new();
	}

	@Override
	public List<Product> searchAndFilter(List<String> genreIds, Integer maxPrice) {
		return pDAO.searchAndFilter(genreIds, maxPrice);
	}

	@Override
	public List<Product> filterByPrice(int maxPrice) {
		return pDAO.filterByPrice(maxPrice);
	}

	@Override
	public List<Product> filterByGenre(List<String> genreIds) {
		return pDAO.filterByGenre(genreIds);
	}

	@Override
	public List<Product> searchProducts(String query, Integer rating, Timestamp createdAtFrom, Timestamp createdAtTo, Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy, boolean ascending) {
	    return pDAO.searchProducts(query, rating, createdAtFrom, createdAtTo, minPrice, maxPrice, minQuantity, maxQuantity, sortBy, ascending);
	}
	
	@Override
	public List<TopSellingProduct> topSellingProducts(){
		return pDAO.topSellingProducts();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		return pDAO.findAll(page, pagesize);
	}
	
	@Override
	public List<Product> findByGenre(String gid){
		return pDAO.findByGenre(gid);
	}
	
}

package vn.starshopvn.service.impl;

import java.util.List;

import vn.starshopvn.dao.ProductDAO;
import vn.starshopvn.dao.impl.ProductDAOImpl;
import vn.starshopvn.entity.Product;
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
<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes
}

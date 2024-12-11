package vn.starshopvn.service;

import java.util.List;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import vn.starshopvn.entity.Product;

public interface ProductService {

<<<<<<< Updated upstream
	List<Product> top3new();

	int countProducts();

	boolean update(Product product);

	boolean delete(String pid);

	boolean insert(Product product);

	List<Product> findByName(String pname);

	Product findById(String pid);

	List<Product> findAll();
=======
    // Các phương thức hiện có
    List<Product> top3new();
    int countProducts();
    boolean update(Product product);
    boolean delete(String pid);
    boolean insert(Product product);
    List<Product> findByName(String pname);
    Product findById(String pid);
    List<Product> findAll();

>>>>>>> Stashed changes

}

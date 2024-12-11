package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Review;

public interface ReviewDAO {

	double averageRatingByProductId(String pid);

	List<Review> findByUserId(String userid);

	List<Review> findByProductId(String pid);

	List<Review> findAll();

	Review findById(String reviewId);

	void update(Review review);

	void deleteById(String reviewId) throws Exception;

	void insert(Review review);

}

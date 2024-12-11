package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Review;

public interface ReviewService {

	double averageRatingByProductId(String pid);

	List<Review> findByUserId(String userid);

	List<Review> findByProductId(String pid);

	List<Review> findAll();

	Review findById(String reviewId);

	void update(Review review);

	void deleteById(String reviewId) throws Exception;

	void insert(Review review);

}

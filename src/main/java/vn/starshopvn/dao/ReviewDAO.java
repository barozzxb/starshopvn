package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Review;

public interface ReviewDAO {

    void insert(Review review); 

    void deleteById(String reviewId) throws Exception; 

    void update(Review review); 

    Review findById(String reviewId); 

    List<Review> findAll(); 

    List<Review> findByProductId(String pid); 

    List<Review> findByUserId(String userid);

    double averageRatingByProductId(String pid); 
}
package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.Review;

public interface ReviewService {
	
	List<Review> getAll();
		
	List<Review> get(int hotelId, int userId);
	
	List<Review> getByUserId(int userId);
	
	List<Review> getByHotelId(int hotelId);
	
	Review add(Review review);
	
	List<Review> addAll(List<Review> reviews);
	
	HashMap<String, Review> upadte(Review review);
	
	String delete(int reviewId);
}

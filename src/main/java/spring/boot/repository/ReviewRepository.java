package spring.boot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByUid(int userId);
	
	List<Review> findByHotelId(int hotelId);
	
	List<Review> findByHotelIdAndUid(int hotelId, int userId);
}
package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.Review;
import spring.boot.repository.ReviewRepository;
import spring.boot.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository repository;

	
	@Override
	public List<Review> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Review> get(int hotelId, int userId) {
		return repository.findByHotelIdAndUid(hotelId, userId);
	}

	@Override
	public Review add(Review review) {
		return repository.save(review);
	}

	@Override
	public List<Review> addAll(List<Review> review) {
		return repository.saveAll(review);
	}

	@Override
	public HashMap<String, Review> upadte(Review review) {
		HashMap<String, Review> map = new HashMap<>();
		Review existingReview = repository.findById(review.getReviewId()).orElse(null);
		if (existingReview != null) {
			repository.save(review);
			map.put("found", review);
		} else {
			map.put("not found", existingReview);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		Review existingReview = repository.findById(id).orElse(null);
		if (existingReview != null) {
			repository.delete(existingReview);
			return "found";
		} else {
			return "not found";
		}
	}

	@Override
	public List<Review> getByUserId(int userId) {
		return repository.findByUid(userId);
	}

	@Override
	public List<Review> getByHotelId(int hotelId) {
		return repository.findByHotelId(hotelId);
	}
}

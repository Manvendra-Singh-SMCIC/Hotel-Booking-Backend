package spring.boot.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.Wishlist;
import spring.boot.repository.WishlistRepository;
import spring.boot.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository repository;

	
	@Override
	public List<Wishlist> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Wishlist> get(int hotelId, int userId) {
		return repository.findByHidAndUid(hotelId, userId);
	}

	@Override
	public Wishlist add(Wishlist wishlist) {
		return repository.save(wishlist);
	}

	@Override
	public String delete(int id) {
		Wishlist existingWish = repository.findById(id).orElse(null);
		if (existingWish != null) {
			repository.delete(existingWish);
			return "found";
		} else {
			return "not found";
		}
	}

	@Override
	public List<Wishlist> getByUserId(int userId) {
		return repository.findByUid(userId);
	}

	@Override
	public List<Wishlist> getByHotelId(int hotelId) {
		return repository.findByHid(hotelId);
	}
}

package spring.boot.service;

import java.util.List;

import spring.boot.entity.Wishlist;

public interface WishlistService {
	
	List<Wishlist> getAll();
	
	Wishlist add(Wishlist wishlist);
		
	List<Wishlist> get(int hotelId, int userId);
	
	List<Wishlist> getByUserId(int userId);
	
	List<Wishlist> getByHotelId(int hotelId);
	
	String delete(int id);
}

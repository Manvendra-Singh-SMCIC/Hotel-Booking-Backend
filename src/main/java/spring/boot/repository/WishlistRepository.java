package spring.boot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findByUid(int userId);
	
	List<Wishlist> findByHid(int hotelId);
	
	List<Wishlist> findByHidAndUid(int hotelId, int userId);
}
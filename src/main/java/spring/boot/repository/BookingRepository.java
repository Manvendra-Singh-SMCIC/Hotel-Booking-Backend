package spring.boot.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByUid(int userId, Sort sort);
	
	List<Booking> findByHid(int hotelId);
	
	List<Booking> findByHidAndUid(int hotelId, int userId);
}
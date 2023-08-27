package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.HotelImages;

public interface HotelImageRepository extends JpaRepository<HotelImages, Integer> {
	
	public List<HotelImages> findByHid(int hotelId);
}
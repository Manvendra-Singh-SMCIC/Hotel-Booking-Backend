package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import spring.boot.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	public List<Hotel> findByName(String name);

	List<Hotel> findByNameStartingWith(@Param("name") String name);

	List<Hotel> findByNameEndingWith(@Param("name") String name);
	
	List<Hotel> findByNameContainingIgnoreCase(@Param("name") String name);
}
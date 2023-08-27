package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.RoomImages;

public interface RoomImageRepository extends JpaRepository<RoomImages, Integer> {
	
	public List<RoomImages> findByRid(int roomId);
}
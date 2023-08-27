package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	
	public List<RoomType> findByHid(int userId);
}
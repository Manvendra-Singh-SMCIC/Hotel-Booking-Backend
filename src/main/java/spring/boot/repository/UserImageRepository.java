package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.UserImages;

public interface UserImageRepository extends JpaRepository<UserImages, Integer> {
	
	public List<UserImages> findByUid(int userId);
}
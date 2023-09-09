package spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import spring.boot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByName(String name);
	
	public List<User> findByEmail(String email);
	
	public List<User> findByEmailAndPassword(String email, String password);

    List<User> findByNameStartingWith(@Param("name") String name);

    List<User> findByNameEndingWith(@Param("name") String name);
}
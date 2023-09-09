package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.User;

public interface UserService {
	
	List<User> getAll();
	
	User get(int id);
	
	User add(User user);
	
	List<User> addAll(List<User> user);
	
	HashMap<String, User> upadte(User user);
	
	String delete(int id);
		
	List<User> findByNameStartingWith(String name);
	
	List<User> findByNameEndingWith(String name);

	List<User> findByName(String name);
	
	List<User> findByEmail(String email);
	
	List<User> findByEmailAndPassword(String email, String password);
}

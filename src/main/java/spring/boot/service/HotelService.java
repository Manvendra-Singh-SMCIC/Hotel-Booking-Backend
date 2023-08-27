package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.Hotel;

public interface HotelService {
	
	List<Hotel> getAll();
	
	Hotel get(int id);
	
	Hotel add(Hotel hotel);
	
	List<Hotel> addAll(List<Hotel> hotel);
	
	HashMap<String, Hotel> upadte(Hotel hotel);
	
	String delete(int id);
	
	List<Hotel> findByNameStartingWith(String name);
	
	List<Hotel> findByNameEndingWith(String name);

	List<Hotel> findByName(String name);
	
	List<Hotel> findByNameLike(String name);
}

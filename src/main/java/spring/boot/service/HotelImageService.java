package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.HotelImages;

public interface HotelImageService {
	
	List<HotelImages> get(int userId);
	
	HotelImages add(HotelImages hotelImages);
	
	List<HotelImages> addAll(List<HotelImages> hotelImages);
	
	HashMap<String, HotelImages> update(HotelImages hotelImages);
	
	String delete(int id);
}

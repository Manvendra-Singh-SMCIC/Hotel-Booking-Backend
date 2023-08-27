package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.RoomType;

public interface RoomTypeService {
	
	RoomType get(int id);

	List<RoomType> getByHotelId(int hotelId);

	RoomType add(RoomType roomType);
	
	List<RoomType> addAll(List<RoomType> roomTypes);

	HashMap<String, RoomType> update(RoomType roomType);
	
	String delete(int id);
}

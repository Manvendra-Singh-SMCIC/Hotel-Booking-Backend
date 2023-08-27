package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.RoomImages;

public interface RoomImageService {

	List<RoomImages> get(int userId);

	RoomImages add(RoomImages roomImage);

	List<RoomImages> addAll(List<RoomImages> roomImages);

	HashMap<String, RoomImages> update(RoomImages roomImage);

	String delete(int id);
}

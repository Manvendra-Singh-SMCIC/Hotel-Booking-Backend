package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.RoomType;
import spring.boot.repository.RoomTypeRepository;
import spring.boot.service.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeRepository repository;
	
	@Override
	public RoomType get(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<RoomType> getByHotelId(int hotelId) {
		return repository.findByHid(hotelId);
	}

	@Override
	public RoomType add(RoomType roomType) {
		return repository.save(roomType);
	}
	
	@Override
	public List<RoomType> addAll(List<RoomType> roomTypes) {
		return repository.saveAll(roomTypes);
	}

	@Override
	public HashMap<String, RoomType> update(RoomType roomType) {
		HashMap<String, RoomType> map = new HashMap<>();
		RoomType existingRoomType = repository.findById(roomType.getRoomId()).orElse(null);
		if (existingRoomType != null) {
			repository.save(roomType);
			map.put("found", roomType);
		} else {
			map.put("not found", existingRoomType);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		RoomType existingRoomType = repository.findById(id).orElse(null);
		if (existingRoomType != null) {
			repository.delete(existingRoomType);
			return "found";
		} else {
			return "not found";
		}
	}
}

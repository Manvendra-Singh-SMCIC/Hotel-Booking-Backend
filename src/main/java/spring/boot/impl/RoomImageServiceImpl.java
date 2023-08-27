package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.RoomImages;
import spring.boot.repository.RoomImageRepository;
import spring.boot.service.RoomImageService;

@Service
public class RoomImageServiceImpl implements RoomImageService {

	@Autowired
	private RoomImageRepository repository;

	@Override
	public List<RoomImages> get(int roomId) {
		return repository.findByRid(roomId);
	}

	@Override
	public RoomImages add(RoomImages roomImages) {
		return repository.save(roomImages);
	}

	@Override
	public List<RoomImages> addAll(List<RoomImages> roomImages) {
		return repository.saveAll(roomImages);
	}

	@Override
	public HashMap<String, RoomImages> update(RoomImages roomImages) {
		HashMap<String, RoomImages> map = new HashMap<>();
		RoomImages existingRoomImages = repository.findById(roomImages.getRoomImageId()).orElse(null);
		if (existingRoomImages != null) {
			repository.save(roomImages);
			map.put("found", roomImages);
		} else {
			map.put("not found", existingRoomImages);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		RoomImages existingHotelImages = repository.findById(id).orElse(null);
		if (existingHotelImages != null) {
			repository.delete(existingHotelImages);
			return "found";
		} else {
			return "not found";
		}
	}
}

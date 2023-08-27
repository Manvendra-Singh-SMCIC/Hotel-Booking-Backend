package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.HotelImages;
import spring.boot.repository.HotelImageRepository;
import spring.boot.service.HotelImageService;

@Service
public class HotelImageServiceImpl implements HotelImageService {

	@Autowired
	private HotelImageRepository repository;

	@Override
	public List<HotelImages> get(int userId) {
		return repository.findByHid(userId);
	}

	@Override
	public HotelImages add(HotelImages hotelImages) {
		return repository.save(hotelImages);
	}
	
	@Override
	public List<HotelImages> addAll(List<HotelImages> hotelImages) {
		return repository.saveAll(hotelImages);
	}

	@Override
	public HashMap<String, HotelImages> update(HotelImages hotelImages) {
		HashMap<String, HotelImages> map = new HashMap<>();
		HotelImages existingHotelImage = repository.findById(hotelImages.getHotelImageId()).orElse(null);
		if (existingHotelImage != null) {
			repository.save(hotelImages);
			map.put("found", hotelImages);
		} else {
			map.put("not found", existingHotelImage);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		HotelImages existingHotelImages= repository.findById(id).orElse(null);
		if (existingHotelImages != null) {
			repository.delete(existingHotelImages);
			return "found";
		} else {
			return "not found";
		}
	}
}

package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.Hotel;
import spring.boot.repository.HotelRepository;
import spring.boot.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository repository;

	@Override
	public List<Hotel> getAll() {
		return repository.findAll();
	}

	@Override
	public Hotel get(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Hotel add(Hotel hotel) {
		return repository.save(hotel);
	}
	
	@Override
	public List<Hotel> addAll(List<Hotel> hotel) {
		return repository.saveAll(hotel);
	}

	@Override
	public HashMap<String, Hotel> upadte(Hotel hotel) {
		HashMap<String, Hotel> map = new HashMap<>();
		Hotel existingHotel = repository.findById(hotel.getHotelId()).orElse(null);
		if (existingHotel != null) {
			repository.save(hotel);
			map.put("found", hotel);
		} else {
			map.put("not found", existingHotel);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		Hotel existingHotel = repository.findById(id).orElse(null);
		if (existingHotel != null) {
			repository.delete(existingHotel);
			return "found";
		} else {
			return "not found";
		}
	}

	@Override
	public List<Hotel> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Hotel> findByNameStartingWith(String name) {
		return repository.findByNameStartingWith(name);
	}

	@Override
	public List<Hotel> findByNameEndingWith(String name) {
		return repository.findByNameEndingWith(name);
	}

	@Override
	public List<Hotel> findByNameLike(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

}

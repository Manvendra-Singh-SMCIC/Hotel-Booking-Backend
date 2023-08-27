package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import spring.boot.entity.Booking;
import spring.boot.repository.BookingRepository;
import spring.boot.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository repository;

	@Override
	public List<Booking> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Booking> get(int hotelId, int userId) {
		return repository.findByHidAndUid(hotelId, userId);
	}

	@Override
	public Booking add(Booking review) {
		return repository.save(review);
	}

	@Override
	public List<Booking> addAll(List<Booking> review) {
		return repository.saveAll(review);
	}

	@Override
	public HashMap<String, Booking> upadte(Booking review) {
		HashMap<String, Booking> map = new HashMap<>();
		Booking existingBooking = repository.findById(review.getBookingId()).orElse(null);
		if (existingBooking != null) {
			repository.save(review);
			map.put("found", review);
		} else {
			map.put("not found", existingBooking);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		Booking existingBooking = repository.findById(id).orElse(null);
		if (existingBooking != null) {
			repository.delete(existingBooking);
			return "found";
		} else {
			return "not found";
		}
	}

	@Override
	public List<Booking> getByUserId(int userId) {
		Sort sort = Sort.by(new Order(Direction.DESC, "timestamp"));
		return repository.findByUid(userId, sort);
	}

	@Override
	public List<Booking> getByHotelId(int hotelId) {
		return repository.findByHid(hotelId);
	}
}

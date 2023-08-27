package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.Booking;

public interface BookingService {

	List<Booking> getAll();

	List<Booking> get(int hotelId, int userId);

	List<Booking> getByUserId(int userId);

	List<Booking> getByHotelId(int hotelId);

	Booking add(Booking booking);

	List<Booking> addAll(List<Booking> reviews);

	HashMap<String, Booking> upadte(Booking booking);

	String delete(int reviewId);
}

package spring.boot.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.service.BookingService;
import spring.boot.entity.Booking;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

	@Autowired
	private BookingService service;

	@GetMapping("getAll")
	public ResponseEntity<Response<Booking>> getAll() {
		List<Booking> list = service.getAll();
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Booking>(list, message));
	}

	@GetMapping("getByHotelId")
	public ResponseEntity<Response<Booking>> getByHotelId(@RequestParam int hotelId) {
		List<Booking> list = service.getByHotelId(hotelId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Booking>(list, message));
	}

	@GetMapping("getByUserId")
	public ResponseEntity<Response<Booking>> getByUserId(@RequestParam int userId) {
		List<Booking> list = service.getByUserId(userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Booking>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<Booking>> get(@RequestParam int hotelId, @RequestParam int userId) {
		List<Booking> list = service.get(hotelId, userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Booking>(list, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody Booking booking) {
		Timestamp currentTimestamps = new Timestamp(System.currentTimeMillis());
        booking.setTimestamp(currentTimestamps);
		service.add(booking);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestBody Booking booking) {
		HashMap<String, Booking> map = service.upadte(booking);
		if (map.containsKey("found")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("updated"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("not found"));
		}
	}

	@DeleteMapping("delete")
	public ResponseEntity<ResponseMessage> update(@RequestParam int id) {
		String response = service.delete(id);
		if (response == "found") {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("deleted"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("not found"));
		}
	}
}

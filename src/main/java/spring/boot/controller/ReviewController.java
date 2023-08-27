package spring.boot.controller;

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

import spring.boot.service.ReviewService;
import spring.boot.entity.Review;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

	@Autowired
	private ReviewService service;
	
	@GetMapping("getAll")
	public ResponseEntity<Response<Review>> getAll() {
		List<Review> list = service.getAll();
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Review>(list, message));
	}

	@GetMapping("getByHotelId")
	public ResponseEntity<Response<Review>> getByHotelId(@RequestParam int hotelId) {
		List<Review> list = service.getByHotelId(hotelId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Review>(list, message));
	}

	@GetMapping("getByUserId")
	public ResponseEntity<Response<Review>> getByUserId(@RequestParam int userId) {
		List<Review> list = service.getByUserId(userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Review>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<Review>> get(@RequestParam int hotelId, @RequestParam int userId) {
		List<Review> list = service.get(hotelId, userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Review>(list, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody Review review) {
		service.add(review);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PostMapping("addAll")
	public ResponseEntity<ResponseMessage> addAll(@RequestBody List<Review> reviews) {
		service.addAll(reviews);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestBody Review review) {
		HashMap<String, Review> map = service.upadte(review);
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

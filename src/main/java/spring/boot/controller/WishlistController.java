package spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.service.WishlistService;
import spring.boot.entity.Wishlist;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService service;
	
	@GetMapping("getAll")
	public ResponseEntity<Response<Wishlist>> getAll() {
		List<Wishlist> list = service.getAll();
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Wishlist>(list, message));
	}

	@GetMapping("getByHotelId")
	public ResponseEntity<Response<Wishlist>> getByHotelId(@RequestParam int hotelId) {
		List<Wishlist> list = service.getByHotelId(hotelId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Wishlist>(list, message));
	}

	@GetMapping("getByUserId")
	public ResponseEntity<Response<Wishlist>> getByUserId(@RequestParam int userId) {
		List<Wishlist> list = service.getByUserId(userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Wishlist>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<Wishlist>> get(@RequestParam int hotelId, @RequestParam int userId) {
		List<Wishlist> list = service.get(hotelId, userId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Wishlist>(list, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody Wishlist review) {
		service.add(review);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
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

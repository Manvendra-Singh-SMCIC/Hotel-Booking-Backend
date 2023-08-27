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

import spring.boot.service.HotelService;
import spring.boot.entity.Hotel;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

	@Autowired
	private HotelService service;

	@GetMapping("getAll")
	public ResponseEntity<Response<Hotel>> getAll() {
		List<Hotel> list = service.getAll();
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<Hotel>> get(@RequestParam int id) {
		boolean found = service.get(id) != null;
		String message = found ? "found" : "not found";
		List<Hotel> user = found ? List.of(service.get(id)) : List.of();
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(user, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody Hotel hotel) {
		service.add(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}
	
	@PostMapping("addAll")
	public ResponseEntity<ResponseMessage> addAll(@RequestBody List<Hotel> hotels) {
		service.addAll(hotels);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestBody Hotel hotel) {
		HashMap<String, Hotel> map = service.upadte(hotel);
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

	@GetMapping("getByName")
	public ResponseEntity<Response<Hotel>> findByName(@RequestParam String name) {
		List<Hotel> list = service.findByName(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(list, message));
	}
	
	@GetMapping("getByNameLike")
	public ResponseEntity<Response<Hotel>> findByNameLike(@RequestParam String name) {
		List<Hotel> list = service.findByNameLike(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(list, message));
	}

	@GetMapping("getByNameStartingWith")
	public ResponseEntity<Response<Hotel>> getByNameStartingWith(@RequestParam String name) {
		List<Hotel> list = service.findByNameStartingWith(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(list, message));
	}

	@GetMapping("getByNameEndingWith")
	public ResponseEntity<Response<Hotel>> getByNameEndingWith(@RequestParam String name) {
		List<Hotel> list = service.findByNameEndingWith(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<Hotel>(list, message));
	}

}

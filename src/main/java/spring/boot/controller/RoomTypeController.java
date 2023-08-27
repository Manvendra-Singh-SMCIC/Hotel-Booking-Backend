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

import spring.boot.service.RoomTypeService;
import spring.boot.entity.RoomType;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/roomType")
public class RoomTypeController {

	@Autowired
	private RoomTypeService service;

	@GetMapping("getByHotelId")
	public ResponseEntity<Response<RoomType>> getByHotelId(@RequestParam int hotelId) {
		List<RoomType> list = service.getByHotelId(hotelId);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<RoomType>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<RoomType>> get(@RequestParam int id) {
		boolean found = service.get(id) != null;
		String message = found ? "found" : "not found";
		List<RoomType> roomTypes = found ? List.of(service.get(id)) : List.of();
		return ResponseEntity.status(HttpStatus.OK).body(new Response<RoomType>(roomTypes, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody RoomType roomType) {
		service.add(roomType);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}
	
	@PostMapping("addAll")
	public ResponseEntity<ResponseMessage> addAll(@RequestBody List<RoomType> roomTypes) {
		service.addAll(roomTypes);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestBody RoomType roomType) {
		HashMap<String, RoomType> map = service.update(roomType);
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

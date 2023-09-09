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

import spring.boot.service.UserService;
import spring.boot.entity.User;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("getAll")
	public ResponseEntity<Response<User>> getAll() {
		List<User> list = service.getAll();
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}

	@GetMapping("get")
	public ResponseEntity<Response<User>> get(@RequestParam int id) {
		boolean found = service.get(id) != null;
		String message = found ? "found" : "not found";
		List<User> user = found ? List.of(service.get(id)) : List.of();
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(user, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestBody User user) {
		service.add(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}
	
	@PostMapping("addAll")
	public ResponseEntity<ResponseMessage> addAll(@RequestBody List<User> users) {
		service.addAll(users);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestBody User user) {
		HashMap<String, User> map = service.upadte(user);
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

	@GetMapping("getByEmail")
	public ResponseEntity<Response<User>> getByEmail(@RequestParam String email) {
		List<User> list = service.findByEmail(email);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}
	
	@GetMapping("getByEmailAndPassword")
	public ResponseEntity<Response<User>> getByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
		List<User> list = service.findByEmailAndPassword(email,password);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}

	@GetMapping("getByName")
	public ResponseEntity<Response<User>> findByName(@RequestParam String name) {
		List<User> list = service.findByName(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}

	@GetMapping("getByNameStartingWith")
	public ResponseEntity<Response<User>> getByNameStartingWith(@RequestParam String name) {
		List<User> list = service.findByNameStartingWith(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}

	@GetMapping("getByNameEndingWith")
	public ResponseEntity<Response<User>> getByNameEndingWith(@RequestParam String name) {
		List<User> list = service.findByNameEndingWith(name);
		String message = list.size() != 0 ? "found" : "not found";
		return ResponseEntity.status(HttpStatus.OK).body(new Response<User>(list, message));
	}

}

package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.User;
import spring.boot.repository.UserRepository;
import spring.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	public User get(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public User add(User user) {
		return repository.save(user);
	}
	
	@Override
	public List<User> addAll(List<User> users) {
		return repository.saveAll(users);
	}

	@Override
	public HashMap<String, User> upadte(User user) {
		HashMap<String, User> map = new HashMap<>();
		User existingUser = repository.findById(user.getUserId()).orElse(null);
		if (existingUser != null) {
			repository.save(user);
			map.put("found", user);
		} else {
			map.put("not found", existingUser);
		}
		return map;
	}

	@Override
	public String delete(int id) {
		User existingUser = repository.findById(id).orElse(null);
		if (existingUser != null) {
			repository.delete(existingUser);
			return "found";
		} else {
			return "not found";
		}
	}

	@Override
	public List<User> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<User> findByNameStartingWith(String name) {
		return repository.findByNameStartingWith(name);
	}

	@Override
	public List<User> findByNameEndingWith(String name) {
		return repository.findByNameEndingWith(name);
	}

	@Override
	public List<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	@Override
	public List<User> findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
}

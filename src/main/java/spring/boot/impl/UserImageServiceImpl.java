package spring.boot.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.entity.UserImages;
import spring.boot.repository.UserImageRepository;
import spring.boot.service.UserImageService;

@Service
public class UserImageServiceImpl implements UserImageService {

	@Autowired
	private UserImageRepository repository;

	@Override
	public List<UserImages> get(int userId) {
		return repository.findByUid(userId);
	}

	@Override
	public UserImages add(UserImages userImages) {
		return repository.save(userImages);
	}

	@Override
	public HashMap<String, UserImages> update(UserImages userImages) {
		HashMap<String, UserImages> map = new HashMap<>();
		UserImages existingUserImage = repository.findById(userImages.getUserImageId()).orElse(null);
		if (existingUserImage != null) {
			repository.save(userImages);
			map.put("found", userImages);
		} else {
			map.put("not found", existingUserImage);
		}
		return map;
	}
}

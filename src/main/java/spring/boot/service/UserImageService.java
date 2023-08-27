package spring.boot.service;

import java.util.HashMap;
import java.util.List;

import spring.boot.entity.UserImages;

public interface UserImageService {
	
	List<UserImages> get(int userId);
	
	UserImages add(UserImages userImages);
	
	HashMap<String, UserImages> update(UserImages userImages);
}

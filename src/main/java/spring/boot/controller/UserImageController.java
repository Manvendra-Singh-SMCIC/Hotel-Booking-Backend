package spring.boot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.boot.service.UserImageService;
import spring.boot.constants.Folders;
import spring.boot.entity.UserImages;
import spring.boot.helper.FileUploadHelper;
import spring.boot.response.*;

@RestController
@RequestMapping("/api/v1/usersImages")
public class UserImageController {

	@Autowired
	private UserImageService service;

	@Autowired
	private FileUploadHelper uploadHelper;

	@GetMapping("get")
	public ResponseEntity<Response<UserImages>> get(@RequestParam int userId) {
		boolean found = !service.get(userId).isEmpty();
		String message = found ? "found" : "not found";
		List<UserImages> userImages = found ? service.get(userId) : List.of();
		return ResponseEntity.status(HttpStatus.OK).body(new Response<UserImages>(userImages, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestParam("image") MultipartFile image, @RequestParam int userId) {
		if (image.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("no image found"));
		}
		String type = image.getContentType();
		if (type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg")) {
			Map<String, Object> map = uploadHelper.uploadFile(image, Folders.USER.toString());
			if ((boolean) map.get("uploaded")) {
				UserImages userImages = UserImages.builder().uid(userId).image((String) map.get("url")).build();
				service.add(userImages);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("error"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("file not supported"));
	}

	@PutMapping("update")
	public ResponseEntity<ResponseMessage> update(@RequestParam int id, @RequestParam String img, @RequestParam int uid,
			@RequestParam MultipartFile image) throws FileNotFoundException, IOException {
		UserImages userImages = UserImages.builder().uid(uid).image(img).userImageId(id).build();
		if (image.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("no image found"));
		}
		String type = image.getContentType();
		if (type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg")) {
			boolean deleted = uploadHelper.deleteFile(userImages.getImage(), Folders.USER.toString());
			if (deleted) {
				Map<String, Object> map = uploadHelper.uploadFile(image, Folders.USER.toString());
				if ((boolean) map.get("uploaded")) {
					userImages.setImage((String) map.get("url"));
					HashMap<String, UserImages> result = service.update(userImages);
					if (result.containsKey("found")) {
						return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("updated"));
					} else {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("not found"));
					}
				} else {
					return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("error1"));
				}
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("error2"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("file not supported"));
	}
}

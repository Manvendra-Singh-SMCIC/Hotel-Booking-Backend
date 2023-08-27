package spring.boot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.boot.constants.Folders;
import spring.boot.entity.HotelImages;
import spring.boot.helper.FileUploadHelper;
import spring.boot.response.*;
import spring.boot.service.HotelImageService;

@RestController
@RequestMapping("/api/v1/hotelImages")
public class HotelImageController {

	@Autowired
	private HotelImageService service;

	@Autowired
	private FileUploadHelper uploadHelper;

	@GetMapping("get")
	public ResponseEntity<Response<HotelImages>> get(@RequestParam int hotelId) {
		boolean found = !service.get(hotelId).isEmpty();
		String message = found ? "found" : "not found";
		List<HotelImages> hotelImages = found ? service.get(hotelId) : List.of();
		return ResponseEntity.status(HttpStatus.OK).body(new Response<HotelImages>(hotelImages, message));
	}

	@PostMapping("add")
	public ResponseEntity<ResponseMessage> add(@RequestParam("image") MultipartFile image, @RequestParam int hotelId) {
		if (image.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("no image found"));
		}
		String type = image.getContentType();
		if (type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg")) {
			Map<String, Object> map = uploadHelper.uploadFile(image, Folders.HOTEL.toString());
			if ((boolean) map.get("uploaded")) {
				HotelImages hotelImages = HotelImages.builder().hid(hotelId).image((String) map.get("url")).build();
				service.add(hotelImages);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("error"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("file not supported"));
	}
	
	@PostMapping("addAll")
	public ResponseEntity<ResponseMessage> addAll(@RequestParam("images") MultipartFile[] images, @RequestParam int hotelId) {
		List<String> uploadedUrls = new ArrayList<>();
	    for (MultipartFile image : images) {
	        if (image.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("no image found"));
	        }

	        String type = image.getContentType();
	        if (type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/jpg")) {
	            Map<String, Object> map = uploadHelper.uploadFile(image, Folders.HOTEL.toString());
	            if ((boolean) map.get("uploaded")) {
	                uploadedUrls.add((String) map.get("url"));
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error"));
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("file not supported"));
	        }
	    }


	    List<HotelImages> hotelImages = new ArrayList<>();
	    for (String imageUrl : uploadedUrls) {
	        hotelImages.add(HotelImages.builder().hid(hotelId).image(imageUrl).build());
	        service.addAll(hotelImages);
	    }
	    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("added"));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam int id, @RequestParam String url) throws FileNotFoundException, IOException {
		boolean deleted = uploadHelper.deleteFile(url, Folders.HOTEL.toString());
		if(deleted) {
			String response = service.delete(id);
			if (response == "found") {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage("deleted"));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("not found"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error"));
		}
	}
}

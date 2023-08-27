package spring.boot.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import com.google.cloud.storage.Storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public Map<String, Object> uploadFile(MultipartFile file, String subFolder) {
		Map<String, Object> map = new HashMap<>();
		String url = "";
		map.put("url", url);
		map.put("uploaded", false);
		try {
			url = upload(file, subFolder);
			map.put("uploaded", true);
			map.put("url", url);

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}
		return map;
	}

	public String upload(MultipartFile multipartFile, String subFolder) throws IOException {
		String objectName = subFolder + "/" + generateFileName(multipartFile);

		FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
		File file = convertMultiPartToFile(multipartFile);
		Path filePath = file.toPath();

		Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setProjectId("amacle-hotel-mobile-app").build().getService();
		BlobId blobId = BlobId.of("hotel-app-e75ca.appspot.com", objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

		storage.create(blobInfo, Files.readAllBytes(filePath));
		Blob uploadedBlob = storage.get(blobId);
		String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/"
				+ (uploadedBlob.getMediaLink()).substring(53);
		System.out.println(downloadUrl);

		return downloadUrl;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
	}

	public boolean deleteFile(String imageUrl, String subfolder) throws FileNotFoundException, IOException {
		boolean deleted = false;
		System.out.println(imageUrl);
		if (imageUrl.startsWith("https://firebasestorage.googleapis.com/")) {
			System.out.println(2);
			String blobName = imageUrl.substring("https://firebasestorage.googleapis.com/v0/b/".length());

			int slashIndex = blobName.indexOf('/');
			if (slashIndex != -1) {
				String bucketName = blobName.substring(0, slashIndex);
				String objectName = subfolder + "/"
						+ blobName.substring(slashIndex + 6 + subfolder.length(), blobName.lastIndexOf("?"));
				System.out.println(bucketName);
				System.out.println(objectName);

				Storage storage = StorageOptions.newBuilder()
						.setCredentials(GoogleCredentials.fromStream(new FileInputStream("./serviceAccountKey.json")))
						.setProjectId("amacle-hotel-mobile-app").build().getService();

				BlobId blobId = BlobId.of(bucketName, objectName);
				System.out.println(blobId);
				deleted = storage.delete(blobId);
				if (deleted) {
					System.out.println("Yah");
				} else {
					System.out.println("No");
				}

			}
		}
		return deleted;
	}
}

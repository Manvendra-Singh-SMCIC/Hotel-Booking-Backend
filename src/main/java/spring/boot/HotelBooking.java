package spring.boot;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = { "spring.boot" }, exclude = {SecurityAutoConfiguration.class})
@ComponentScan
@OpenAPIDefinition(
		info = @Info(
				title = "Easy Yatra", 
				version = "1.0.0", 
				description = "Backend Service for easy yatra app and web app", 
				termsOfService = "All terms of service are mentioned in the playstore and Terms of Services of the web app.", 
				contact = @Contact(
						name = "Manvendra Singh", 
						email = "12a.manvendrasingh@gmail.com")))
public class HotelBooking {

	@SuppressWarnings("all")
	public static void main(String[] args) throws IOException {

		FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(HotelBooking.class, args);
	}
}

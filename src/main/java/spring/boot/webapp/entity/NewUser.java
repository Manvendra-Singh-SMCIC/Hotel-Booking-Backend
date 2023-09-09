package spring.boot.webapp.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.boot.constants.Constants;

@ToString
@Getter
@Setter
public class NewUser {
	
	@NotBlank(message = "Email cannot be empty !!")
	@Size(min = 4, message = "Invalid email.")
	@Pattern(regexp = Constants.EMAIL_REGEX, message = "Invalid email format. Please use a valid email address.")
	public String email;
	
	@NotBlank(message = "Password cannot be empty !!")
	@Pattern(regexp = Constants.PASSWORD_REGEX, message = "Password must contain atleast 1 uppercase, 1 lowecase, 1 digit and minimumlength should be 8")
	public String password;
	
	@NotBlank(message = "Phone Number cannot be empty !!")
	@Size(min = 10, max = 10, message = "Invalid Phone Number")
	@Pattern(regexp = Constants.PHONE_REGEX, message = "Invalid format")
	public String phoneNumber;
	
	@NotBlank(message = "Name cannot be empty !!")
	@Size(min = 2, max = 60, message = "Invalid Name")
	@Pattern(regexp = Constants.NAME_REGEX, message = "Invalid name format.")
	public String name;
	
	
	public String role;
	public String dob;
}

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
public class User {
	
	@NotBlank(message = "Email cannot be empty !!")
	@Size(min = 8, message = "Invalid email")
	@Pattern(regexp = Constants.EMAIL_REGEX)
	public String email;
	
	@NotBlank(message = "Password cannot be empty !!")
	public String password;
}

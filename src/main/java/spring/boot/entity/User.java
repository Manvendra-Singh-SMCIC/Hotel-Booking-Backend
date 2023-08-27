package spring.boot.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings("all")
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String phoneNumber;
	private String name;
	private String email;
	private String role;
	private String dob;
	
	@OneToMany
	@JoinColumn(name = "uid")
    private List<UserImages> userImages;
	
	@OneToMany
	@JoinColumn(name = "uid")
	private List<Wishlist> wishlists;
}


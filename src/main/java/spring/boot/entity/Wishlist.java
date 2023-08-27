package spring.boot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int wishlistId;
	private int uid;
	private int hid;
}

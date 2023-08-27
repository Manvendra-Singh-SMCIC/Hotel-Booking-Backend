package spring.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class HotelImages {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelImageId;
	private int hid;
    private String image;
}

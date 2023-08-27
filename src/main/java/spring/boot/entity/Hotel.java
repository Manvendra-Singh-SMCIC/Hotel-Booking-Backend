package spring.boot.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotelId;
	private String phone;
	private String name;
	private String city;
	private String state;
	private String place;
	private double rating;
	private int r1;
	private int r2;
	private int r3;
	private int r4;
	private int r5;
	private int origCost;
	private String location;
	private int discCost;
	private String popularAmenities;

	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	private String cancellationPolicy;
	private String rules;
	private String breakfast;
	private String airportShuttle;

	@OneToMany
	@JoinColumn(name = "hid")
	private List<Review> reviews;

	@OneToMany
	@JoinColumn(name = "hid")
	private List<HotelImages> hotelImages;
	
	@OneToMany
	@JoinColumn(name = "hid")
	private List<RoomType> roomTypes;
}

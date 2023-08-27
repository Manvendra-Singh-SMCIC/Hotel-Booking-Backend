package spring.boot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

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
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    private String paymentId;
    private int amountPaid;
    private int durationInSec;
    private String bookingDate;
    private String endingDate;
    private int adults;
    private int children;
    private int rooms;
    private int uid;
    private int hid;
    private int rid;
    
    private Timestamp timestamp;
    
    @OneToOne
	@JoinColumn(name = "uid", referencedColumnName = "userId", insertable = false, updatable = false)
	private User user;
    
    @OneToOne
	@JoinColumn(name = "hid", referencedColumnName = "hotelId", insertable = false, updatable = false)
	private Hotel hotel;
}

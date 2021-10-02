package com.elanza48.TMS.model.dto;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "booking_Info")
public class Booking {
	
	public enum RoomType{
	    STANDARD,
	    SUPERIOR,
	    DELUXE
	}
	
	public enum BookingStatus{
	    ACTIVE,
	    CANCELLED,
	    COMPLETE
	}
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	
	@Column
	@NotNull
	private Date date;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pkg_id", referencedColumnName = "id")
	private Package packageId;
	
	@Column(name = "T_DATE")
	@NotNull
	private Date tourDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usr_id", referencedColumnName = "id")
	private UserAccount userId;
	
	@Column(name = "PASSENGER_COUNT")
	@NotNull
	@Size(min=1, max=12)
	private short passengerCount=1;
	
	@Column(name = "R_TYPE")
	@NotNull
	@Enumerated
	private RoomType roomType= RoomType.STANDARD;
	
	@Column
	@NotNull
	private int price;
	
	@Column
	@NotNull
	@Enumerated
	private BookingStatus status = BookingStatus.ACTIVE;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Set<Ticket> tickets;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Set<DestinationReview> destinationReviews;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Set<HotelReview> hotelReviews;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Payment paymentInfo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
	private Set<Enquiry> enquiries;
	
}

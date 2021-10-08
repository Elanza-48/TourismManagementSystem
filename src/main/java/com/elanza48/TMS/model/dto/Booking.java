package com.elanza48.TMS.model.dto;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "booking_Info")
public class Booking  extends Identity{
	
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
	
	@Column(name = "PASSENGER_COUNT", length = 2)
	@NotNull
	@Range(min=1, max=12)
	private short passengerCount=1;
	
	@Column(name = "R_TYPE")
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoomType roomType= RoomType.STANDARD;
	
	@Column
	@NotNull
	private int price;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
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
	
	public Booking() {
		super();
	}

	public Booking(@NotNull Date date, Package packageId, @NotNull Date tourDate, UserAccount userId,
			@NotNull @Range(min = 1, max = 12) short passengerCount, @NotNull RoomType roomType, @NotNull int price,
			@NotNull BookingStatus status, Payment paymentInfo) {
		this.date = date;
		this.packageId = packageId;
		this.tourDate = tourDate;
		this.userId = userId;
		this.passengerCount = passengerCount;
		this.roomType = roomType;
		this.price = price;
		this.status = status;
		this.paymentInfo = paymentInfo;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Package getPackageId() {
		return packageId;
	}

	public void setPackageId(Package packageId) {
		this.packageId = packageId;
	}

	public Date getTourDate() {
		return tourDate;
	}

	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}

	public UserAccount getUserId() {
		return userId;
	}

	public void setUserId(UserAccount userId) {
		this.userId = userId;
	}

	public short getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(short passengerCount) {
		this.passengerCount = passengerCount;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Set<DestinationReview> getDestinationReviews() {
		return destinationReviews;
	}

	public void setDestinationReviews(Set<DestinationReview> destinationReviews) {
		this.destinationReviews = destinationReviews;
	}

	public Set<HotelReview> getHotelReviews() {
		return hotelReviews;
	}

	public void setHotelReviews(Set<HotelReview> hotelReviews) {
		this.hotelReviews = hotelReviews;
	}

	public Payment getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public Set<Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(Set<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}
	

	@Override
	public String toString() {
		return "Booking [date=" + date + ", packageId=" + packageId + ", tourDate=" + tourDate + ", userId=" + userId
				+ ", passengerCount=" + passengerCount + ", roomType=" + roomType + ", price=" + price + ", status="
				+ status + ", tickets=" + tickets + ", destinationReviews=" + destinationReviews + ", hotelReviews="
				+ hotelReviews + ", paymentInfo=" + paymentInfo + ", enquiries=" + enquiries + ", id=" + id + "]";
	}
}

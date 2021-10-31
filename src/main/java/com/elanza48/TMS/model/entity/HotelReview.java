package com.elanza48.TMS.model.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "hotel_review")
public class HotelReview extends Identity{
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotelId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@Column
	@NotNull
	private Date date;
	
	@Column(length = 2)
	@Range(min = 1, max = 10)
	private short rating;
	
	@Column
	@Type(type = "text")
	private String review;

	@Embedded
	@NotNull
	private MetaData metaData=new MetaData();
	
	public HotelReview() {}
	public HotelReview(Hotel hotelId, Booking bookingId, @NotNull Date date, @Range(min = 1, max = 10) short rating,
	 String review) {
		this.hotelId = hotelId;
		this.bookingId = bookingId;
		this.date=date;
		this.rating = rating;
		this.review = review;
	}

	public Hotel getHotelId() {
		return hotelId;
	}

	public void setHotelId(Hotel hotelId) {
		this.hotelId = hotelId;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public short getRating() {
		return rating;
	}

	public void setRating(short rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "HotelReview [hotelId=" + hotelId + ", bookingId=" + bookingId + ", date=" + date + ", rating=" + rating
				+ ", review=" + review + ", id=" + id + "]";
	}
}
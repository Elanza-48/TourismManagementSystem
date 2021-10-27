package com.elanza48.TMS.model.dto;

import java.sql.Date;

public class HotelReviewDTO extends IdentityDTO{
	
	private HotelDTO hotelId;
	private BookingDTO bookingId;
	private Date date;
	private short rating;
	private String review;
	
	public HotelReviewDTO() {}

	public HotelReviewDTO(HotelDTO hotelId, BookingDTO bookingId,Date date,  short rating, String review) {
		super();
		this.hotelId = hotelId;
		this.bookingId = bookingId;
		this.date=date;
		this.rating = rating;
		this.review = review;
	}

	public HotelDTO getHotelId() {
		return hotelId;
	}

	public void setHotelId(HotelDTO hotelId) {
		this.hotelId = hotelId;
	}

	public BookingDTO getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingDTO bookingId) {
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

	@Override
	public String toString() {
		return "HotelReview [hotelId=" + hotelId + ", bookingId=" + bookingId + ", date=" + date + ", rating=" + rating
				+ ", review=" + review + ", id=" + id + "]";
	}
}

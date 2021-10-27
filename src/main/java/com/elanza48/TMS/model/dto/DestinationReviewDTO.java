package com.elanza48.TMS.model.dto;

import java.sql.Date;
public class DestinationReviewDTO extends IdentityDTO{

	private DestinationDTO destinationId;
	private BookingDTO bookingId;
	private Date date;
	private short rating;
	private String review;
	
	public DestinationReviewDTO() {}

	public DestinationReviewDTO(DestinationDTO destinationId, BookingDTO bookingId, Date date, 
			short rating, String review) {
		this.destinationId = destinationId;
		this.bookingId = bookingId;
		this.date=date;
		this.rating = rating;
		this.review = review;
	}

	public DestinationDTO getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(DestinationDTO destinationId) {
		this.destinationId = destinationId;
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
		return "DestinationReview [destinationId=" + destinationId + ", bookingId=" + bookingId + ", date=" + date
				+ ", rating=" + rating + ", review=" + review + ", id=" + id + "]";
	}
}

package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "destination_review")
public class DestinationReview extends Identity{

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dest_id", referencedColumnName = "id")
	private Destination destinationId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@Column
	@Size(min=1, max=10)
	private short rating;
	
	@Column
	@Type(type = "text")
	private String review;
	
	public DestinationReview() {
		super();
	}

	public DestinationReview(Destination destinationId, Booking bookingId, @Size(min = 1, max = 10) short rating,
			String review) {
		this.destinationId = destinationId;
		this.bookingId = bookingId;
		this.rating = rating;
		this.review = review;
	}

	public Destination getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Destination destinationId) {
		this.destinationId = destinationId;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
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
		return "DestinationReview [destinationId=" + destinationId + ", bookingId=" + bookingId + ", rating=" + rating
				+ ", review=" + review + ", id=" + id + "]";
	}
}

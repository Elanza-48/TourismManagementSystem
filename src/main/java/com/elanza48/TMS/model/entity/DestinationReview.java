package com.elanza48.TMS.model.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "destination_review")
public class DestinationReview extends Identity{

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dest_id", referencedColumnName = "id")
	@JsonBackReference("destRvw")
	private Destination destinationId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	@JsonBackReference("bookingDestRvw")
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
	
	public DestinationReview() {
		super();
	}

	public DestinationReview(Destination destinationId, Booking bookingId, @NotNull Date date, 
			@Range(min = 1, max = 10) short rating, String review) {
		this.destinationId = destinationId;
		this.bookingId = bookingId;
		this.date=date;
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

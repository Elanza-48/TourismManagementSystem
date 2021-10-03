package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "hotel_review")
@OnDelete(action = OnDeleteAction.CASCADE)
public class HotelReview extends Identity{
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotelId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@Column
	@Size(min=1, max=10)
	private short rating;
	
	@Column
	@Type(type = "text")
	private String review;

}

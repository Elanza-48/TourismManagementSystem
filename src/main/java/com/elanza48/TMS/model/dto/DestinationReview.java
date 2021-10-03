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
@Table(name = "destination_review")
@OnDelete(action = OnDeleteAction.CASCADE)
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

}

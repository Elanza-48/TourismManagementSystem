package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "booking_transport_ticket")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Ticket extends Identity{
	
	@Column(name = "seat_no")
	private String seatNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transport_id", referencedColumnName = "id")
	private Transport transportId;
}

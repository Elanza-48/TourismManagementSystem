package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;


@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class Enquiry extends Identity{

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id",referencedColumnName = "id")
	private Booking bookingId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transport_id",referencedColumnName = "id")
	private Transport transportId;
	
	@Column
	@NotNull
	private String subject;
	
	@Column
	@Type(type = "text")
	private String body;
	
	@Column(name = "enq_open")
	@NotNull
	private boolean isOpen= true;
	
	
}

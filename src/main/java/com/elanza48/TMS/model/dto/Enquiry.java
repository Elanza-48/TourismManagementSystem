package com.elanza48.TMS.model.dto;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity
public class Enquiry {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	
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

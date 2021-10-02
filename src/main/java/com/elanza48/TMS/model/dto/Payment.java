package com.elanza48.TMS.model.dto;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity
@Table(name = "payment_Info")
public class Payment {
	
	public enum PaymentMode{
	    CARD,
	    UPI,
	    INTERNET_BANKING,
	    WALLET,
	    CRYPTOCURRENCY
	}
	
	public enum PaymentStatus{
	    SUCCESS,
	    PENDING,
	    FAILED
	}
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@Column
	@NotNull
	@Enumerated
	private PaymentMode mode= PaymentMode.CARD;
	
	@Column
	private short discount=0;
	
	@Column(name = "net_charge")
	@NotNull
	private int netCharge=0;
	
	@Column
	@NotNull
	private int gst=0; //less than net charge.
	
	@Column(name = "txn_id")
	@NotNull
	private String transactionId;
	
	@Column
	@NotNull
	@Enumerated
	private PaymentStatus status;
	
}

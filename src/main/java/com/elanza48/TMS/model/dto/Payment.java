package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "payment_Info")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Payment extends Identity{
	
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

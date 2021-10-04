package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking_transport_ticket")
public class Ticket extends Identity{
	
	@Column(name = "seat_no")
	private String seatNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transport_id", referencedColumnName = "id")
	private Transport transportId;
	
	public Ticket() {
		super();
	}

	public Ticket(String seatNumber, Booking bookingId, Transport transportId) {
		super();
		this.seatNumber = seatNumber;
		this.bookingId = bookingId;
		this.transportId = transportId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	public Transport getTransportId() {
		return transportId;
	}

	public void setTransportId(Transport transportId) {
		this.transportId = transportId;
	}

	@Override
	public String toString() {
		return "Ticket [seatNumber=" + seatNumber + ", bookingId=" + bookingId + ", transportId=" + transportId
				+ ", id=" + id + "]";
	}
}

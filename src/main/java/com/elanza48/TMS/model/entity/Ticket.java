package com.elanza48.TMS.model.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "booking_transport_ticket")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Ticket extends Identity implements Serializable {
	
	@Column(name = "seat_no")
	private String seatNumber;
	
	@Column
	@NotNull
	private Date date;

	@Embedded
	@NotNull
	private MetaData metaData= new MetaData();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking bookingId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transport_id", referencedColumnName = "id")
	private Transport transportId;
	
	public Ticket() {}
	public Ticket(String seatNumber, @NotNull Date date, Booking bookingId,
	 Transport transportId) {
		this.seatNumber = seatNumber;
		this.date=date;
		this.bookingId = bookingId;
		this.transportId = transportId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public MetaData getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "Ticket [seatNumber=" + seatNumber + ", date=" + date + ", bookingId=" + bookingId + ", transportId="
				+ transportId + ", id=" + id + "]";
	}
}

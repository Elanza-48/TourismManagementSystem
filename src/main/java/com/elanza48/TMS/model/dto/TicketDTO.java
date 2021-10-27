package com.elanza48.TMS.model.dto;

import java.sql.Date;

public class TicketDTO extends IdentityDTO{
	
	private String seatNumber;
	private Date date;
	private BookingDTO bookingId;
	private TransportDTO transportId;
	
	public TicketDTO() {}

	public TicketDTO(String seatNumber, Date date, BookingDTO bookingId, TransportDTO transportId) {
		super();
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

	public BookingDTO getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingDTO bookingId) {
		this.bookingId = bookingId;
	}

	public TransportDTO getTransportId() {
		return transportId;
	}

	public void setTransportId(TransportDTO transportId) {
		this.transportId = transportId;
	}

	@Override
	public String toString() {
		return "Ticket [seatNumber=" + seatNumber + ", date=" + date + ", bookingId=" + bookingId + ", transportId="
				+ transportId + ", id=" + id + "]";
	}
}

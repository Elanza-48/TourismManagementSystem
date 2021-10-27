package com.elanza48.TMS.model.dto;

import java.sql.Date;

public class EnquiryDTO extends IdentityDTO{

	private BookingDTO bookingId;
	private Date date;
	private String subject;
	private String body;
	private boolean isOpen= true;
	
	public EnquiryDTO() {}

	public EnquiryDTO(BookingDTO bookingId, Date date, String subject, String body, boolean isOpen) {
		this.bookingId = bookingId;
		this.date=date;
		this.subject = subject;
		this.body = body;
		this.isOpen = isOpen;
	}

	public BookingDTO getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingDTO bookingId) {
		this.bookingId = bookingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public String toString() {
		return "Enquiry [bookingId=" + bookingId +  ", date=" + date + ", subject="
				+ subject + ", body=" + body + ", isOpen=" + isOpen + ", id=" + id + "]";
	}
}

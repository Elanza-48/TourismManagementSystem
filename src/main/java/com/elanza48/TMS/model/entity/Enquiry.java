package com.elanza48.TMS.model.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.Type;


@Entity
public class Enquiry extends Identity{

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id",referencedColumnName = "id")
	@JsonBackReference("bookingEnqRvw")
	private Booking bookingId;
	
	@Column
	@NotNull
	private Date date;
	
	@Column
	@NotNull
	private String subject;
	
	@Column
	@Type(type = "text")
	private String body;
	
	@Column(name = "enq_open")
	@NotNull
	private boolean isOpen= true;
	
	public Enquiry() {
		super();
	}

	public Enquiry(Booking bookingId, @NotNull Date date, @NotNull String subject, String body,
			@NotNull boolean isOpen) {
		this.bookingId = bookingId;
		this.date=date;
		this.subject = subject;
		this.body = body;
		this.isOpen = isOpen;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
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

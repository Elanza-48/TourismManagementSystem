package com.elanza48.TMS.model.entity;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;


@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enquiry extends Identity {

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id",referencedColumnName = "id")
	private Booking bookingId;
	
	@Column
	@NotNull
	@PastOrPresent
	private Date date;
	
	@Column
	@NotNull
	private String subject;
	
	@Column
	@Type(type = "text")
	private String body;
	
	@Column(name = "enq_open")
	@NotNull
	private boolean open= true;

	@Embedded
	@NotNull
	private MetaData metaData;
	
	
	public Enquiry() {}
	public Enquiry(Booking bookingId, @NotNull Date date, @NotNull String subject, String body,
			@NotNull boolean open) {
		this.bookingId = bookingId;
		this.date=date;
		this.subject = subject;
		this.body = body;
		this.open = open;
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
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "Enquiry [bookingId=" + bookingId +  ", date=" + date + ", subject="
				+ subject + ", body=" + body + ", isOpen=" + open + ", id=" + id + "]";
	}
}

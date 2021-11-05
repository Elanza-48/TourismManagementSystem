package com.elanza48.TMS.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Check;


@Entity
@Table(name = "payment_Info")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Check( constraints = "gst < net_charge")
public class Payment extends Identity implements Serializable {
	
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
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	private Booking bookingId;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private PaymentMode mode= PaymentMode.CARD;
	
	@Column
	private short discount=0;
	
	@Column(name = "net_charge")
	@NotNull
	private int netCharge=0;
	
	@Column
	@NotNull
	private int gst=0;
	
	@Column(name = "txn_id")
	@NotNull
	private String transactionId;
	
	@Column
	@NotNull
	@PastOrPresent
	private Date date;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Embedded
	@NotNull
	private MetaData metaData=new MetaData();
	
	
	public Payment() {}
	public Payment(Booking bookingId, @NotNull PaymentMode mode, short discount, @NotNull int netCharge,
			@NotNull int gst, @NotNull String transactionId, @NotNull Date date, @NotNull PaymentStatus status) {
		this.bookingId = bookingId;
		this.mode = mode;
		this.discount = discount;
		this.netCharge = netCharge;
		this.gst = gst;
		this.transactionId = transactionId;
		this.date=date;
		this.status = status;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public short getDiscount() {
		return discount;
	}

	public void setDiscount(short discount) {
		this.discount = discount;
	}

	public int getNetCharge() {
		return netCharge;
	}

	public void setNetCharge(int netCharge) {
		this.netCharge = netCharge;
	}

	public int getGst() {
		return gst;
	}

	public void setGst(int gst) {
		if(gst>netCharge) throw new IllegalArgumentException("GST greater than net Charges !");
		this.gst = gst;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "Payment [bookingId=" + bookingId + ", mode=" + mode + ", discount=" + discount + ", netCharge="
				+ netCharge + ", gst=" + gst + ", transactionId=" + transactionId + ", date=" + date + ", status="
				+ status + ", id=" + id + "]";
	}
}

package com.elanza48.TMS.model.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "payment_Info")
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
	private int gst=0;
	
	@Column(name = "txn_id")
	@NotNull
	private String transactionId;
	
	@Column
	@NotNull
	@Enumerated
	private PaymentStatus status;
	
	public Payment() {
		super();
	}

	public Payment(Booking bookingId, @NotNull PaymentMode mode, short discount, @NotNull int netCharge,
			@NotNull int gst, @NotNull String transactionId, @NotNull PaymentStatus status) {
		super();
		this.bookingId = bookingId;
		this.mode = mode;
		this.discount = discount;
		this.netCharge = netCharge;
		this.gst = gst;
		this.transactionId = transactionId;
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

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [bookingId=" + bookingId + ", mode=" + mode + ", discount=" + discount + ", netCharge="
				+ netCharge + ", gst=" + gst + ", transactionId=" + transactionId + ", status=" + status + ", id=" + id
				+ "]";
	}
}

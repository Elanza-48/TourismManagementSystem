package com.elanza48.TMS.model.dto;

import java.sql.Date;

import com.elanza48.TMS.model.entity.Payment.PaymentMode;
import com.elanza48.TMS.model.entity.Payment.PaymentStatus;

public class PaymentDTO extends IdentityDTO{
	
	private BookingDTO bookingId;
	private PaymentMode mode= PaymentMode.CARD;
	private short discount=0;
	private int netCharge=0;
	private int gst=0;
	private String transactionId;
	private Date date;
	private PaymentStatus status;
	
	public PaymentDTO() {}

	public PaymentDTO(BookingDTO bookingId, PaymentMode mode, short discount, int netCharge,
			int gst, String transactionId,  Date date, PaymentStatus status) {

		this.bookingId = bookingId;
		this.mode = mode;
		this.discount = discount;
		this.netCharge = netCharge;
		this.gst = gst;
		this.transactionId = transactionId;
		this.date=date;
		this.status = status;
	}

	public BookingDTO getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingDTO bookingId) {
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

	@Override
	public String toString() {
		return "Payment [bookingId=" + bookingId + ", mode=" + mode + ", discount=" + discount + ", netCharge="
				+ netCharge + ", gst=" + gst + ", transactionId=" + transactionId + ", date=" + date + ", status="
				+ status + ", id=" + id + "]";
	}
}

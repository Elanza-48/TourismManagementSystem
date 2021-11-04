package com.elanza48.TMS.model.dto;

import java.io.Serializable;
import java.sql.Date;

import com.elanza48.TMS.model.entity.Payment.PaymentMode;
import com.elanza48.TMS.model.entity.Payment.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO extends IdentityDTO implements Serializable {
	
	@Setter private BookingDTO bookingId;
	@Setter private PaymentMode mode= PaymentMode.CARD;
	@Setter private short discount=0;
	@Setter private int netCharge=0;
	private int gst=0;
	@Setter private String transactionId;
	@Setter private Date date;
	@Setter private PaymentStatus status;
	

	public void setGst(int gst) {
		if(gst>netCharge) throw new IllegalArgumentException("GST greater than net Charges !");
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "Payment [bookingId=" + bookingId + ", mode=" + mode + ", discount=" + discount + ", netCharge="
				+ netCharge + ", gst=" + gst + ", transactionId=" + transactionId + ", date=" + date + ", status="
				+ status + ", id=" + id + "]";
	}
}

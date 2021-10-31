package com.elanza48.TMS.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryDTO extends IdentityDTO{

	private BookingDTO bookingId;
	private Date date;
	private String subject;
	private String body;
	private boolean isOpen= true;
	
	@Override
	public String toString() {
		return "Enquiry [bookingId=" + bookingId +  ", date=" + date + ", subject="
				+ subject + ", body=" + body + ", isOpen=" + isOpen + ", id=" + id + "]";
	}
}

package com.elanza48.TMS.model.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationReviewDTO extends IdentityDTO implements Serializable {

	private DestinationDTO destinationId;
	private BookingDTO bookingId;
	private Date date;
	private short rating;
	private String review;
	
	@Override
	public String toString() {
		return "DestinationReview [destinationId=" + destinationId + ", bookingId=" + bookingId + ", date=" + date
				+ ", rating=" + rating + ", review=" + review + ", id=" + id + "]";
	}
}

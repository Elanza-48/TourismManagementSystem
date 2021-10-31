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
public class HotelReviewDTO extends IdentityDTO{
	
	private HotelDTO hotelId;
	private BookingDTO bookingId;
	private Date date;
	private short rating;
	private String review;
	

	@Override
	public String toString() {
		return "HotelReview [hotelId=" + hotelId + ", bookingId=" + bookingId + ", date=" + date + ", rating=" + rating
				+ ", review=" + review + ", id=" + id + "]";
	}
}

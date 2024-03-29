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
public class TicketDTO extends IdentityDTO {
	
	private String seatNumber;
	private Date date;
	private BookingDTO bookingId;
	private TransportDTO transportId;

	@Override
	public String toString() {
		return "TicketDTO [seatNumber=" + seatNumber + ", date=" + date + ", bookingId=" + bookingId + ", transportId="
				+ transportId + ", id=" + id + "]";
	}
}

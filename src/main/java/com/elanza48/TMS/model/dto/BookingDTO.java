package com.elanza48.TMS.model.dto;

import java.sql.Date;

import com.elanza48.TMS.model.entity.Booking.BookingStatus;
import com.elanza48.TMS.model.entity.Booking.RoomType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO  extends IdentityDTO{

	private Date date;
	private PackageDTO packageId;
	private Date tourDate;
	private UserAccountDTO userId;
	private short passengerCount;
	private RoomType roomType= RoomType.STANDARD;
	private int price;
	private BookingStatus status = BookingStatus.ACTIVE;


	@Override
	public String toString() {
		return "Booking [date=" + date + ", packageId=" + packageId + ", tourDate=" + tourDate + ", userId=" + userId
				+ ", passengerCount=" + passengerCount + ", roomType=" + roomType + ", price=" + price + ", status="
				+ status + ", id=" + id + "]";
	}
}

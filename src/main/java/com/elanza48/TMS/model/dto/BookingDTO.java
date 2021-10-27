package com.elanza48.TMS.model.dto;

import java.sql.Date;

import com.elanza48.TMS.model.entity.Booking.BookingStatus;
import com.elanza48.TMS.model.entity.Booking.RoomType;

public class BookingDTO  extends IdentityDTO{

	private Date date;
	private PackageDTO packageId;
	private Date tourDate;
	private UserAccountDTO userId;
	private short passengerCount;
	private RoomType roomType= RoomType.STANDARD;
	private int price;
	private BookingStatus status = BookingStatus.ACTIVE;


	public BookingDTO() {}

	public BookingDTO(Date date, PackageDTO packageId, Date tourDate, UserAccountDTO userId,
			short passengerCount, RoomType roomType, int price, BookingStatus status) {
		this.date = date;
		this.packageId = packageId;
		this.tourDate = tourDate;
		this.userId = userId;
		this.passengerCount = passengerCount;
		this.roomType = roomType;
		this.price = price;
		this.status = status;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PackageDTO getPackageId() {
		return packageId;
	}

	public void setPackageId(PackageDTO packageId) {
		this.packageId = packageId;
	}

	public Date getTourDate() {
		return tourDate;
	}

	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}

	public UserAccountDTO getUserId() {
		return userId;
	}

	public void setUserId(UserAccountDTO userId) {
		this.userId = userId;
	}

	public short getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(short passengerCount) {
		this.passengerCount = passengerCount;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [date=" + date + ", packageId=" + packageId + ", tourDate=" + tourDate + ", userId=" + userId
				+ ", passengerCount=" + passengerCount + ", roomType=" + roomType + ", price=" + price + ", status="
				+ status + ", id=" + id + "]";
	}
}

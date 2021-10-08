package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user_Account")
public class UserAccount extends Contact{
	
	public enum UserRole{
		USER,
		MANAGER,
		ADMIN
	}
	
	public enum UserAccountStatus{
		ACTIVE,
		SUSPENDED,
		INACTIVE,
		CLOSED
	}
	
	@Column
	@NotNull
	private String password;
	
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole role= UserRole.USER;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserAccountStatus status=UserAccountStatus.ACTIVE;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	Set<Booking> bookings;
	
	public UserAccount() {
		super();
	}
	
	public UserAccount(@NotNull String name, @NotNull @Email String email,
			@NotNull @Pattern(regexp="(^$|[0-9]{10})") long mobileNo, @NotNull Address address,
			@NotNull String password) {
		super(name, email, mobileNo, address);
		this.password = password;
	}

	@JsonIgnore
	@JsonProperty(access = Access.READ_WRITE)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserAccountStatus getStatus() {
		return status;
	}

	public void setStatus(UserAccountStatus status) {
		this.status = status;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	@Override
	public long getMobileNo() {
		return super.getMobileNo();
	}

	@Override
	public void setMobileNo(long mobileNo) {
		super.setMobileNo(mobileNo);
	}

	@Override
	public Address getAddress() {
		return super.getAddress();
	}

	@Override
	public void setAddress(Address address) {
		super.setAddress(address);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String toString() {
		return "UserAccount [role=" + role + ", status=" + status + ", bookings=" + bookings + ", email=" + email
				+ ", mobileNo=" + mobileNo + ", address=" + address + ", name=" + name + ", id=" + id + "]";
	}

}

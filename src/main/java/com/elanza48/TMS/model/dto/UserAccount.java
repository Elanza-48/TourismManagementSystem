package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Column
	@NotNull
	private String password;
	
	
	@Column
	@NotNull
	@Enumerated
	private UserRole role= UserRole.USER;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	Set<Booking> bookings;
	
	public UserAccount() {
		super();
	}
	
	public UserAccount(@NotNull String name, @NotNull @Email String email,
			@NotNull @Size(min = 10, max = 10) long mobileNo, @NotNull Address address,
			@NotNull String password, @NotNull UserRole role) {
		super(name, email, mobileNo, address);
		this.password = password;
		this.role = role;
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
		return "UserAccount [role=" + role + ", bookings=" + bookings + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", name=" + name + ", id=" + id + "]";
	}

}

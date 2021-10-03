package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user_Account")
@OnDelete(action = OnDeleteAction.CASCADE)
public class UserAccount extends IdentityNameContact{
	
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
	
	public UserAccount() {}

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
	
	

}

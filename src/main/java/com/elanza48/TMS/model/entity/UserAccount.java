package com.elanza48.TMS.model.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user_Account")
public class UserAccount extends Contact {

	public enum UserGender{
		MALE, FEMALE, TRANS
	}
	
	@Column
	@NotNull
	private String password;
	
	@Column
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private UserGender gender;

	@Column
	@NotNull
	@Temporal(TemporalType.DATE)
	@Past
	private Date dob;

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
			CascadeType.REFRESH
	})
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private UserRole role;
	
	@Column
	@NotNull
	private boolean active=true;

	@Column
	@NotNull
	private boolean suspended=false;

	@Embedded
	@NotNull
	private MetaData metaData = new MetaData();

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "userId",
			fetch = FetchType.EAGER
	)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	Set<Booking> bookings;
	

	public UserAccount() {}
	public UserAccount(@NotNull String name, @NotNull @Email String email,
			@NotNull long mobileNo, @NotNull Address address, @NotNull String password,
			@NotNull Date dob, @NotNull UserGender gender) {
		super(name, email, mobileNo, address);
		this.password = password;
		this.gender=gender;
		this.dob=dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	
	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public MetaData getMetaData() {
		return metaData;
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
		return "UserAccount [role=" + role + ", active=" + active + ", dob=" + dob + ", bookings=" + bookings + ", email=" + email
				+ ", mobileNo=" + mobileNo + ", address=" + address + ", name=" + name + ", gender=" + gender + ", id=" + id + "]";
	}

}

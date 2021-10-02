package com.elanza48.TMS.model.dto;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_Account")
public class UserAccount {
	
	public enum UserRole{
		USER,
		MANAGER,
		ADMIN
	}
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String password;
	
	@Column
	@NotNull
	private String address1;
	
	@Column
	private String address2;
	
	@Column
	@NotNull
	private String district;
	
	@Column
	@NotNull
	private String state;
	
	@Column
	@NotNull
	@Size(min=6, max = 6)
	private int zip;
	
	@Embedded
	private Contact contact;
	
	@Column
	@NotNull
	@Enumerated
	private UserRole role= UserRole.USER;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	Set<Booking> bookings;

}

package com.elanza48.TMS.model.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "transport_info")
public class Transport extends IdentityName{
	
	public enum TransportMode{
		BUS,
	    CAR,
	    CRUISE,
	    STEAMER,
	    FLIGHT,
	    RAILWAY
	}
	
	public enum TransportEntity{
	    PRIVATE,
	    PUBLIC
	}
	
	@Column
	private String description;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransportMode mode = TransportMode.CAR;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransportEntity entity= TransportEntity.PRIVATE;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "transports")
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transportId")
	private Set<Ticket> tickets;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transportId")
	private Set<Enquiry> enquiries;
	
	public Transport() {
		super();
	}

	public Transport(@NotNull String name, String description, @NotNull TransportMode mode, @NotNull TransportEntity entity) {
		super(name);
		this.description = description;
		this.mode = mode;
		this.entity = entity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransportMode getMode() {
		return mode;
	}

	public void setMode(TransportMode mode) {
		this.mode = mode;
	}

	public TransportEntity getEntity() {
		return entity;
	}

	public void setEntity(TransportEntity entity) {
		this.entity = entity;
	}

	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Set<Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(Set<Enquiry> enquiries) {
		this.enquiries = enquiries;
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
		return "Transport [description=" + description + ", mode=" + mode + ", entity=" + entity + ", packages="
				+ packages + ", tickets=" + tickets + ", enquiries=" + enquiries + ", name=" + name + ", id=" + id
				+ "]";
	}
	
}

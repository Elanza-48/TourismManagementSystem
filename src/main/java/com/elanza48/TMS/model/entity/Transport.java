package com.elanza48.TMS.model.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "transport_info")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Transport extends IdentityName implements Serializable {
	
	public enum TransportMode{
		BUS,
	  CAR,
	  CRUISE,
	  STEAMER,
	  FLIGHT,
	  RAILWAY
	}
	
	@Column
	private String description;

	@Embedded
	@NotNull
	private MetaData metaData=new MetaData();
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private TransportMode mode = TransportMode.CAR;
	
	@Column(name="public")
	@NotNull
	private boolean isPublic= false;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "transports")
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Package> packages;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transportId")
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Ticket> tickets;
	
	public Transport() {}
	public Transport(@NotNull String name, String description, @NotNull TransportMode mode,
	 @NotNull boolean isPublic) {
		super(name);
		this.description = description;
		this.mode = mode;
		this.isPublic = isPublic;
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


	public Set<Package> getPackages() {
		return packages;
	}

	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public MetaData getMetaData() {
		return metaData;
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
		return "Transport [description=" + description + ", mode=" + mode + ", public=" + isPublic + ", packages="
				+ packages + ", tickets=" + tickets + ", name=" + name + ", id=" + id
				+ "]";
	}
	
}

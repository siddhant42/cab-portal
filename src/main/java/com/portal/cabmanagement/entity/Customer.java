package com.portal.cabmanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class Customer extends User {
	@OneToMany(mappedBy="customer")
	private List<Trip> trips;
	public double avgrating;
	@OneToOne
	@JoinColumn(name ="city_id", nullable = false)
	private City city;
}

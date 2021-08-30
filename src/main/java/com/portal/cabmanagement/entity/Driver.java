package com.portal.cabmanagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="driver")
public class Driver extends User {
	 @OneToMany(mappedBy="driver")
	private List<Trip> trips;
	 
	public double avgrating;
	
	@Column(name="lincensenumber")
	private String licenseNumber;
}

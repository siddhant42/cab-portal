package com.portal.cabmanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class Customer extends User {
	 @OneToMany(mappedBy="customer")
	private List<Trip> trips;
	public double avgrating;
}

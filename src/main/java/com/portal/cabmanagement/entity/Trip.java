package com.portal.cabmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="trip")
public class Trip {
	@Id
	private int id;
	private String source;
	private String destination;
	@ManyToOne
	@JoinColumn(name ="driver_id", nullable = false)
	private Driver driver;
	@ManyToOne
	@JoinColumn(name ="customer_id", nullable = false)
	private Customer customer;
	private double estimatedPrice;
	private double finalPrice;
	private int driverRating;
	private int customerRating;
	private String driverFeedback;
}

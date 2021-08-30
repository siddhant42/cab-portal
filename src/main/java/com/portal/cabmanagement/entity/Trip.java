package com.portal.cabmanagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="trip")
public class Trip {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name ="source_city_id", nullable = false)
	private City sourceCity;
	
	@OneToOne
	@JoinColumn(name ="dest_city_id", nullable = false)
	private City destinationCity;
	   
	@Column(name = "bookingtime", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp bookingTime;
	
	@Column(name="starttime", nullable = true)
	private Timestamp startTime;
	
	@Column(name="endtime", nullable = true)
	private Timestamp endTime;
	
	@ManyToOne
	@JoinColumn(name ="driver_id", nullable = false)
	private Driver driver;
	
	@ManyToOne
	@JoinColumn(name ="customer_id", nullable = false)
	private Customer customer;
	
	@Column(name="estimatedprice", nullable = true)
	private double estimatedPrice;
	
	@Column(name="finalprice", nullable = true)
	private double finalPrice;
	
	@Column(name="driverrating", nullable = true)
	private int driverRating;
	
	@Column(name="customerrating", nullable = true)
	private int customerRating;
	
	@Column(name="driverfeedback", nullable = true)
	private String driverFeedback;
}

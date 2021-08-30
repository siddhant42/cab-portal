package com.portal.cabmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name="cab")
public class Cab {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name ="city_id", nullable = false)
	private City city;
	private String vehicleNumber;
	@Column(columnDefinition = "ENUM('ON_TRIP', 'IDLE')", nullable = false)
	private Status status;
	@OneToOne
	@JoinColumn(name ="driver_id", nullable = false)
	private Driver driver;
}

package com.portal.cabmanagement.entity;

import javax.persistence.Entity;
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
	private int id;
	@OneToOne
	@JoinColumn(name ="city_id", nullable = false)
	private City city;
	private String vehicleNumber;
	@OneToOne
	@JoinColumn(name ="driver_id", nullable = false)
	private Driver driver;
}

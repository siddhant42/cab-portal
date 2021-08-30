package com.portal.cabmanagement.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="cab")
@DynamicUpdate(true)
public class Cab {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name ="city_id")
	private City city;
	
	@Column(name="vehiclenumber", nullable = true)
	private String vehicleNumber;
	
	@Column(columnDefinition = "ENUM('ON_TRIP', 'IDLE') default 'IDLE'", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy="cab")
	@JsonIgnore
	private List<Trip> trips;
	
	@Column(name="last_trip_end_time", nullable = true)
	private Timestamp lastTripEndTime;
}

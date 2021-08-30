package com.portal.cabmanagement.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@Table(name="trip")
@DynamicUpdate(true)
public class Trip {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@Column(name ="source_city_id", nullable = false)
	private int sourceCityId;
	
	@Column(name ="dest_city_id", nullable = false)
	private int destCityId;
	   
	@Column(name = "bookingtime", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp bookingTime;
	
	@Column(name="starttime")
	private Timestamp startTime;
	
	@Column(name="endtime")
	private Timestamp endTime;
	
	@ManyToOne
	@JoinColumn(name ="cab_id", nullable = false)
	private Cab cab;
	
	@Column(name ="user_id", nullable = false)
	private int userId;
	
	@Column(name="estimatedprice")
	private double estimatedPrice;
	
	@Column(name="finalprice")
	private double finalPrice;

}

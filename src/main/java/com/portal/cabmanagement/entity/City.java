package com.portal.cabmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="city")
public class City {
	@Id
	private int id;
	private String city;
	private String state;
}

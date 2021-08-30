package com.portal.cabmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;


@Entity
@Data
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate(true)
public class User {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	
	@Column(name="isActive",nullable = false, columnDefinition="bit default 1")
	private boolean isActive;

}

package com.portal.cabmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.cabmanagement.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}

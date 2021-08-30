package com.portal.cabmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.cabmanagement.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	Optional<City> findById(int id);
}

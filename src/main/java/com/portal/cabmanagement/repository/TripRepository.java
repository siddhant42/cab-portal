package com.portal.cabmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	Optional<Trip> findById(int id);
}

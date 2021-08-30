package com.portal.cabmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.Trip;
@Repository
public interface CabPortalRepository extends JpaRepository<Trip, Integer> {
}

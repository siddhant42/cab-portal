package com.portal.cabmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.Trip;
@Repository
public interface CabPortalRepository extends JpaRepository<Trip, Integer> {
	@Query(value = "select driver_id from trip where city=?1 order by endtime", nativeQuery=true)
	List<Integer> getMostAwaitedDriver(String city);
}

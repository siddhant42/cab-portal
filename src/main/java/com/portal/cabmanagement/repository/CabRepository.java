package com.portal.cabmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.Cab;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {
	@Query(value = "select * from cab where city_id=?1 and status='IDLE' order by last_trip_end_time limit 1", nativeQuery=true)
	Cab getMostAwaitedCab(int cityId);
}

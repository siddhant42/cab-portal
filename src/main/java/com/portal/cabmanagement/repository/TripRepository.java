package com.portal.cabmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portal.cabmanagement.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	Optional<Trip> findById(int id);
	
	@Query(value = "select * from trip where id=?1", nativeQuery=true)
	List<Trip> getAllTrip(int id);
	
	@Query(value = "select source_city_id,count(*) as totalcount from trip group by 1 order by 2 desc limit 5", nativeQuery=true)
	List<Object> getHighDemandCities();
	
	@Query(value = "select substring(bookingtime,12,2),count(*) as totalcount from trip where source_city_id=?1 group by 1 order by 2 desc limit 5", nativeQuery=true)
	List<Object> getHighDemandHours(int city_id);
	
	@Query(value = "select source_city_id,substring(bookingtime,12,2),count(*) from trip group by 1,2 order by 3 desc limit 5", nativeQuery=true)
	List<Object> getHighDemandCityWithTime();
}

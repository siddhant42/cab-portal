package com.portal.cabmanagement.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.cabmanagement.entity.City;
import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.repository.CabPortalRepository;
import com.portal.cabmanagement.repository.CustomerRepository;
import com.portal.cabmanagement.repository.DriverRepository;

@Service
public class CabPortalService {
	@Autowired
	private CabPortalRepository cabPortalRepo;
	
	@Autowired
	DriverRepository driverRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	private Random random = new Random();
	
	
	
	public Trip bookCab(Trip trip) {
		City cityObj = trip.getSourceCity();
		String city = cityObj.getName();
		List<Integer> drivers = cabPortalRepo.getMostAwaitedDriver(city);
		int index = random.nextInt(drivers.size());
		int driver_id = drivers.get(index);
		
		Trip newtrip = new Trip();
		newtrip.setBookingTime(new Timestamp(System.currentTimeMillis()));
		newtrip.setCustomer(trip.getCustomer());
		newtrip.setDriver(driverRepo.getById(driver_id));
		return trip;
	}
}

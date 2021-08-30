package com.portal.cabmanagement.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.cabmanagement.entity.Cab;
import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.repository.CabRepository;
import com.portal.cabmanagement.repository.CityRepository;
import com.portal.cabmanagement.repository.TripRepository;
import com.portal.cabmanagement.repository.UserRepository;

@Service
public class CabPortalService {

	@Autowired
	private CabRepository cabRepo;
	
	@Autowired
	private TripRepository tripRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	public Trip bookCab(int userid, int sourceCityId, int destCityId) {
		Cab cab = cabRepo.getMostAwaitedCab(sourceCityId);
		if(cab==null) {
			throw new RuntimeException("no cab available now. Please try after some time");
		}
		Trip trip = new Trip();
		trip.setCab(cab);
		trip.setSourceCityId(sourceCityId);
		trip.setDestCityId(destCityId);
		trip.setBookingTime(new Timestamp(System.currentTimeMillis()));
		trip.setUserId(userid);
		Trip newtrip = tripRepo.saveAndFlush(trip);
		
		return newtrip;
	}

	public void startTrip(Trip trip) {
		trip = tripRepo.findById(trip.getId()).get();
		trip.setStartTime(new Timestamp(System.currentTimeMillis()));
		trip.getCab().setCity(null);
		double estimatedPrice = caculateEstPrice(trip.getSourceCityId(), trip.getDestCityId());
		trip.setEstimatedPrice(estimatedPrice);
		tripRepo.saveAndFlush(trip);
	}

	public void endTrip(Trip trip) {
		trip = tripRepo.findById(trip.getId()).get();
		trip.setEndTime(new Timestamp(System.currentTimeMillis()));
		trip.getCab().setCity(cityRepo.getById(trip.getDestCityId()));
		double finalPrice = caculateFinalPrice(trip.getSourceCityId(), trip.getDestCityId());
		trip.setFinalPrice(finalPrice);
		tripRepo.saveAndFlush(trip);
	}
	private double caculateEstPrice(int sourceCityId, int destCityId) {
		// dummy value
		return 50.0d;
	}
	private double caculateFinalPrice(int sourceCityId, int destCityId) {
		// dummy value
		return 50.0d;
	}
}

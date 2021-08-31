package com.portal.cabmanagement.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.cabmanagement.entity.Cab;
import com.portal.cabmanagement.entity.City;
import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.repository.CabRepository;
import com.portal.cabmanagement.repository.CityRepository;
import com.portal.cabmanagement.repository.TripRepository;

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
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		trip.setEndTime(currentTime);
		trip.getCab().setCity(cityRepo.getById(trip.getDestCityId()));
		trip.getCab().setLastTripEndTime(currentTime);
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

	public void register(Cab cab) {
		cabRepo.saveAndFlush(cab);
	}

	public void register(City city) {
		cityRepo.saveAndFlush(city);
	}

	public void update(Cab cab) {
		Cab cab1 = cabRepo.findById(cab.getId()).get();
		if(cab.getCity()!=null)
		cab1.setCity(cab.getCity());
		
		if(cab.getStatus()!=null)
		cab1.setStatus(cab.getStatus());
		
		cabRepo.saveAndFlush(cab1);
	}

	public Map<City,Integer> getHighDemandCities() {
		List<Object> list = tripRepo.getHighDemandCities();
		Map<City,Integer> map = new HashMap<>();
		for(Object obj : list) {
			Object[] objs = (Object[])obj;
			Integer id = (Integer)objs[0];
			BigInteger count = (BigInteger)objs[1];
			map.put(cityRepo.findById(id).get(), count.intValue());
		}
		return map;
	}
	public Map<String,Integer> getHighDemandHours(int city_id) {
		List<Object> list = tripRepo.getHighDemandHours(city_id);
		Map<String,Integer> map = new HashMap<>();
		for(Object obj : list) {
			Object[] objs = (Object[])obj;
			String time = (String)objs[0];
			BigInteger count = (BigInteger)objs[1];
			map.put(time+" PM", count.intValue());
		}
		System.out.println(map);
		return map;
	}
	public List<Object> getHighDemandCityWithTime() {
		List<Object> list = tripRepo.getHighDemandCityWithTime();
		List<Object> reslist = new ArrayList<>();
		for(Object obj : list) {
			Object[] objs = (Object[])obj;
			Object[] objarr = new Object[objs.length];
			Integer id = (Integer)objs[0];
			String time = (String)objs[1];
			BigInteger count = (BigInteger)objs[2];
			objarr[0] = cityRepo.findById(id).get();
			objarr[1] = time+" PM";
			objarr[2] = count.intValue();
			reslist.add(objarr);
		}
		return reslist;
	}
}

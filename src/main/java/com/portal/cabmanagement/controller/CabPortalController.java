package com.portal.cabmanagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.cabmanagement.entity.Cab;
import com.portal.cabmanagement.entity.City;
import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.service.CabPortalService;

@RestController
@RequestMapping("/cabportal")
public class CabPortalController {
	
	@Autowired
	CabPortalService cabPortalService;
	
	@GetMapping("/{userid}/cab")
	public Trip bookCab(@PathVariable int userid,int sourceCityId, int destCityId) {
		return cabPortalService.bookCab(userid,sourceCityId, destCityId);
	}
	@PutMapping("/trip/start")
	public void startTrip(@RequestBody Trip trip) {
		cabPortalService.startTrip(trip);
	}
	@PutMapping("/trip/end")
	public void endTrip(@RequestBody Trip trip) {
		cabPortalService.endTrip(trip);
	}
	@PostMapping("/cab")
	public void registerCab(@RequestBody Cab cab) {
		System.out.println(cab);
		cabPortalService.register(cab);
	}
	@PutMapping("/cab")
	public void updateCab(Cab cab) {
		cabPortalService.update(cab);
	}
	@PostMapping("/city")
	public void registerCity(@RequestBody City city) {
		cabPortalService.register(city);
	}
	@GetMapping("/city")
	public List<City> getAllCities() {
		return cabPortalService.getAllCities();
	}
	@GetMapping("/highdemandcities")
	public Map<City,Integer> highDemandCities() {
		return cabPortalService.getHighDemandCities();
	}
	@GetMapping("/highdemandHours/{city_id}")
	public Map<String,Integer> highDemandHours(@PathVariable int city_id) {
		return cabPortalService.getHighDemandHours(city_id);
	}
	@GetMapping("/highdemandcitywithtime")
	public List<Object> highDemandCityWithTime() {
		return cabPortalService.getHighDemandCityWithTime();
	}
	@GetMapping("/idletime/{id}")
	public String getIdleTime(@PathVariable int id, String from, String to) {
		LocalDateTime fromtime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime tilltime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return cabPortalService.getIdleTime(id,fromtime,tilltime);
	}
}

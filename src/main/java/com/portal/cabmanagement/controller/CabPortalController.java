package com.portal.cabmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.service.CabPortalService;

@RestController
@RequestMapping("/cabportal/{userid}")
public class CabPortalController {
	
	@Autowired
	CabPortalService cabPortalService;
	
	@GetMapping("/cab")
	public Trip bookCab(@PathVariable int userid,int sourceCityId, int destCityId) {
		return cabPortalService.bookCab(userid,sourceCityId, destCityId);
	}
	@PutMapping("/trip/start")
	public void startTrip(@PathVariable int userid, @RequestBody Trip trip) {
		cabPortalService.startTrip(trip);
	}
	@PutMapping("/trip/end")
	public void endTrip(@PathVariable int userid, @RequestBody Trip trip) {
		cabPortalService.endTrip(trip);
	}
}

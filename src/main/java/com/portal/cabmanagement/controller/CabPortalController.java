package com.portal.cabmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.cabmanagement.entity.Customer;
import com.portal.cabmanagement.entity.Trip;
import com.portal.cabmanagement.service.CabPortalService;

@RestController
@RequestMapping("/cabportal")
public class CabPortalController {
	@Autowired
	CabPortalService cabPortalService;
	
	@GetMapping("/cab")
	Trip bookCab(@RequestBody Customer customer) {
		return cabPortalService.bookCab(customer);
	}
}

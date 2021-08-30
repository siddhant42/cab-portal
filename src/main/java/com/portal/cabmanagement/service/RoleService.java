package com.portal.cabmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.cabmanagement.entity.Role;
import com.portal.cabmanagement.repository.RoleRepository;


@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepo;
	
	public Role getDefaultRole() {
		return roleRepo.findByName("ROLE_USER");
	}
}

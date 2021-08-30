package com.portal.cabmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portal.cabmanagement.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r FROM Role r WHERE r.name = :name")
	public Role findByName(String name);

}

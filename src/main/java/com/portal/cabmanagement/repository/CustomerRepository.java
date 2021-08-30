package com.portal.cabmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.cabmanagement.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

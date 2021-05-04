package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.entities.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
	Customer getCustomer(long customerId);
	List<Customer> getAllCustomers();
	Customer getCustomerByPetId(long petId);
	
}

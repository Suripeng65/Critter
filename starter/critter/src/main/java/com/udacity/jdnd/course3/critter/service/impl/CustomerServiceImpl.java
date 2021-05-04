package com.udacity.jdnd.course3.critter.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(long customerId) {
		Optional<Customer> searchResult = customerRepository.findById(customerId);
		if(searchResult.isPresent()) {
			return searchResult.get();
		}else {
			throw new NoSuchElementException("Employee with id:" + customerId + " can not be found");
		}
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByPetId(long petId) {	
		return customerRepository.findCustomerByPetId(petId);
	}

}

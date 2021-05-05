package com.udacity.jdnd.course3.critter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	@Override
	public List<Schedule> getScheduleByPetId(long petId) {
		return scheduleRepository.findAllByPetsId(petId);
	}

	@Override
	public List<Schedule> getScheduleByEmployeeId(long employeeId) {
		return scheduleRepository.findAllByEmployeesId(employeeId);
	}

	@Override
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}
	
	@Override
	public List<Schedule> getSchedulesForCustomer(long customerId){
		Optional<Customer> optionalUser = customerRepository.findById(customerId);
		Customer customer = optionalUser.orElse(null);
		List<Pet> pets = Objects.requireNonNull(customer).getPets();
		ArrayList<Schedule> schedules = new ArrayList<>();
		for (Pet pet : pets) {
            schedules.addAll(scheduleRepository.findAllByPetsId(pet.getId()));
        }
		return schedules;
	}

}

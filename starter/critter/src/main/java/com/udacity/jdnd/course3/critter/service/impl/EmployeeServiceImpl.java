package com.udacity.jdnd.course3.critter.service.impl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(long employeeId) {
		Optional<Employee> searchResult = employeeRepository.findById(employeeId);
		if(searchResult.isPresent()) {
			return searchResult.get();
		}else {
			throw new NoSuchElementException("Employee with id:" + employeeId + " can not be found");
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void setAvailability(Set<DayOfWeek> availableDays, long employeeId) {
		Optional<Employee> searchResult = employeeRepository.findById(employeeId);
		if(searchResult.isPresent()) {
			searchResult.get().setDaysAvailable(availableDays);
			employeeRepository.save(searchResult.get());
		}else {
            throw new NoSuchElementException("Employee with id:" + employeeId + " can not be found");
		}
		
	}

	@Override
	public List<Employee> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek availableDays) {
		List<Employee> result = new ArrayList<Employee>();
		List<Employee> availableEmployees = new ArrayList<Employee>();
		
		availableEmployees = employeeRepository.findAllByDaysAvailable(availableDays);
		
		for(Employee e : availableEmployees) {
			if(e.getSkills().containsAll(skills)) {
				result.add(e);
			}
		}
		return result;
	}

}

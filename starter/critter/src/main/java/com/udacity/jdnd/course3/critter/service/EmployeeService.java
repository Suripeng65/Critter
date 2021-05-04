package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.EmployeeSkill;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	Employee getEmployee(long employeeId);
	List<Employee> getAllEmployees();
	void setAvailability(Set<DayOfWeek> availableDays, long employeeId);
	List<Employee> getEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek availableDays);
	
}

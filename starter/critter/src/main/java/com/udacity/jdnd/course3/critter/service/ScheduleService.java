package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.entities.Schedule;

public interface ScheduleService {
	Schedule saveSchedule(Schedule schedule);
	List<Schedule> getScheduleByPetId(long petId);
	List<Schedule> getScheduleByEmployeeId(long employeeId);
//	List<Schedule> getScheduleByCustomerId(long customerId);
	List<Schedule> getAllSchedules();
	List<Schedule> getSchedulesForCustomer(long customerId);
}

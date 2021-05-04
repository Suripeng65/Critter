package com.udacity.jdnd.course3.critter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	PetService petService;
	
	@Autowired
	EmployeeService employeeService;
	
	 @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		 Schedule schedule = scheduleService.saveSchedule(dtoToSchedule(scheduleDTO));
		 return scheduleToDTO(schedule);
	 }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
    	List<ScheduleDTO> results = new ArrayList<ScheduleDTO>();
    	List<Schedule> scheduleList = scheduleService.getAllSchedules();
    	
    	for(Schedule s : scheduleList) {
    		results.add(scheduleToDTO(s));
    	}
    	
    	return results;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByPetId(petId);
        List<ScheduleDTO> results = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                results.add(scheduleToDTO(s));
            }
        }
        
        return results;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        List<ScheduleDTO> results = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                results.add(scheduleToDTO(s));
            }
        }
        
        return results;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByCustomerId(customerId);
        List<ScheduleDTO> results = new ArrayList<>();

        if(scheduleList.size() != 0){
            for(Schedule s : scheduleList){
                results.add(scheduleToDTO(s));
            }
        }
        return results;
    }
    
    private ScheduleDTO scheduleToDTO(Schedule schedule){

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> petList = petService.getAllPets();
        List<Employee> employeeList = employeeService.getAllEmployees();

        List<Long> petIds = new ArrayList<>();
        if(petList.size() != 0){
            for(Pet p : petList){
                petIds.add(p.getId());
            }
        }

        List<Long> employeeIds = new ArrayList<>();
        if(employeeList.size() != 0){
            for(Employee e : employeeList){
                employeeIds.add(e.getId());
            }
        }
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);

        return scheduleDTO;
    }

    private Schedule dtoToSchedule(ScheduleDTO scheduleDTO){

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> petIds = scheduleDTO.getPetIds();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();

        List<Pet> petList = new ArrayList<>();
        if(petIds.size() != 0){
            for(Long id : petIds){
                Pet pet = petService.getPet(id);
                petList.add(pet);
            }
        }

        List<Employee> employeeList = new ArrayList<>();
        if(petIds.size() != 0){
            for(Long id : employeeIds){
                Employee employee = employeeService.getEmployee(id);
                employeeList.add(employee);
            }
        }

        schedule.setEmployees(employeeList);
        schedule.setPets(petList);

        return schedule;
    }
}

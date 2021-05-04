package com.udacity.jdnd.course3.critter.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PetService petService;
	
	@Autowired
	EmployeeService employeeService;
	
	 @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
		 Customer customer = customerService.saveCustomer(dtoToCustomer(customerDTO));
		 return customerToDTO(customer);
	 }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
    	List<Customer> customerList = customerService.getAllCustomers();
    	List<CustomerDTO> results = new ArrayList<CustomerDTO>();
    	
    	for(Customer c : customerList) {
    		results.add(customerToDTO(c));
    	}
    	
    	return results;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
    	//TODO!! if this implementation is correct or not
    	
        Pet pet = petService.getPet(petId);
        Customer customer = pet.getOwner();
//        Customer customer = customerService.getCustomerByPetId(petId);
        return customerToDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
    	Employee employee = employeeService.saveEmployee(dtoToEmployee(employeeDTO));
    	return employeeToDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
    	return employeeToDTO(employeeService.getEmployee(employeeId)); 	
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
    	employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
    	
    	List<Employee> employeeList = employeeService.getEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());
    	List<EmployeeDTO> results = new ArrayList<EmployeeDTO>();
    	
    	for(Employee e : employeeList) {
    		results.add(employeeToDTO(e));
    	}
    	
    	return results;
    }
    
    public CustomerDTO customerToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();

        List<Long> petIds = new ArrayList<>();
        if(pets.size() != 0){
            for(Pet p : pets){
                Long id = p.getId();
                petIds.add(id);
            }
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    public Customer dtoToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> petIds = customerDTO.getPetIds();

        List<Pet> pets = new ArrayList<>();
        if(petIds != null && petIds.size() != 0){
            for(Long id : petIds){
                pets.add(petService.getPet(id));
            }
        }
        customer.setPets(pets);
        return customer;
    }

    public EmployeeDTO employeeToDTO (Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    public Employee dtoToEmployee (EmployeeDTO dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }
}

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

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	PetService petService;
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
		Pet pet = petDtoToPet(petDTO);
		Customer owner;
		System.out.println("hit pet post controller");
		System.out.println(pet);
        if((Long) petDTO.getOwnerId() != 0) {
            owner = customerService.getCustomer(petDTO.getOwnerId());
            pet.setOwner(owner);
            owner.addPet(pet);
        }
		pet = petService.savePet(pet);
		
		return petToPetDTO(pet);
		
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
    	return petToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
    	List<Pet> petResults =  petService.getAllPets(); 
    	List<PetDTO> results = new ArrayList<PetDTO>();
    	   
        for(Pet p : petResults) {
        	results.add(petToPetDTO(p));
        }
        
        return results;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    	List<PetDTO> results = new ArrayList<PetDTO>();
    	List<Pet> petList = petService.getPetsByOwnerId(ownerId);
    	
    	for(Pet p : petList) {
    		results.add(petToPetDTO(p));
    	}
    	
    	return results;
    }
    
    private Pet petDtoToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
    
    private PetDTO petToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if(pet.getOwner() != null){
            petDTO.setOwnerId(pet.getOwner().getId());
        }
        return petDTO;
    }

}

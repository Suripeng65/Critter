package com.udacity.jdnd.course3.critter.service.impl;

import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.PetService;

@Transactional
@Service
public class PetServiceImpl implements PetService{
	@Autowired
	PetRepository petRepository;
	
	@Override
	public Pet savePet(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public Pet getPet(long petId) {
		Optional<Pet> searchResult = petRepository.findById(petId);
		if(searchResult.isPresent()) {
			return searchResult.get();
		}else {
			throw new NoSuchElementException("Pet with id:" + petId + " can not be found");
		}
		
	}

	@Override
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}

	@Override
    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findAllPetsByCustomerId(ownerId);
    }

}

package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import com.udacity.jdnd.course3.critter.entities.Pet;

public interface PetService {
	Pet savePet(Pet pet);
	Pet getPet(long petId);
	List<Pet> getAllPets();
	List<Pet> getPetsByOwnerId(long ownerId);
}

package com.udacity.jdnd.course3.critter.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

@Entity
public class Customer {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private  long id;

	    private String name;

	    private String phoneNumber;

	    private String notes;

	    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Pet> pets;

	    public void addPet(Pet pet){
	        pets.add(pet);
	    }

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public String getNotes() {
	        return notes;
	    }

	    public void setNotes(String notes) {
	        this.notes = notes;
	    }

	    public List<Pet> getPets() {
	        return pets;
	    }

	    public void setPets(List<Pet> pets) {
	        this.pets = pets;
	    }
}

package com.udacity.jdnd.course3.critter.entities;

import java.time.LocalDate;

import javax.persistence.*;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.entities.PetType;

@Entity
public class Pet {
	  	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private long id;

	    @Enumerated(EnumType.STRING)
	    private PetType type;

	    private String name;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "owner_id")
	    private Customer owner;
	    private LocalDate birthDate;

	    private String notes;

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public PetType getType() {
	        return type;
	    }

	    public void setType(PetType type) {
	        this.type = type;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Customer getOwner() {
	        return owner;
	    }

	    public void setOwner(Customer owner) {
	        this.owner = owner;
	    }

	    public LocalDate getBirthDate() {
	        return birthDate;
	    }

	    public void setBirthDate(LocalDate birthDate) {
	        this.birthDate = birthDate;
	    }

	    public String getNotes() {
	        return notes;
	    }

	    public void setNotes(String notes) {
	        this.notes = notes;
	    }
}

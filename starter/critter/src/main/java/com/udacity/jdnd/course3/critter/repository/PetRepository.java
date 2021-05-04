package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    @Query("SELECT p FROM Pet p WHERE p.owner.id = :ownerId")
    List<Pet> findAllPetsByCustomerId(@Param("ownerId") long ownerId);
}

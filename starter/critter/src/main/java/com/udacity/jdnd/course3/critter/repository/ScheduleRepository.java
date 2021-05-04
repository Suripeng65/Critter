package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>{
    List<Schedule> findAllByPetsId(long id);
    List<Schedule> findAllByEmployeesId(long id);
    
    @Query("SELECT s FROM Schedule s JOIN s.pets p JOIN p.owner o WHERE o.id=:id")
    List<Schedule> findAllByCustomerId(@Param("id") long id);
}

package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    @Query("SELECT E FROM Employee E WHERE :day MEMBER OF E.daysAvailable")
    List<Employee> findAllByDaysAvailable(@Param("day") DayOfWeek day);
}

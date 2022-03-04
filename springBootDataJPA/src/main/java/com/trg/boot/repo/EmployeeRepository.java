package com.trg.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trg.boot.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Integer> {
	
	List<Employee> findByName(String name); // no query reqd as method name suggests to find by name property
	
	@Query("from Employee e where e.salary > :sal")
	List<Employee> findBySalGT(@Param ("sal") float salary); //@Param annotation maps this methods' argument salary to the Query's parameter sal
	
	@Query("from Employee e where e.salary between :lowsal and :highsal")
	List<Employee> findOnSalBetween(@Param ("lowsal") float sal1, @Param("highsal") float sal2);

	List<Employee> findBySalaryBetween(float sal1, float sal2);
}


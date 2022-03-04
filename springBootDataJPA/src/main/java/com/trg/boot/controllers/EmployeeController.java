package com.trg.boot.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.trg.boot.entity.AppRes;
import com.trg.boot.entity.Employee;
import com.trg.boot.repo.EmployeeRepository;

@RestController
@RequestMapping("employees")

@CrossOrigin(origins="http://localhost:3000")

public class EmployeeController {
	
@Autowired
EmployeeRepository repo;

@PostMapping
public ResponseEntity<AppRes> saveEmployee(@RequestBody Employee e) {
	if (repo.existsById(e.getEmpid())) {
		return new ResponseEntity<AppRes>(
				new AppRes("SAVE FAIL!!", "Employee with Id " + e.getEmpid() + " already exists"),
				HttpStatus.BAD_REQUEST);
		} 
	else {
		repo.save(e);
		return new ResponseEntity<AppRes>(
				new AppRes("SUCCESS", "Employee data with Id " + e.getEmpid() + " successfully created"), HttpStatus.CREATED);
		}
	}

@PutMapping
public ResponseEntity<AppRes> modifyEmployee(@RequestBody Employee e) {
	if (repo.existsById(e.getEmpid())) {
		repo.save(e);
		return new ResponseEntity<AppRes>(new AppRes("Successfully updated!!","Employee with Id " + e.getEmpid() + "successfully updated"), HttpStatus.CREATED);
		} 
	else {
		repo.save(e);
		return new ResponseEntity<AppRes>(
				new AppRes("NOT FOUND!!", "Employee data with Id " + e.getEmpid() + " not found"), HttpStatus.NOT_FOUND);
		}
	}

@DeleteMapping("{eid}")
public ResponseEntity<AppRes> deleteEmployee(@PathVariable("eid") int Empid) {
	if (repo.existsById(Empid)) {
		repo.deleteById(Empid);
		return new ResponseEntity<AppRes>(
				new AppRes("Successfully deleted!!", "Student with Id " + Empid + " successfully deleted"),HttpStatus.CREATED);
		} 
	else
		return new ResponseEntity<AppRes>(
				new AppRes("NOT FOUND!!", "Student with Id " + Empid + " not found"), HttpStatus.BAD_REQUEST);
}

@GetMapping("{eid}")
public ResponseEntity<?> getEmployee(@PathVariable("eid") int Empid) {
	Optional<Employee> opt = repo.findById(Empid);
	if (opt.isPresent()) {
		return new ResponseEntity<Employee>(opt.get(),HttpStatus.OK);
		} 
	else
		return new ResponseEntity<AppRes>(
				new AppRes("NOT FOUND!!", "Student with Id " + Empid + " is not found"), HttpStatus.NOT_FOUND);
}

@GetMapping
public ResponseEntity<?> getAllEmployee() {
	List<Employee> list = repo.findAll();
	if (list.size() == 0) {
		return new ResponseEntity<AppRes>(new AppRes("GET FAIL!!", "No employee exists in database"),HttpStatus.NOT_FOUND);
		}
	return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

@GetMapping("salrange/{low}/{high}")
public ResponseEntity<?> getEmployeesonSalRange(@PathVariable("low") float s1, @PathVariable("high") float s2) {
	List<Employee> list = repo.findBySalaryBetween(s1,s2);
	if (list.size() == 0) {
		return new ResponseEntity<AppRes>(new AppRes("GET FAIL!!", "No employee meeting the criteria"),HttpStatus.NOT_FOUND);
		}
	return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
}


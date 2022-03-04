package com.trg.boot.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_tbl")
public class Employee {

	@Id
	private int empid;
	
	@Column(length = 20)
	private String name;
	
	@Column(columnDefinition = "decimal(8,2)")
	private float salary;
	private Date dob;
	
	public Employee() {
		super();
	}

	public Employee(int empid, String name, float salary, Date dob) {
		super();
		this.empid = empid;
		this.name = name;
		this.salary = salary;
		this.dob = dob;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}

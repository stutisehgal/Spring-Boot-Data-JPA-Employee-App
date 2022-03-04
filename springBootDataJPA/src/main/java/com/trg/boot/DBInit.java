package com.trg.boot;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.trg.boot.entity.Employee;
import com.trg.boot.repo.EmployeeRepository;

@Component //spring bean -> component annotation
public class DBInit implements CommandLineRunner{
	
	@Autowired
	EmployeeRepository repo;
	
	Logger logger = LoggerFactory.getLogger(DBInit.class);

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("DBInit started");
		
		repo.save(new Employee(100,"Suresh",25000.0f, Date.valueOf("1989-10-21")));
		repo.save(new Employee(200,"Mahesh",12000.0f, Date.valueOf("1986-11-21")));
		repo.save(new Employee(300,"Ganesh",23000.0f, Date.valueOf("1989-12-21")));
		repo.save(new Employee(400,"Satish",29000.0f, Date.valueOf("1985-3-21")));
		repo.save(new Employee(500,"Naresh",28000.0f, Date.valueOf("1984-4-21")));
		repo.save(new Employee(600,"Satish",31000.0f, Date.valueOf("1983-5-21")));
		
		long count = repo.count();
		
		logger.info(count+" objects added to emp_tbl");
		
		//command line runner class' bean is automatically executed whenever application is started
	}

}

package com.jrp.pma;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;

@SpringBootApplication(scanBasePackages = {"com.jrp.pma","com.jrp.utils"})
public class ProjectManegementApplication {
	

	
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManegementApplication.class, args);
	}


}


package com.jrp.pma.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public List<Employee> getAll() {
		return empRepo.findAll();

	}
	
	public List<EmployeeProject> employeeProject() {
		return empRepo.employeeProject();
	}

	public Employee findByEmployeeId(long theId) {
		// TODO Auto-generated method stub
		return empRepo.findByEmployeeId(theId);
	}

	public void delete(Employee theEmp) {
	
	
		empRepo.delete(theEmp);
		
	}
	
	
}

package com.jrp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees" )
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	

	@GetMapping
	public String getEmployees(Model model) {
		List<Employee> employees = empService.getAll();
		model.addAttribute("employeesList", employees);
		return "employee/employees";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee);
		
		return "employee/new-employee";
	}
	
	
	@PostMapping("/save")
	public String createEmployee(Model model,@Valid Employee employee , Errors errors) {
		
		if(errors.hasErrors()) 
			return "employee/new-employee";
		
		
		empService.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		return "employee/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
		
	}
	
	


}

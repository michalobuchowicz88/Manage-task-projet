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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;

	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String getEmployees(Model model) {
	
		List<Project> projects = proService.getAll();
		model.addAttribute("projectsList", projects);
		return "project/projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		List<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "project/new-project";
	}
	

	
	@PostMapping(path="/save")
	public String createProject(Model model, @Valid Project aProject, Errors errors) {

		if(errors.hasErrors()) 
			return "project/new-project";
		
		proService.save(aProject);
		return "redirect:/projects";
	}
	
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
		
		Project thePro = proService.findByProjectId(theId);
		model.addAttribute("project", thePro);
		return "project/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		
		Project thePro = proService.findByProjectId(theId);
		proService.delete(thePro);
		return "redirect:/projects";
		
	}
	
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		List<TimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		
		System.out.println("-------------projet timelines-----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "project/project-timelines";
	
	}
	
	

	
}

package com.jrp.pma.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;


@RepositoryRestResource(collectionResourceRel = "apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, value = "SELECT E.FIRST_NAME AS FIRSTNAME , E.LAST_NAME AS LASTNAME, COUNT(PE.EMPLOYEE_ID) AS " + 
			"PROJECTCOUNT FROM EMPLOYEE E LEFT JOIN PROJECT_EMPLOYEE PE ON PE.EMPLOYEE_ID = E.EMPLOYEE_ID " + 
			"GROUP BY E.FIRST_NAME, E.LAST_NAME ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProject();

	public Employee findByEmail(String value);

	public Employee findByEmployeeId(long theId);

}

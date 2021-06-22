package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{

	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value ="SELECT STAGE AS LABEL, COUNT(*) AS VALUE " + 
			"FROM PROJECT " + 
			"GROUP BY STAGE")
	public List<ChartData> getProjectStatus();

	public Project findByProjectId(long theId);
	
	@Query(nativeQuery = true, value= "SELECT name as projectName, start_date as startDate, end_date as endDate"
			+ " FROM project WHERE start_date is not null")
	
	public List<TimeChartData> getTimeData();
	
	
}

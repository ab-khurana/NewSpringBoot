package com.cg.rp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectAllocation {

	@Id
	@GeneratedValue
	private Long projectAllocationId;
	
	private String employeeName;
	
	private String projectName;
	
	private Date startDate;
	
	private Date endDate;
	
	private String status;
	
	public Long getProjectAllocationId() {
		return projectAllocationId;
	}

	public void setProjectAllocationId(Long projectAllocationId) {
		this.projectAllocationId = projectAllocationId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}

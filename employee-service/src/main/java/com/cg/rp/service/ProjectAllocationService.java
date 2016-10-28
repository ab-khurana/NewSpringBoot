package com.cg.rp.service;

import java.util.Collection;
import java.util.List;

import com.cg.rp.model.Employee;
import com.cg.rp.model.ProjectAllocation;

public interface ProjectAllocationService {
	
	public ProjectAllocation get(Long projectAllocationId);
	public Collection<ProjectAllocation> get();
	public List<ProjectAllocation> get(String projectName);
	public ProjectAllocation create(ProjectAllocation newProjectAllocation);
	public ProjectAllocation update(ProjectAllocation existingProjectAllocation);
	public Boolean delete(Long projectAllocationId);
	
	
	
}

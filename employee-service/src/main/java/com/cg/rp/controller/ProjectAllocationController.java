package com.cg.rp.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rp.model.Employee;
import com.cg.rp.model.ProjectAllocation;
import com.cg.rp.service.ProjectAllocationService;

@RestController
@RequestMapping("/projectallocations")
public class ProjectAllocationController {

	ProjectAllocationService allocationService;
	
	@Autowired
	public ProjectAllocationController(ProjectAllocationService allocationService) {
		this.allocationService = allocationService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity get() {
		Collection<ProjectAllocation> projectAllocationList;
		try {
			projectAllocationList = allocationService.get();
			if(projectAllocationList.isEmpty()) {
				return new ResponseEntity("No employees is allocated", HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity(projectAllocationList, HttpStatus.OK);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Failed to allocated employees", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{projectAllocationId}", method=RequestMethod.GET)
	public ResponseEntity get(@PathVariable("projectAllocationId") Long projectAllocationId) {
		ProjectAllocation projectAllocation;
		try {
			projectAllocation = allocationService.get(projectAllocationId);
			if(null == projectAllocation) {
				return new ResponseEntity("Project Allocation with ID " + projectAllocationId + " not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(projectAllocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Failed to get Project Allocation with ID " + projectAllocationId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/byProjectName/{projectName}", method=RequestMethod.GET)
	public ResponseEntity get(@PathVariable("projectName") String projectName) {
		ProjectAllocation projectAllocation;
		try {
			List projectsByName = allocationService.get(projectName);
			if(null == projectsByName) {
				return new ResponseEntity("Employee with projectName " + projectName + " not found", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(projectsByName, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Failed to get employee with projectName " + projectName, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity create(@RequestBody ProjectAllocation newProjectAllocation) {
		ProjectAllocation projectAllocation;;
		ResponseEntity entity = null;
		try {
			projectAllocation = allocationService.create(newProjectAllocation);
			if(null == projectAllocation) {
			 entity =new ResponseEntity("Failed to allocate employee in project", HttpStatus.INTERNAL_SERVER_ERROR);
			 return entity;
			} else {
				 entity= new ResponseEntity(projectAllocation, HttpStatus.CREATED);
				 return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity("Failed to allocate employee in project", HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
	}
	
	@RequestMapping(value="/{projectAllocationId}", method=RequestMethod.PUT)
	public ResponseEntity update(@PathVariable("projectAllocationId") Long projectAllocationId, @RequestBody ProjectAllocation existingProjectAllocation) {
		ProjectAllocation projectAllocation;
		try {
			projectAllocation = allocationService.get(projectAllocationId);
			if(null == projectAllocation) {
				return new ResponseEntity("Project allocation with ID " + projectAllocationId + " not found", HttpStatus.NOT_FOUND);
			} else {
				projectAllocation.setProjectName(existingProjectAllocation.getProjectName());
				projectAllocation.setEmployeeName(existingProjectAllocation.getEmployeeName());
				projectAllocation.setStartDate(existingProjectAllocation.getStartDate());
				projectAllocation.setEndDate(existingProjectAllocation.getEndDate());
				projectAllocation.setStatus(existingProjectAllocation.getStatus());
				
				ProjectAllocation updatedprojectAllocation = allocationService.create(projectAllocation);
				if(null == updatedprojectAllocation) {
					return new ResponseEntity("Failed to update project allocation", HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					return new ResponseEntity(updatedprojectAllocation, HttpStatus.OK);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Failed to update project allocation", HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(value="/{projectAllocationId}", method=RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("projectAllocationId") Long projectAllocationId) {
		Boolean isDeleted;
		System.out.println(projectAllocationId);
		ProjectAllocation currentprojectAllocation;
		try {
			currentprojectAllocation = allocationService.get(projectAllocationId);
			if(null == currentprojectAllocation) {
				System.out.println("inside 1st if");
				return new ResponseEntity("Project allocationId with ID " + projectAllocationId + " not found", HttpStatus.NOT_FOUND);
			} else {
				isDeleted = allocationService.delete(projectAllocationId);
				System.out.println("is delete " + isDeleted );
				if(null == isDeleted || !isDeleted) {
					System.out.println("inside 1st if");
					return new ResponseEntity("Failed to delete employee from project with ID " + projectAllocationId, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					return new ResponseEntity("Project allocation with ID " + projectAllocationId + " deleted succssfully", HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Failed to delete employee from project", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}

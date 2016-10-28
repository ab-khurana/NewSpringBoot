package com.cg.rp.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rp.model.Employee;
import com.cg.rp.model.ProjectAllocation;
import com.cg.rp.repository.ProjectAllocationRepository;
import com.cg.rp.service.ProjectAllocationService;

@Service
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	ProjectAllocationRepository employeeRepository;
	
	@Autowired
	public ProjectAllocationServiceImpl(ProjectAllocationRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public ProjectAllocation get(Long projectAllocationId) {
		return employeeRepository.findOne(projectAllocationId);
	}

	@Override
	public Collection<ProjectAllocation> get() {
		return employeeRepository.findAll();
	}

	@Override
	public List<ProjectAllocation> get(String projectName) {
		return employeeRepository.findByProjectName(projectName);
	}

	@Override
	public ProjectAllocation create(ProjectAllocation newProjectAllocation) {
		return employeeRepository.save(newProjectAllocation);
	}

	@Override
	public ProjectAllocation update(ProjectAllocation existingProjectAllocation) {
		return employeeRepository.save(existingProjectAllocation);
	}

	@Override
	public Boolean delete(Long projectAllocationId) {
		try {
			System.out.println("Hello forom");
			employeeRepository.customdelete(projectAllocationId);
			
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
			
	}
	}

//	@Override
//	public Employee get(Long employeeId) {
//		return employeeRepository.findOne(employeeId);
//	}
//
//	@Override
//	public Collection<Employee> get() {
//		return employeeRepository.findAll();
//	}
//
//	@Override
//	public Employee create(Employee newEmployee) {
//		return employeeRepository.save(newEmployee);
//	}
//
//	@Override
//	public Employee update(Employee existingEmploye) {
//		return employeeRepository.save(existingEmploye);
//	}
//
//	@Override
//	public Boolean delete(Long employeeId) {
//		try {
//			employeeRepository.delete(employeeId);
//			return true;
//		} catch(Exception e) {
//			return false;
//		}
//	}
//
//	@Override
//	public Employee get(String firstName) {
//		return employeeRepository.findByFirstName(firstName);
//	}


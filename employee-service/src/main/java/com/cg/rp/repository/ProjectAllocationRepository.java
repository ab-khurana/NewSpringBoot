package com.cg.rp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.rp.model.Employee;
import com.cg.rp.model.ProjectAllocation;

@Repository
@Transactional
public interface ProjectAllocationRepository extends JpaRepository<ProjectAllocation, Long> {

	public List<ProjectAllocation> findByProjectName(String projectName);
	
	@Modifying(clearAutomatically = true)
	@Query(value="update project_allocation  set status='inactive' WHERE project_allocation_id = :id", nativeQuery = true)
	public Integer customdelete(@Param("id") Long id );
	


}

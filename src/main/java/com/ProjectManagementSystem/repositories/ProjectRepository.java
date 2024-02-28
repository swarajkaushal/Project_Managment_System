package com.ProjectManagementSystem.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectManagementSystem.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	
}

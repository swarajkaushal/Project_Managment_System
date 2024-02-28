package com.ProjectManagementSystem.services;

import java.util.List;

import com.ProjectManagementSystem.models.Project;

public interface ProjectServices {
	 Project createProject(Project project);
	 Project getProjectById(Long id);
	 Project updateProject(Long id, Project projectDetails);
	 void deleteProject(Long id);
	 List<Project> getAllProjects();
}

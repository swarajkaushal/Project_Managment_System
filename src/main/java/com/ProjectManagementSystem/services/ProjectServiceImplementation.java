package com.ProjectManagementSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ProjectManagementSystem.Exceptation.ErrorResponse;
import com.ProjectManagementSystem.Exceptation.ResourceNotFoundException;
import com.ProjectManagementSystem.models.Project;
import com.ProjectManagementSystem.repositories.ProjectRepository;

@Service
public class ProjectServiceImplementation implements ProjectServices {
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);

	}

	@Override
	public Project getProjectById(Long id) {

		return projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
	}

	@Override
	public Project updateProject(Long id, Project projectDetails) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

		project.setName(projectDetails.getName());
		project.setDescription(projectDetails.getDescription());
		project.setStartDate(projectDetails.getStartDate());
		project.setEndDate(projectDetails.getEndDate());

		return projectRepository.save(project);
	}

	@Override
	public void deleteProject(Long id) {
		Project project = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
		projectRepository.delete(project);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
}

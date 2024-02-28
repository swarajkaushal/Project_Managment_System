package com.ProjectManagementSystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectManagementSystem.models.Project;
import com.ProjectManagementSystem.services.ProjectServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "ProjectController", description = "To perform operation on projects")
public class ProjectController {

	@Autowired
	private ProjectServices projectServices;

	@Operation(summary = "Post operation on projects", description = "It is used to create the projects ")
	@PostMapping
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
		 Project pro=  projectServices.createProject(project);
		 return new ResponseEntity<>(pro,HttpStatus.CREATED);
	}

	@Operation(summary = "GET operation on projects", description = "It is used to Get All the projects ")
	@GetMapping
	public List<Project> getAllProjects() {
		return projectServices.getAllProjects();
	}

	@Operation(summary = "GET operation on projects", description = "It is used to GET the project by ID ")
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
		Project project = projectServices.getProjectById(projectId);
		return ResponseEntity.ok().body(project);
	}

	@Operation(summary = "PUT operation on projects", description = "It is used to Update the projects by ID ")
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
			@Valid @RequestBody Project projectDetails) {
		Project updatedProject = projectServices.updateProject(projectId, projectDetails);
		return ResponseEntity.ok(updatedProject);
	}

	@Operation(summary = "Delete operation on projects", description = "It is used to Delete the project by ID ")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Long projectId) {
		projectServices.deleteProject(projectId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

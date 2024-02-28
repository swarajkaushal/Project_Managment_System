package com.ProjectManagementSystem.services;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import com.ProjectManagementSystem.models.Project;
import com.ProjectManagementSystem.repositories.ProjectRepository;
public class ProjectServiceTest {
	 @Mock
	    private ProjectRepository projectRepository;

	    @InjectMocks
	    private ProjectServiceImplementation projectService;

	    @Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void createProjectTest() {
	        // Create a sample project
	        Project project = new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7));

	        // Mock the repository behavior
	        when(projectRepository.save(project)).thenReturn(project);

	        // Call the service method
	        Project result = projectService.createProject(project);

	        // Verify the result
	        assertEquals("Project 1", result.getName());
	      
	    }

	    @Test
	    public void getAllProjectsTest() {
	        // Create sample projects
	        List<Project> projects = new ArrayList<>();
	        projects.add(new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7)));
	        projects.add(new Project(2L, "Project 2", "Description 2", LocalDate.now(), LocalDate.now().plusDays(14)));

	        // Mock the repository behavior
	        when(projectRepository.findAll()).thenReturn(projects);
	        // Call the service method
	        List<Project> result = projectService.getAllProjects();

	        // Verify the result
	        assertEquals(2, result.size());
	       
	    }
	    @Test
	    public void getProjectByIdTest() {
	        // Create a sample project
	        Project project = new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7));

	        // Mock the repository behavior
	        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.of(project));

	        // Call the service method
	        Project result = projectService.getProjectById(1L);

	        // Verify the result
	        assertEquals("Project 1", result.getName());
	        
	    }
	    @Test
	    public void updateProjectTest() {
	        // Create a sample project
	        Project project = new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7));

	        // Mock the repository behavior
	        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
	        when(projectRepository.save(project)).thenReturn(project);

	        // Call the service method
	        Project updatedProject = new Project(1L, "Project 1 Updated", "Description 1 Updated", LocalDate.now(), LocalDate.now().plusDays(14));
	        Project result = projectService.updateProject(1L, updatedProject);

	        // Verify the result
	        assertEquals("Project 1 Updated", result.getName());
	        assertEquals("Description 1 Updated", result.getDescription());
	        // Add more assertions as needed
	    }
	    @Test
	    public void deleteProjectTest() {
	        // Mock the repository behavior
	        doNothing().when(projectRepository).deleteById(1L);

	        // Call the service method
	        projectService.deleteProject(1L);

	        // Verify that the repository delete method was called
	        verify(projectRepository,times(1)).deleteById(1L);
	    }
}

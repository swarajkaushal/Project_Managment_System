package com.ProjectManagementSystem.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ProjectManagementSystem.models.Project;
import com.ProjectManagementSystem.services.ProjectServiceImplementation;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {
	 

	 @Autowired
	    private MockMvc mockMvc;

	 @MockBean
	    private ProjectServiceImplementation projectService;

	    @Test
	    public void getAllProjectsTest() throws Exception {
	    	List<Project> projects = new ArrayList<>();
	        projects.add(new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7)));
	        projects.add(new Project(2L, "Project 2", "Description 2", LocalDate.now(), LocalDate.now().plusDays(14)));

	        when(projectService.getAllProjects()).thenReturn(projects);
	    	mockMvc.perform(get("/api/projects")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$[0].id").value(1))
	                .andExpect(jsonPath("$[0].name").value("Project 1"))
	                .andExpect(jsonPath("$[1].id").value(2))
	                .andExpect(jsonPath("$[1].name").value("Project 2"));
	    }
	    
	    
	    @Test
	    public void getProjectByIdTest() throws Exception {
	    	
	    	Project project = new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(7));

	        when(projectService.getProjectById(1L)).thenReturn(project);
	        
	        mockMvc.perform(get("/api/projects/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id").value(1))
	                .andExpect(jsonPath("$.name").value("Project 1"));
	    }

	    @Test
	    public void createProjectTest() throws Exception {
	        String projectJson = "{ \"name\": \"New Project\", \"description\": \"New Description\", \"startDate\": \"2024-03-01\", \"endDate\": \"2024-03-15\" }";
	        when(projectService.createProject(any(Project.class))).thenAnswer(invocation -> {
	            Project project = invocation.getArgument(0);
	            project.setId(1L); // Assuming the service returns the saved project with an ID
	            return project;
	        });
	        mockMvc.perform(post("/api/projects")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(projectJson))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.name").value("New Project"))
	                .andExpect(jsonPath("$.description").value("New Description"))
	                .andExpect(jsonPath("$.id").exists());
	        
	    }

	    @Test
	    public void updateProjectTest() throws Exception {
	        String updatedProjectJson = "{ \"name\": \"Updated Project\", \"description\": \"Updated Description\", \"startDate\": \"2024-03-01\", \"endDate\": \"2024-03-15\" }";
	        
	        when(projectService.updateProject(eq(1L), any(Project.class))).thenAnswer(invocation -> {
	            Project project = invocation.getArgument(1);
	            project.setId(1L); // Assuming the service returns the updated project with the same ID
	            return project;
	        });
	        
	        mockMvc.perform(put("/api/projects/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(updatedProjectJson))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name").value("Updated Project"))
	                .andExpect(jsonPath("$.description").value("Updated Description"))
	                .andExpect(jsonPath("$.id").value(1));
	    }

	    @Test
	    public void deleteProjectTest() throws Exception {
	    	
	    	doNothing().when(projectService).deleteProject(1L);
	    	
	        mockMvc.perform(delete("/api/projects/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
}

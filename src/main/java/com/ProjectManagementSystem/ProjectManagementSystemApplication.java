package com.ProjectManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Project Management System",
				version = "1.0.0",
				description = "Project OPEN Api Documentation"
				),
		servers = @Server(
				url = "http://localhost:8282",
				description = "project OPEN API Url"
				)
		)
public class ProjectManagementSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementSystemApplication.class, args);
	}

}

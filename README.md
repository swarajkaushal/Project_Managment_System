# Project Management System

This is a simple Project Management System RESTful API built using Java 17 and Spring Boot, with an in-memory H2 database for data storage.

## Setup Instructions

### Prerequisites
- Java 17 installed on your system
- Maven for building the project

### Steps to Setup
1. Clone this repository: `git clone https://github.com/swarajkaushal/Project_Managment_System.git`
2. Navigate to the project directory: `cd project-management-system`
3. Run the application 

The application will start running at `http://localhost:8282`.

### Swagger Documentation
Swagger documentation is available at `http://localhost:8282/swagger-ui.html`. 
It provides detailed information about all the available endpoints, request/response structures, and example usage.

## Packages Stricture
com
└── ProjectManagementSystem
├── controller # REST controller classes
└── Date # date formatter class
└── Exceptation class
├── model # Entity classes
├── repository # Spring Data JPA repositories
├── service # Service layer interfaces and implementations
└── ProjectManagementSystemApplication.java # Main Spring Boot application class

## API Usage

### Get All Projects
    GET /api/projects
- Returns a list of all projects.

### Get Project by ID
  GET /api/projects/{id}
- Returns the project with the specified ID.

### Create Project
  POST /api/projects
- Creates a new project.
- Request Body:
  ```json
  {
    "name": "Project Name",
    "description": "Project Description",
    "startDate": "YYYY-MM-DD",
    "endDate": "YYYY-MM-DD"
  }

###  Update Project by ID
PUT /api/projects/{id}

- Updates an existing project with the specified ID.
- Request Body:
  {
  "name": "Updated Project Name",
  "description": "Updated Project Description",
  "startDate": "YYYY-MM-DD",
  "endDate": "YYYY-MM-DD"
  }

 #### Delete Project by ID

  DELETE /api/projects/{id}
- Deletes the project with the specified ID.
  

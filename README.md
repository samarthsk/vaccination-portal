**Repository Overview**

This repository contains the complete source code for the School Vaccination Portal — a full-stack web application built with Spring Boot (backend) and ReactJS (frontend), using MySQL as the database.

**Run Locally (Frontend & Backend)**

**Backend (Spring Boot)**

	1. Clone the repository and open the backend folder in your IDE (e.g., IntelliJ, VS Code).

**Set up MySQL Database:**

	1. Create a database named: vaccination_portal
	2. Update your application.properties:

		spring.datasource.url=jdbc:mysql://localhost:3306/vaccination_portal
		spring.datasource.username={username}
		spring.datasource.password={password}
		springdoc.api-docs.path=/api-docs
	
 3. Run the application:
    
		1. mvn spring-boot:run
		2. Access Swagger API docs at: http://localhost:8080/swagger-ui/index.html

**Frontend (ReactJS)**

	1. Navigate to the frontend directory:
 
		1. cd frontend
		2. Install dependencies: npm install
		4. Start the development server: npm start
		5. Open in browser: http://localhost:3000

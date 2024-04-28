# Spring Boot 3.0 Security with JWT Implementation
This project uses Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* Logout mechanism
* Refresh token

## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/Ressis66/spring-boot-3-jwt-security-main-master`
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "jwt_security" to postgres using docker-compose.yml
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:8080. It uses BEARER TOKEN for authentication and authorisation.
The examples of tokens could be taken from Spring CLI. There ara 4 token type in this application: ADMIN, USER, POSTS, ALBUMS.
ADMIN is for access to all directories. Other are for access to specific ones.

Swagger: http://localhost:8080/swagger-ui/index.html

To test project you can use the following endpoints:
(Bearer tokens are printed on the console)

TASK (need to use TASK or ADMIN tokens)

GET http://localhost:8080/api/v1/tasks/tasks

POST http://localhost:8080/api/v1/tasks/task
{
"id": 1,
"title": "quidem",
"description": "111",
"dueDate": "2019-02-03",
"completed": true
}
GET http://localhost:8080/api/v1/tasks/task/1

PUT http://localhost:8080/api/v1/tasks/task/1

{
"id": 1,
"title": "qudem",
"description": "111",
"dueDate": "2013-02-03",
"completed": true
}

DELETE http://localhost:8080/api/v1/tasks/task/1

Application uses Spring Boot actuator:
Endpoint: http://localhost:8080/actuator

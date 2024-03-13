# Spring Boot 3.0 Security with JWT Implementation
This project redirects requests to  https://jsonplaceholder.typicode.com/ 
using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

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

* Clone the repository: `git clone https://github.com/Ressis66/spring-boot-3-jwt-security-main`
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "jwt_security" to postgres using docker-compose.yml
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:8080. It uses BEARER TOKEN for authentication and authorisation.
The examples of tokens could be taken from Spring CLI. There ara 4 token type in this application: ADMIN, USER, POSTS, ALBUMS.
ADMIN is for access to all directories. Other are for access to specific ones.

To test project you can use the following endpoints:
USER (need to use USER or ADMIN tokens)
GET http://localhost:8080/api/v1/users/users
GET http://localhost:8080/api/v1/users/user/1
POST http://localhost:8080/api/v1/users/newUser
{
"id": 11,
"name": "Mary Poopins",
"username": "r44@mail.ru",
"email": "r44@mail.ru",
"password": null,
"address": {
"street": "Kulas Light",
"suite": "Apt. 55",
"city": "Gwenborough",
"zipcode": "92998-3874",
"geo": {
"lat": "-38.3159",
"lng": "81.1496"
}
},
"phone": "8-770-736-8031 x56442",
"website": "gard.org",
"company": {
"name": "Crona",
"catchPhrase": "Multi-layered client-server neural-net",
"bs": "harness real-time e-markets"
}

}

DELETE http://localhost:8080/api/v1/users/user/11

ALBUM (need to use ALBUM or ADMIN tokens)

GET http://localhost:8080/api/v1/albums/albums

POST http://localhost:8080/api/v1/albums/newAlbum
{
"userId": 1,
"title": "quidem molestiae enim"
}
GET http://localhost:8080/api/v1/albums/album/101

DELETE http://localhost:8080/api/v1/albums/album/101

POST (need to use POST or ADMIN tokens)

GET http://localhost:8080/api/v1/posts/posts

POST http://localhost:8080/api/v1/posts/newPost
{
"userId": 1,
"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
GET http://localhost:8080/api/v1/posts/post/101

DELETE http://localhost:8080/api/v1/posts/post/101

Application uses Spring Boot actuator:
Endpoint: http://localhost:8080/actuator
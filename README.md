# Spring Security Demo

A Spring Boot REST API project demonstrating **authentication** and **authorization** using **Spring Security**, **JWT**, **Spring Data JPA**, and **MySQL**.  
This repository is intended as a learning and reference project for understanding how security is implemented in modern Spring Boot applications.

---

## Features

- RESTful APIs built with Spring Boot
- Authentication using Spring Security
- Stateless authentication using JWT (JSON Web Tokens)
- Role-based authorization
- Database integration using Spring Data JPA
- MySQL as the relational database
- Maven-based dependency management
- Clean and beginner-friendly project structure

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL
- Maven
- Lombok

---

## Project Structure

src/main/java  
└── com.security.demo  
&nbsp;&nbsp;&nbsp;&nbsp;├── config  
&nbsp;&nbsp;&nbsp;&nbsp;├── controller  
&nbsp;&nbsp;&nbsp;&nbsp;├── model 
&nbsp;&nbsp;&nbsp;&nbsp;├── repo  
&nbsp;&nbsp;&nbsp;&nbsp;├── service  
&nbsp;&nbsp;&nbsp;&nbsp;└── DemoApplication.java  

---

## Security Overview

- Spring Security is configured using SecurityFilterChain
- JWT is used for stateless authentication
- Tokens are generated after successful authentication
- Each request is validated using a JWT filter
- Secured endpoints require a valid token

---

## Database Configuration

MySQL is used as the database and configured using Spring Data JPA.

Example application.properties configuration:

spring.datasource.url=jdbc:MySQL://localhost:5432/your_db  
spring.datasource.username=your_username  
spring.datasource.password=your_password  

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  

---

## Running the Application

### Prerequisites

- Java 21 or higher
- Maven
- MySQL running locally

### Steps

1. Clone the repository

git clone https://github.com/a6hijeet/spring-security-demo.git  

2. Navigate to the project directory

cd spring-security-demo  

3. Build the project

mvn clean install  

4. Run the application

mvn spring-boot:run  

The application will start on:

http://localhost:8080

---

## API Testing

You can test the APIs using:

- Postman
- curl
- Any REST client

Typical request flow:

1. Authenticate user and receive JWT token  
2. Send token in request header  

Authorization: Bearer YOUR_JWT_TOKEN  

3. Access secured endpoints

---

## Learning Goals

- Understand Spring Security fundamentals
- Learn JWT-based authentication
- Secure REST APIs
- Work with Spring Data JPA and MySQL
- Build real-world backend security patterns

---

## Notes

- This is a demo and learning project
- Not intended for production use
- Focuses on clarity and fundamentals

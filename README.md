# Dream Case App - Restful API

This project is a Restful Web Service application created using Spring Boot, allowing to manage cases (folders) with CRUD (Create, Read, Update, Delete) functionalities. Each case is represented by the following information:

| Field Name   | Type       | Description              |
|--------------|------------|--------------------------|
| caseId       | BIGINT (PK)| Case ID                  |
| creationDate | DATETIME   | Case creation date       |
| lastUpdateDate| DATETIME  | Case last update date    |
| title        | VARCHAR(255)| Case title              |
| description  | VARCHAR(2056)| Case description        |

## Prerequisites

- Language: Java 11
- Framework: Spring Boot
- Database: H2 Database (Embedded SQL database for testing)
- Pattern: MVC

## Execution Instructions

1. Make sure you have Java 11 installed on your system.
2. Clone this repository to your local machine.
3. Open the project in your favorite IDE (e.g., Eclipse, IntelliJ, etc.).

## Running the Application

You can run the application using the following Maven command:

```bash
mvn spring-boot:run

# ContactsApp

A backend-only **Contact Management REST API** built using **Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

The application provides complete CRUD operations, partial updates using PATCH, contact search, request validation, duplicate email handling, global exception handling, and Swagger/OpenAPI documentation.

---

## 📌 Project Overview

ContactsApp is a RESTful backend application designed to manage contact information.

The application allows users to:

* Create contacts
* Retrieve all contacts
* Retrieve a contact by ID
* Update a complete contact
* Partially update a contact
* Delete contacts
* Search contacts
* Validate incoming requests
* Prevent duplicate email addresses
* Handle application errors consistently
* Test APIs using Swagger/OpenAPI

This project follows a **layered architecture** to maintain separation of concerns and clean code organization.

---

## 🚀 Features

### Contact Management

* Create a new contact
* Get all contacts
* Get contact by ID
* Update complete contact details
* Partially update selected fields
* Delete a contact

### Search

Contacts can be searched using:

* First name
* Last name
* Email
* Phone number

Search is case-insensitive and supports partial matching.

### Validation

Request validation is implemented using Jakarta Bean Validation.

Examples:

* First name is required
* Last name is required
* Email is required
* Email format must be valid
* Phone number is required

### Exception Handling

The application provides centralized exception handling for:

* Contact not found
* Duplicate email
* Validation errors
* Unexpected server errors

### API Documentation

Swagger/OpenAPI is integrated for API documentation and testing.

---

# 🛠️ Technology Stack

| Technology         | Purpose                       |
| ------------------ | ----------------------------- |
| Java 21            | Programming Language          |
| Spring Boot 4.1.0  | Backend Framework             |
| Spring Web         | REST API Development          |
| Spring Data JPA    | Database Access               |
| Hibernate          | ORM                           |
| MySQL              | Relational Database           |
| Maven              | Build & Dependency Management |
| Lombok             | Boilerplate Code Reduction    |
| Jakarta Validation | Request Validation            |
| Swagger / OpenAPI  | API Documentation & Testing   |
| IntelliJ IDEA      | Development Environment       |

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
                    Client
               Postman / Swagger
                       |
                       v
              +------------------+
              |   Controller     |
              +--------+---------+
                       |
                       v
              +------------------+
              |     Service      |
              +--------+---------+
                       |
                       v
              +------------------+
              |    Repository    |
              +--------+---------+
                       |
                       v
              +------------------+
              |  Spring Data JPA |
              +--------+---------+
                       |
                       v
              +------------------+
              |    Hibernate     |
              +--------+---------+
                       |
                       v
              +------------------+
              |      MySQL       |
              +------------------+
```

---

# 📁 Project Structure

```text
contacts-app/
│
├── pom.xml
│
├── README.md
│
└── src/
    │
    ├── main/
    │   │
    │   ├── java/
    │   │   └── com/
    │   │       └── contacts/
    │   │           │
    │   │           ├── ContactsAppApplication.java
    │   │           │
    │   │           ├── config/
    │   │           │   └── SwaggerConfig.java
    │   │           │
    │   │           ├── controller/
    │   │           │   └── ContactController.java
    │   │           │
    │   │           ├── dto/
    │   │           │   ├── request/
    │   │           │   │   ├── ContactRequest.java
    │   │           │   │   └── ContactPatchRequest.java
    │   │           │   │
    │   │           │   └── response/
    │   │           │       └── ContactResponse.java
    │   │           │
    │   │           ├── entity/
    │   │           │   └── Contact.java
    │   │           │
    │   │           ├── exception/
    │   │           │   ├── ContactNotFoundException.java
    │   │           │   ├── DuplicateContactException.java
    │   │           │   ├── ErrorResponse.java
    │   │           │   ├── ValidationErrorResponse.java
    │   │           │   └── GlobalExceptionHandler.java
    │   │           │
    │   │           ├── mapper/
    │   │           │   └── ContactMapper.java
    │   │           │
    │   │           ├── repository/
    │   │           │   └── ContactRepository.java
    │   │           │
    │   │           └── service/
    │   │               ├── ContactService.java
    │   │               └── impl/
    │   │                   └── ContactServiceImpl.java
    │   │
    │   └── resources/
    │       └── application.properties
    │
    └── test/
```

---

# 🗃️ Contact Entity

The `Contact` entity represents the contact table in MySQL.

### Fields

| Field         | Type          | Description                |
| ------------- | ------------- | -------------------------- |
| `id`          | Long          | Primary key                |
| `firstName`   | String        | Contact's first name       |
| `lastName`    | String        | Contact's last name        |
| `email`       | String        | Unique email address       |
| `phoneNumber` | String        | Contact's phone number     |
| `address`     | String        | Contact's address          |
| `createdAt`   | LocalDateTime | Contact creation timestamp |
| `updatedAt`   | LocalDateTime | Last update timestamp      |

---

# 🔗 REST API Endpoints

Base URL:

```text
http://localhost:8080/api/v1/contacts
```

---

## 1. Create Contact

### Request

```http
POST /api/v1/contacts
```

### Request Body

```json
{
  "firstName": "Priyanka",
  "lastName": "Yadav",
  "email": "priyanka@gmail.com",
  "phoneNumber": "9876543210",
  "address": "Mathura, Uttar Pradesh"
}
```

### Response

```json
{
  "id": 1,
  "firstName": "Priyanka",
  "lastName": "Yadav",
  "email": "priyanka@gmail.com",
  "phoneNumber": "9876543210",
  "address": "Mathura, Uttar Pradesh",
  "createdAt": "2026-08-17T10:00:00",
  "updatedAt": "2026-08-17T10:00:00"
}
```

### Status

```text
201 Created
```

---

# 2. Get All Contacts

### Request

```http
GET /api/v1/contacts
```

### Response

```json
[
  {
    "id": 1,
    "firstName": "Priyanka",
    "lastName": "Yadav",
    "email": "priyanka@gmail.com",
    "phoneNumber": "9876543210",
    "address": "Mathura, Uttar Pradesh",
    "createdAt": "2026-08-17T10:00:00",
    "updatedAt": "2026-08-17T10:00:00"
  }
]
```

### Status

```text
200 OK
```

---

# 3. Get Contact By ID

### Request

```http
GET /api/v1/contacts/{id}
```

### Example

```http
GET /api/v1/contacts/1
```

### Status

```text
200 OK
```

---

# 4. Update Contact - PUT

PUT is used when the complete contact information is being updated.

### Request

```http
PUT /api/v1/contacts/{id}
```

### Example

```http
PUT /api/v1/contacts/1
```

### Request Body

```json
{
  "firstName": "Priyanka",
  "lastName": "Yadav",
  "email": "priyanka.yadav@gmail.com",
  "phoneNumber": "9999999999",
  "address": "Agra, Uttar Pradesh"
}
```

### Status

```text
200 OK
```

---

# 5. Partial Update - PATCH

PATCH is used when only selected fields need to be updated.

### Request

```http
PATCH /api/v1/contacts/{id}
```

### Example

```http
PATCH /api/v1/contacts/1
```

### Request Body

Only the field that needs to be changed is required.

```json
{
  "phoneNumber": "9999999999"
}
```

Another example:

```json
{
  "address": "Agra, Uttar Pradesh"
}
```

Multiple fields can also be updated:

```json
{
  "firstName": "Priyanka",
  "phoneNumber": "9999999999"
}
```

Fields that are not included in the PATCH request retain their existing values.

### Status

```text
200 OK
```

---

# 6. Delete Contact

### Request

```http
DELETE /api/v1/contacts/{id}
```

### Example

```http
DELETE /api/v1/contacts/1
```

### Status

```text
204 No Content
```

---

# 7. Search Contacts

Search contacts using a keyword.

### Request

```http
GET /api/v1/contacts/search?keyword={keyword}
```

### Example

```http
GET /api/v1/contacts/search?keyword=priya
```

The search checks:

```text
firstName
lastName
email
phoneNumber
```

Search is:

* Case-insensitive
* Partial-match based

Examples:

```text
priya
PRIYA
gmail
9876
Yadav
```

### Status

```text
200 OK
```

---

# ⚠️ Error Handling

The application uses custom exceptions and a global exception handler.

## Contact Not Found

Example:

```http
GET /api/v1/contacts/999
```

Response:

```json
{
  "status": 404,
  "message": "Contact not found with id: 999",
  "timestamp": "2026-08-17T10:00:00"
}
```

---

# Duplicate Email

Email addresses are unique.

If a contact already exists with:

```text
priyanka@gmail.com
```

and another contact attempts to use the same email:

```text
409 Conflict
```

Example response:

```json
{
  "status": 409,
  "message": "Contact already exists with email: priyanka@gmail.com",
  "timestamp": "2026-08-17T10:00:00"
}
```

---

# Validation

The application validates incoming contact requests.

Example invalid request:

```json
{
  "firstName": "",
  "lastName": "",
  "email": "invalid-email",
  "phoneNumber": ""
}
```

Expected response:

```text
400 Bad Request
```

Validation includes:

* Required first name
* Required last name
* Required email
* Valid email format
* Required phone number

---

# 🛢️ Database Configuration

The application uses MySQL.

Create the database:

```sql
CREATE DATABASE contacts_db;
```

Configure the database in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.application.name=contacts-app

spring.datasource.url=jdbc:mysql://localhost:3306/contacts_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your MySQL password.

---

# ⚙️ Prerequisites

Before running the project, make sure the following are installed:

* Java 21
* Maven
* MySQL
* IntelliJ IDEA or another Java IDE
* Postman or Swagger UI

---

# ▶️ Running the Application

### 1. Clone or download the project

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project in IntelliJ IDEA.

### 3. Configure MySQL

Create:

```sql
CREATE DATABASE contacts_db;
```

Then update `application.properties`.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run:

```text
ContactsAppApplication.java
```

from IntelliJ IDEA.

---

# 🌐 Application URL

The application runs on:

```text
http://localhost:8080
```

API base URL:

```text
http://localhost:8080/api/v1/contacts
```

---

# 📚 Swagger API Documentation

Swagger UI can be accessed at:

```text
http://localhost:8080/swagger-ui.html
```

Swagger provides an interactive interface for:

* Viewing API endpoints
* Viewing request/response models
* Sending API requests
* Testing CRUD operations
* Testing search
* Testing PATCH operations

---

# 🧪 API Testing

The recommended testing sequence is:

```text
1. POST      → Create Contact
2. POST      → Create Second Contact
3. GET       → Get All Contacts
4. GET /id   → Get Contact By ID
5. PUT /id   → Full Update
6. PATCH /id → Partial Update
7. GET Search → Search Contact
8. DELETE /id → Delete Contact
9. GET /id   → Verify Deletion
```

Additional tests:

```text
Duplicate Email
Invalid Email
Missing Required Fields
Non-existing Contact ID
Empty Search Result
```

---

# 🔄 Request Flow

For a create operation:

```text
Client
  ↓
ContactController
  ↓
Validation
  ↓
ContactService
  ↓
Duplicate Email Check
  ↓
ContactMapper
  ↓
Contact Entity
  ↓
ContactRepository
  ↓
Spring Data JPA
  ↓
Hibernate
  ↓
MySQL
```

Response:

```text
MySQL
  ↓
Hibernate
  ↓
Contact Entity
  ↓
ContactMapper
  ↓
ContactResponse
  ↓
Controller
  ↓
JSON Response
```

---

# 🧩 Layer Responsibilities

## Controller

Responsible for:

* Handling HTTP requests
* Mapping URLs to methods
* Receiving request DTOs
* Returning HTTP responses

---

## Service

Responsible for:

* Business logic
* Duplicate email checking
* Contact creation
* Contact update
* Partial update
* Contact deletion
* Search operations

---

## Repository

Responsible for:

* Database access
* CRUD operations
* Search queries

Uses:

```java
JpaRepository<Contact, Long>
```

---

## Entity

Represents the database structure.

```text
Contact.java
      ↓
contacts table
```

---

## DTO

DTOs separate API data from database entities.

### Request DTO

```text
ContactRequest
ContactPatchRequest
```

### Response DTO

```text
ContactResponse
```

---

## Mapper

Responsible for converting:

```text
ContactRequest → Contact
```

and:

```text
Contact → ContactResponse
```

---

## Exception Layer

Responsible for centralized error handling.

Includes:

```text
ContactNotFoundException
DuplicateContactException
GlobalExceptionHandler
ErrorResponse
ValidationErrorResponse
```

---

# 🔐 Data Integrity

The application maintains data integrity using:

* Primary key for contact ID
* Unique constraint for email
* Non-null constraints for required fields
* Request validation
* Duplicate email checks
* Custom exception handling

---

# 🎯 Key Learning Concepts

This project demonstrates practical usage of:

* Spring Boot
* REST API development
* Spring MVC
* Dependency Injection
* Inversion of Control
* Layered Architecture
* DTO Pattern
* Mapper Pattern
* Service Layer
* Repository Pattern
* Spring Data JPA
* Hibernate ORM
* MySQL
* Bean Validation
* Exception Handling
* RESTful HTTP methods
* PUT vs PATCH
* Swagger/OpenAPI
* Maven
* Lombok
---

# 📌 API Summary

| Method | Endpoint                           | Description       |
| ------ | ---------------------------------- | ----------------- |
| POST   | `/api/v1/contacts`                 | Create contact    |
| GET    | `/api/v1/contacts`                 | Get all contacts  |
| GET    | `/api/v1/contacts/{id}`            | Get contact by ID |
| PUT    | `/api/v1/contacts/{id}`            | Full update       |
| PATCH  | `/api/v1/contacts/{id}`            | Partial update    |
| DELETE | `/api/v1/contacts/{id}`            | Delete contact    |
| GET    | `/api/v1/contacts/search?keyword=` | Search contacts   |

---

# 👩‍💻 Author

**Priyanka Yadav**

B.Tech Computer Science Engineering

---

# 📄 License

This project is created for learning, practice, and portfolio purposes.

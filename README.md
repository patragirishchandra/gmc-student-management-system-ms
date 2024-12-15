# GMC Student Management System (gmc-student-management-system-ms)

## Overview
The **GMC Student Management System** is a microservice application developed using Java Spring Boot. It is designed to manage students' information, including registration, result updates, and fetching details. The service demonstrates CRUD operations and integrates Swagger for API documentation.

## Key Features
- **Student Registration**: Register student details along with their marks.
- **Result Revision**: Update the results or marks of students.
- **Fetch Student Results**: Retrieve individual or all students' result details.

## Technologies Used
- **Spring Boot**
- **Spring Web**
- **HikariCP** (for database connection pooling)
- **PostgreSQL** (database)
- **Swagger** (API documentation)

---

## API Endpoints

### Base URL
`https://localhost:8080/gmc/v1`

### Endpoints

#### 1. Register Students
- **URL**: `/studentmanagemet/students`
- **Method**: POST
- **Description**: Registers one or more students and their marks.
- **Headers**:
    - `xt-trace-id` (required): UUID for tracking the request.
- **Request Body**:
  ```json
  {
    "students": [
      {
        "firstName": "Girish",
        "lstName": "Patra",
        "dob": "2023-08-18",
        "section": "A",
        "gender": "M",
        "coreJava": 85,
        "spring": 90,
        "cloudTechnology": 95
      }
    ]
  }
  ```
- **Response**: 201 Created
  ```json
  [
    {
      "studentId": "fad7c05d-656d-4328-bc69-c3eb3ac66930",
      "name": "Girish Patra",
      "totalMark": 270,
      "average": 90,
      "result": "Pass"
    }
  ]
  ```

#### 2. Revise Student Result
- **URL**: `/studentmanagemet/student/{student-id}`
- **Method**: PATCH
- **Description**: Updates marks of an existing student.
- **Headers**:
    - `xt-trace-id` (required): UUID for tracking the request.
- **Path Parameters**:
    - `student-id` (required): ID of the student to update.
- **Request Body**:
  ```json
  {
    "coreJava": 95,
    "spring": 80,
    "cloudTechnology": 85
  }
  ```
- **Response**: 200 OK
  ```json
  {
    "studentId": "fad7c05d-656d-4328-bc69-c3eb3ac66930",
    "name": "Girish Patra",
    "totalMark": 260,
    "average": 86.67,
    "result": "Pass"
  }
  ```

#### 3. Get Student Result
- **URL**: `/studentmanagemet/student/{student-id}`
- **Method**: GET
- **Description**: Fetches the result of a specific student.
- **Headers**:
    - `xt-trace-id` (required): UUID for tracking the request.
- **Path Parameters**:
    - `student-id` (required): ID of the student.
- **Response**: 200 OK
  ```json
  {
    "studentId": "fad7c05d-656d-4328-bc69-c3eb3ac66930",
    "name": "Girish Patra",
    "dob": "2023-08-18",
    "section": "A",
    "gender": "M",
    "coreJava": 95,
    "spring": 80,
    "cloudTechnology": 85,
    "totalMark": 260,
    "average": 86.67,
    "result": "Pass"
  }
  ```

#### 4. Get All Students' Results
- **URL**: `/studentmanagemet/students`
- **Method**: GET
- **Description**: Fetches the results of all registered students.
- **Headers**:
    - `xt-trace-id` (required): UUID for tracking the request.
- **Response**: 200 OK
  ```json
  {
    "totalRecords": 100,
    "currentPage": 1,
    "totalPages": 10,
    "data": [
      {
        "studentId": "fad7c05d-656d-4328-bc69-c3eb3ac66930",
        "name": "Girish Patra",
        "dob": "2023-08-18",
        "section": "A",
        "gender": "M",
        "coreJava": 95,
        "spring": 80,
        "cloudTechnology": 85,
        "totalMark": 260,
        "average": 86.67,
        "result": "Pass"
      }
    ]
  }
  ```

---

## Swagger Documentation
The Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

- **OpenAPI Version**: 3.0.3
- **API Version**: 1.0.11

## Developer Contact
- **Name**: Girish Chandra Patra
- **Email**: [patragirishchandra@gmail.com](mailto:patragirishchandra@gmail.com)

---

## Prerequisites
- Java 1.8
- Maven 3.5.2
- PostgreSQL

## How to Run
1. Clone the repository.
2. Configure `application.yml` with your PostgreSQL credentials.
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   java -jar target/gmc-student-management-system-ms.jar
   ```
5. Access the application at `http://localhost:8080`.

---

## License
This project is licensed under the MIT License.

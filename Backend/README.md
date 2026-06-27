# Veterinarios por Venezuela - Backend API

This repository contains the complete backend infrastructure for the Veterinarios por Venezuela platform. It includes a Java Spring Boot application that exposes a full CRUD REST API, backed by a MySQL database with stored procedures for all data operations.

## Features

- **Layered Architecture**: Follows a clean architecture pattern (Controller, Service, Repository).
- **Database-First Approach**: All business logic is handled by MySQL stored procedures.
- **RESTful API**: Provides full CRUD (Create, Read, Update, Delete) endpoints for all major database tables.
- **Ready for Testing**: Includes a pre-configured Postman collection for immediate API testing.
- **Idempotent SQL Scripts**: Database scripts are designed to be safely re-runnable.
- **Request Tracing**: Includes a correlation ID (`correlation-id`) for end-to-end request tracking in logs.

---

## System Requirements

To run this project, you will need the following software installed:

- **Java Development Kit (JDK)**: Version 17 or higher
- **Apache Maven**: Version 3.8 or higher
- **MySQL Server**: Version 8.x
- **DBeaver**: (Recommended) or any other SQL client
- **Postman**: (Recommended) for API testing

---

## Folder Structure

The repository is organized into the following main components:

```
/
├── Backend/              # Java Spring Boot application source code
├── DB/                   # MySQL database scripts
│   ├── veterinarios_venezuela_db.sql       # Main schema (tables, indexes, seed data)
│   ├── veterinarios_venezuela_sp.sql       # Stored procedures (creation script)
│   └── veterinarios_venezuela_sp_drop.sql  # Stored procedures (drop script)
└── Postman/              # Postman collection for API testing
```

---

## 1. Database Setup

The database logic is contained in three SQL script files. You must run them in the correct order to set up the schema and stored procedures.

### Instructions for DBeaver

1.  **Connect to MySQL**:
    - Open DBeaver and create a new connection to your local MySQL 8 server.
    - Authenticate with your MySQL username and password.

2.  **Create the Database and Tables**:
    - In DBeaver, open the `DB/veterinarios_venezuela_db.sql` file.
    - This script will create the `veterinarios_venezuela` database and all required tables.
    - Execute the script by right-clicking in the editor and selecting **Execute SQL Script**.

3.  **Create the Stored Procedures**:
    - Open the `DB/veterinarios_venezuela_sp.sql` file.
    - This script contains all the `CREATE PROCEDURE` statements for the application's business logic.
    - Execute this script in the same way. The `DROP PROCEDURE IF EXISTS` statements ensure that you can re-run this script safely at any time.

> **Note**: The `DB/veterinarios_venezuela_sp_drop.sql` script is provided for convenience if you need to drop all stored procedures without affecting the tables.

---

## 2. Backend Application Setup

The backend is a standard Maven-based Spring Boot application.

### Step 1: Configure Database Connection

1.  Navigate to the `Backend/src/main/resources/` directory.
2.  Open the `application.properties` file.
3.  Update the `spring.datasource.username` and `spring.datasource.password` properties to match your MySQL server credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/veterinarios_venezuela_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```

### Step 2: Build and Run the Application

1.  Open a terminal or command prompt.
2.  Navigate to the root of the `Backend` folder.
3.  Run the following Maven command to build and start the application:

    ```bash
    mvn spring-boot:run
    ```

4.  The application will start on `http://localhost:8080`.

---

## 3. API Testing with Postman

A Postman collection is included to make testing the API simple and straightforward.

### Step 1: Import the Collection

1.  Open Postman.
2.  Click **File > Import...** and select the `Postman/veterinarios_venezuela_api.postman_collection.json` file.
3.  The "Veterinarios Venezuela API" collection will appear in your workspace.

### Step 2: Use the Collection

1.  The collection uses a `{{base_url}}` variable, which is pre-configured to `http://localhost:8080`.
2.  Expand the collection to see folders organized by resource (e.g., `role`, `user`, `animal`).
3.  Each folder contains pre-built requests for all CRUD operations.
4.  To trace a request, add a header to your request in Postman:
    - **Key**: `correlation-id`
    - **Value**: A unique string (e.g., a UUID, `test-id-123`)

If you do not provide a `correlation-id`, the backend will automatically generate one for you. This ID will appear in all log messages related to the request.

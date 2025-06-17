# Microservice-Based Train Ticket Booking System

This project is a demonstration of a microservice architecture for a train ticket booking system, built using the Spring Boot and Spring Cloud frameworks. It showcases a decoupled, scalable, and resilient system design suitable for modern enterprise applications.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Core Services](#core-services)
- [Key Technical Concepts](#key-technical-concepts)
- [System Requirements](#system-requirements)
- [Setup and Execution](#setup-and-execution)
- [API Endpoints](#api-endpoints)

## Architecture Overview

The system is designed following a distributed architecture model, where each business capability is encapsulated within its own service. Communication and service discovery are managed by dedicated Spring Cloud components, ensuring a robust and cohesive ecosystem.

![Architecture Diagram](https://via.placeholder.com/800x450.png?text=Architecture+Diagram+Here)
*(It is highly recommended to replace this placeholder with an actual diagram of your architecture.)*

---

## Core Services

### 1. **Eureka Server (`eureka-server`)**
- **Technology**: Spring Cloud Netflix Eureka
- **Port**: `8761`
- **Responsibility**: Acts as the service registry. All other microservices register themselves with Eureka, allowing for dynamic discovery of service locations. This eliminates the need for hardcoded URLs in inter-service communication.

### 2. **API Gateway (`api-gateway`)**
- **Technology**: Spring Cloud Gateway
- **Port**: `8800`
- **Responsibility**: Serves as the single entry point for all client requests. It provides request routing to the appropriate downstream service, load balancing, and a centralized point for cross-cutting concerns like security and rate limiting.

### 3. **User Service (`user-service`)**
- **Technology**: Spring Boot, Spring Data JPA, Spring Security, MySQL
- **Port**: `8081`
- **Responsibility**: Manages all user-centric operations, including user registration, JWT-based authentication, profile management, and role-based access control.

### 4. **Station & Schedule Service (`station-schedule-service`)**
- **Technology**: Spring Boot, Spring Data JPA, PostgreSQL
- **Port**: `8082`
- **Responsibility**: Manages master data for train stations and travel schedules. It provides CRUD functionalities and public endpoints for clients to query available routes and schedules.

### 5. **Transaction Service (`transaction-service`)**
- **Technology**: Spring Boot, Spring Data MongoDB, WebClient
- **Port**: `8083`
- **Responsibility**: Orchestrates the ticket booking process. It communicates synchronously with the User Service (to identify the customer) and the Station & Schedule Service (to validate schedule details) via `WebClient`. Its use of MongoDB demonstrates polyglot persistence for handling unstructured transaction data.

---

## Key Technical Concepts

* **Service Discovery**: Implemented using **Netflix Eureka**, allowing services to find and communicate with each other without hardcoded hostnames and ports.
* **Centralized Routing**: **Spring Cloud Gateway** is used to create a unified API layer. Routes are configured in `application.yml` to forward requests to the correct microservice based on URL paths.
* **Inter-Service Communication**: The `transaction-service` utilizes Spring's reactive **`WebClient`** for making REST calls to other services. The `@LoadBalanced` annotation enables client-side load balancing, integrating `WebClient` with Eureka.
* **Decoupled Security**: Authentication is centralized in the `user-service`, which generates **JWT (JSON Web Tokens)** upon successful login. Other services then validate this token for authorization without needing direct access to the user database.
* **Polyglot Persistence**: The architecture demonstrates flexibility in data storage by using different databases for different services based on their needs:
    * `user-service`: **MySQL** (Relational).
    * `station-schedule-service`: **PostgreSQL** (Relational).
    * `transaction-service`: **MongoDB** (NoSQL, Document-oriented).

---

## System Requirements

- Java Development Kit (JDK) 17 or newer
- Apache Maven 3.8+
- Active instances of MySQL, PostgreSQL, and MongoDB.
- Docker & Docker Compose (Recommended for database setup)

## Setup and Execution

1.  **Clone the repository:**
    ```sh
    git clone <your-repository-url>
    cd <repository-name>
    ```

2.  **Configure Databases:**
    Ensure that your database instances are running. Update the connection details (`url`, `username`, `password`) in the `application.yml` file of `user-service`, `station-schedule-service`, and `transaction-service`.

3.  **Build All Modules:**
    From the root directory (`TTS_MicroService`), run the Maven command to build all modules.
    ```sh
    mvn clean install
    ```

4.  **Run the Services:**
    Services must be started in a specific order to ensure proper dependency registration with Eureka. Open a new terminal for each service.

    ```sh
    # 1. Start Eureka Server
    cd eureka-server
    mvn spring-boot:run

    # 2. Start dependent services
    cd ../user-service
    mvn spring-boot:run

    cd ../station-schedule-service
    mvn spring-boot:run

    # 3. Start the orchestrator service
    cd ../transaction-service
    mvn spring-boot:run

    # 4. Start the API Gateway
    cd ../api-gateway
    mvn spring-boot:run
    ```

5.  **Verification:**
    -   Navigate to the Eureka Dashboard at `http://localhost:8761` to see all registered services.
    -   All API calls should now be routed through the API Gateway at `http://localhost:8800`.

## API Endpoints

All requests should be directed to the API Gateway.

-   **Authentication**: `POST /api/auth/register`, `POST /api/auth/login`
-   **Users**: `GET /api/users/profile`, `PUT /api/users/profile`
-   **Schedules**: `GET /api/schedules`, `GET /api/schedules/{id}`
-   **Stations**: `GET /api/stations`, `GET /api/stations/{id}`
-   **Transactions**: `POST /api/transactions`, `GET /api/transactions/my-history`

For detailed request/response formats, please refer to the DTO classes within each service module.

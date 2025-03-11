# Store Management API - JPA & JDBC Study Project

## 📌 Overview
This project is a **RESTful API** developed for **educational purposes**, using **Java Persistence API (JPA) and JDBC** to manage a store’s operations, including product inventory, customer data, and order processing.

## 🚀 Features
- **Product Management:** Create, update, and delete store products.
- **Customer Management:** Register and manage customer data.
- **Order Processing:** Handle purchase orders and maintain order history.
- **Database Integration:** Uses **JPA and JDBC** for efficient data persistence.
- **Secure Authentication:** Implemented with JWT for controlled access.

## 🛠️ Technologies Used
- **Java** (Backend development)
- **Spring Boot** (Framework for building the API)
- **JPA & JDBC** (Database persistence)
- **Maven** (Dependency management)
- **MySQL/PostgreSQL** (Database)
- **JWT** (Authentication for secure access)

## ⚙️ Requirements
Before running the project, ensure you have the following installed:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL/PostgreSQL](https://www.mysql.com/ or https://www.postgresql.org/)

## 🚀 How to Run the Project
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/braga-gustavo/API_Loja_JPA.git
cd API_Loja_JPA
```

### 2️⃣ Configure the Database
Edit the `application.properties` file inside `src/main/resources/`, setting up your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Build and Run the Project
```bash
mvn clean install
mvn spring-boot:run
```
The server will be running at `http://localhost:8080`.

## 📌 API Endpoints
- **POST /products** → Register a new product.
- **GET /products/{id}** → Retrieve product details.
- **PUT /products/{id}** → Update a product.
- **DELETE /products/{id}** → Delete a product.
- **POST /customers** → Register a new customer.
- **GET /customers/{id}** → Retrieve customer details.
- **POST /orders** → Create a new order.
- **GET /orders/{id}** → Retrieve order details.


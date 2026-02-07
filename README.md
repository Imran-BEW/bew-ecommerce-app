# BEW Global Vision - Medical Supply Ordering Portal

BEW Global Vision Pvt Ltd is a manufacturer and supplier of ophthalmic surgical equipment including intraocular lenses, surgical blades, and drapes.

This web application is a full-stack B2B ordering portal designed for hospitals and doctors to securely browse, add to cart, and place product orders.

---

## Tech Stack

### Frontend
- React (Vite)
- Bootstrap 5
- Axios
- Context API

### Backend
- Spring Boot
- Spring Data JPA
- REST APIs
- MySQL Database
- Hibernate

---

## Core Features

### Product Management
- View all products
- Search & filter products
- Add new products (Admin)
- Update & delete products
- Product image upload support

### Cart System
- Add to cart
- Increase/decrease quantity
- Remove items
- Dynamic total calculation

### Order Management
- Place orders
- Automatic stock update after purchase
- Unique Order ID generation
- View all orders
- Order item breakdown

---

## Target Users

- Hospitals
- Ophthalmologists
- Surgical Centers
- Authorized Dealers

---

## Project Structure

bew-ecommerce-app/
│
├── backend/ # Spring Boot Application
│ ├── src/
│ ├── pom.xml
│
├── frontend/ # React Application
│ ├── src/
│ ├── package.json
│
└── README.md


---


### Backend Setup (Spring Boot)
cd backend


Update application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/bewdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

### Backend runs on:

http://localhost:8080

### Frontend Setup (React)

cd frontend
npm install
npm run dev

### Frontend runs on:

http://localhost:5173

### API Endpoints
Product APIs

GET /api/products

GET /api/product/{id}

POST /api/product

PUT /api/product/{id}

DELETE /api/product/{id}

Order APIs

POST /api/orders/place

GET /api/orders

### Key Concepts Implemented

Layered Architecture (Controller → Service → Repository)

DTO Pattern for clean API responses

Entity Relationships (Order ↔ OrderItem)

Stock validation & update logic

UUID-based Order ID generation

RESTful API Design

### Future Improvements

Authentication & Role-based Access

Payment Gateway Integration

Order Status Tracking

Invoice Generation

Deployment (AWS / Docker)

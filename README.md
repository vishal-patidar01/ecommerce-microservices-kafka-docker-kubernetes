# ecommerce-microservices-kafka-docker-kubernetes

## 📌 Overview

A demo microservices-based application built using Spring Boot, Spring Cloud, Apache Kafka, Docker, and Kubernetes.
This project demonstrates service-to-service communication, API Gateway routing, service discovery,
event-driven architecture, containerization, and Kubernetes deployment.

The goal of this project is **learning and practicing real-world microservices architecture
with professional Git workflow**.

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Cloud
- Apache Kafka
- Docker
- Kubernetes (Minikube)
- MySQL / PostgreSQL

---

## 🏗️ Architecture Overview

The system follows a microservices architecture where each service is independently deployable
and communicates using REST APIs and service discovery.

**Architecture Components:**
- Eureka Server for service discovery
- Spring Cloud API Gateway as a single entry point
- Independent microservices with separate databases
- (Planned) Kafka for event-driven communication

---

## 🧩 Microservices

### 🔹 Discovery Service
- Netflix Eureka Server
- Registers and discovers all microservices

### 🔹 API Gateway
- Central entry point for all client requests
- Routes requests dynamically using Eureka discovery
- Load-balanced routing using `lb://SERVICE-NAME`

### 🔹 Inventory Service
- Manages product inventory
- Exposes REST APIs for product data
- Uses PostgreSQL / MySQL

### 🔹 Order Service
- Handles order creation and management
- Communicates with Inventory Service
- Uses PostgreSQL / MySQL

---

## 🌐 API Endpoints (via API Gateway)

### Inventory Service
- `GET /api/v1/inventory`
- `GET /api/v1/inventory/{id}`

### Order Service
- `POST /api/v1/orders`
- `GET /api/v1/orders/{id}`

---

## ⚙️ Configuration Management

Real configuration files are **not committed** to GitHub for security reasons.

Each service provides an example configuration file:

- `application-example.yml` → committed to GitHub
- `application.yml` → ignored via `.gitignore`

### How to run locally:
1. Copy `application-example.yml`
2. Rename it to `application.yml`
3. Update database credentials and ports

---

## ▶️ How to Run Locally

### Prerequisites
- Java 17
- Maven
- Docker
- Minikube (for Kubernetes)

### Run order
1. Start Discovery Service (Eureka Server)
2. Start Inventory Service
3. Start Order Service
4. Start API Gateway
5. Access APIs via API Gateway

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Gateway)
- Apache Kafka (planned)
- Docker
- Kubernetes (Minikube)
- MySQL / PostgreSQL

---

## 🚀 Roadmap

- [x] Service Discovery (Eureka)
- [x] API Gateway
- [x] Inventory Service
- [x] Order Service
- [ ] Kafka Integration
- [ ] Docker Compose
- [ ] Kubernetes Deployment
- [ ] Centralized Logging & Monitoring

---

## 📚 Learning Focus

- Microservices architecture
- Spring Cloud ecosystem
- Service discovery & API Gateway
- Clean Git branching strategy
- Real-world project structuring

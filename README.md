# 🚀 Ecommerce Microservices Platform 
     **Spring Boot | Kafka | Docker | Kubernetes**


## 📌 Overview

A demo microservices-based application built using Spring Boot, Spring Cloud, Apache Kafka, Docker, and Kubernetes.
This project demonstrates service-to-service communication, API Gateway routing, service discovery,
event-driven architecture, containerization, and Kubernetes deployment.

The goal of this project is **learning and practicing real-world microservices architecture
with professional Git workflow**.

The project focuses on:
- Service Discovery & API Gateway
- Event-driven architecture
- Centralized configuration & logging
- Containerization with Docker
- Kubernetes deployment (Minikube)

![Microservices Architecture](docs/screenshots/Linkedin-microservice.png)

> 🎯 Purpose: **Learning & hands-on practice** of production-grade microservices with a clean Git workflow.

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Cloud**
    - Eureka (Service Discovery)
    - API Gateway
    - Config Server
- **Apache Kafka** (event-driven communication)
- **Docker & Docker Compose**
- **Kubernetes (Minikube)**
- **PostgreSQL / MySQL**
- **ELK Stack** (Elasticsearch, Logstash, Kibana)

---

## 🏗️ Architecture Overview

The system follows a **microservices architecture** where each service is independently deployable
and communicates using REST APIs and service discovery.

**Architecture Components:**
- Eureka Server for service discovery
- Spring Cloud API Gateway as a single entry point
- Independent microservices with separate databases
- ELK Stack for centralized logging & monitoring
- Kafka for event-driven communication

---

## 🐳 Docker & Containerization

This project uses **Docker** to containerize all microservices and infrastructure
components, ensuring consistency across development and deployment environments.

Each microservice runs inside its own isolated Docker container and communicates
with other services over a Docker network using service discovery.

### 🔹 Docker Usage in This Project
- Separate `Dockerfile` for each microservice
- JAR-based Spring Boot containers
- Centralized orchestration using `docker-compose`
- Infrastructure services (PostgreSQL, Zipkin, ELK) run as containers
- Service-to-service communication via container names

### 🧩 Dockerized Components
- API Gateway
- Config Server
- Discovery Service (Eureka)
- Inventory Service
- Order Service
- PostgreSQL Databases
- Zipkin (Distributed Tracing)
- ELK Stack (Elasticsearch, Logstash, Kibana)

### ▶️ Run Project Using Docker

Start all services:
```bash
   docker-compose up -d
```
---
## 📦 Kubernetes Infrastructure (k8s)

This project uses **Kubernetes (Minikube)** to run supporting infrastructure services in a production-like local environment.

All Kubernetes manifests are located inside the `k8s/` directory.

### 📁 Directory Structure
```
k8s/
├── order-db.yml
├── inventory-db.yml
└── zipkin.yml
```

## 🗄️ Databases (PostgreSQL)

Each microservice follows the **Database Per Service** pattern.

### 🟢 Order Database (`order-db.yml`)
- PostgreSQL database for **order-service**
- Deployed using **StatefulSet**
- Uses **PersistentVolumeClaim (PVC)** for data persistence
- Exposed via **Headless Service** for stable DNS

**Service DNS (inside Kubernetes):**

    order-db-0.order-db-service

### 🟢 Inventory Database (`inventory-db.yml`)
- PostgreSQL database for **inventory-service**
- Deployed using **StatefulSet**
- Uses **PersistentVolumeClaim (PVC)** for persistent storage
- Exposed via **Headless Service**

**Service DNS (inside Kubernetes):**

    inventory-db-0.inventory-db-service

---

## 📊 Distributed Tracing

### 🟣 Zipkin (`zipkin.yml`)
- Used for **distributed tracing**
- Helps track request flow across microservices
- Useful for debugging latency and failures in a microservices system

## 🚀 Deploying Infrastructure

From the project root directory:

```bash
cd k8s
kubectl apply -f order-db.yml
kubectl apply -f inventory-db.yml
kubectl apply -f zipkin.yml
```

Verify deployment:

    kubectl get pods 
    kubectl get pvc
---
## 🧩 Microservices

### 🔹 API Gateway
- Central entry point for all client requests
- Routes requests using Eureka discovery
- Implements custom filters (authentication, headers)
- Integrates Resilience4j for fault tolerance

### 🔹 Config Server
- Centralized configuration management using **Spring Cloud Config Server**
- Fetches configuration from a **separate Git repository**
- Provides environment-specific configurations (dev, test, prod)
- Prevents duplication of configuration across microservices
- Improves security by keeping sensitive configs out of the main codebase

Each microservice loads its configuration from the Config Server at startup.

#### Configuration Strategy
- `application-example.yml` → committed to GitHub for reference
- `application.yml` → ignored via `.gitignore` (contains real credentials)


### 🔹 Discovery Service
- Netflix Eureka Server
- Registers and discovers all microservices

📸 Screenshot:
![Eureka Dashboard](docs/screenshots/eureka-dashboard.png)

### 🔹 Inventory Service
- Manages product inventory
- Exposes REST APIs for product data
- Uses PostgreSQL / MySQL

### 🔹 Order Service
- Handles order creation and management
- Communicates with Inventory Service
- Uses PostgreSQL / MySQL
- 
---

## 🌐 API Endpoints (via API Gateway)

### Inventory Service
- `GET /api/v1/inventory`
- `GET /api/v1/inventory/{id}`

### Order Service
- `POST /api/v1/orders`
- `GET /api/v1/orders/{id}`

---

## 📊 Centralized Logging (ELK Stack)

This project implements **centralized logging** using the **ELK Stack** to collect
and visualize logs from all microservices in one place.

### 🔄 Log Flow

**Microservices → Logstash → Elasticsearch → Kibana**

### 🔹 Elasticsearch
- Stores logs in indexed format
- Enables fast searching and filtering

### 🔹 Logstash
- Reads log files generated by each microservice
- Forwards logs to Elasticsearch

### 🔹 Kibana
- Web UI to view and analyze logs
- Displays logs from all services in a single dashboard


📸 Screenshot:
  ![Eureka Dashboard](docs/screenshots/kibana-centralized-logs.png)

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

## 🚀 Roadmap

- [x] Service Discovery (Eureka)
- [X] Config Server
- [x] API Gateway
- [x] Inventory Service
- [x] Order Service
- [ ] Kafka Integration
- [x] Docker Compose
- [ ] Kubernetes Deployment
- [x] Centralized Logging & Monitoring

---

## 📚 Learning Focus

- Microservices architecture
- Spring Cloud ecosystem
- Service discovery & API Gateway
- Clean Git branching strategy
- Real-world project structuring

---

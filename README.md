# 🎟️ Flash Sale & Ticket Booking API - High Concurrency System

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Relational_DB-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-Distributed_Lock-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI%2FCD-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

**Flash Sale & Ticket Booking API** is a production-ready, high-performance REST API designed to handle massive concurrent traffic during flash ticket sales (e.g., concerts, sports events) without over-selling.

This project demonstrates an advanced **Concurrency Management Architecture**. It is fully containerized with **Docker**, utilizes **Redis (Redisson)** for Distributed Locking to prevent race conditions, stores data reliably in **PostgreSQL**, and features an automated **CI/CD pipeline** via GitHub Actions.

---

## 🏗 System Architecture & Workflow

### 🔄 CI/CD Pipeline (Deployment Flow)
Continuous Integration and Deployment are automated using GitHub Actions.

> **💻 Developer Push** 👉  **⚙️ GitHub Actions (Build & Test)** 👉  **🐳 Docker Hub (Image Registry)** 👉 **🚀 Ready for Cloud Deployment**

### ⚙️ Runtime Architecture (The Flash Sale Flow)
How the application safely handles 1000 users trying to buy the last ticket:

1.  **🌍 Client Request:** User requests a ticket purchase via Swagger/Postman.
2.  **🛡️ Security Layer:** JWT Filter validates the user's Bearer token.
3.  **⚡ Redis Distributed Lock (Redisson):** System attempts to acquire a lock for the specific `Event ID`.
    * *If locked:* Other requests wait in a queue or fail gracefully.
    * *If acquired:* The request proceeds to the database.
4.  **🐘 PostgreSQL Transaction:** Checks available ticket count. If > 0, deducts 1, generates a unique Ticket Code, and saves the record.
5.  **🔓 Lock Release:** Redis releases the lock, allowing the next request in line to process.

---

## 🚀 Key Technical Features

This project tackles one of the hardest problems in software engineering: **Race Conditions in Distributed Systems.**

### 1. 🔒 Distributed Locking & Concurrency Control (Redisson)
In a standard application, multiple simultaneous requests can lead to selling the same ticket twice. This project implements a **Distributed Lock Mechanism**:
* **Redisson Integration:** Used Redisson to handle Redis-based locks easily.
* **Safe Operations:** Wrapped the ticket purchase logic inside a `tryLock()` block. It ensures that even if 500 requests hit the server at the exact same millisecond for a single event, they are processed sequentially, guaranteeing 100% data integrity and preventing negative ticket stock.

### 2. 🛡️ Robust Security Authentication (JWT)
* Implemented stateless authentication using **JSON Web Tokens (JWT)**.
* Custom `SecurityFilterChain` securing sensitive endpoints while leaving OpenAPI/Swagger accessible for testing.
* Users must register, obtain a token, and use Bearer Authentication to interact with the ticket system.

### 3. 🤖 Automated CI/CD (GitHub Actions)
* **Zero-Touch Image Building:** Every push to the `main` branch triggers an automated workflow.
* The pipeline sets up JDK 17, builds the Maven project (skipping tests for speed), creates a Docker image, and automatically pushes it to **Docker Hub**.

### 4. 🐳 Full Containerization (Docker Compose)
* The entire infrastructure (Java App, PostgreSQL Database, Redis) is orchestrated using a single `docker-compose.yml` file.
* Ensures the "it works on my machine" problem is eliminated.

---

## 🛠️ Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.x |
| **Database** | PostgreSQL |
| **Concurrency Control** | Redis (Redisson Client) |
| **Security** | Spring Security 6, JWT (JSON Web Tokens) |
| **DevOps** | Docker, Docker Compose, GitHub Actions |
| **Container Registry** | Docker Hub |
| **API Documentation** | OpenAPI 3.0 / Swagger UI |

---

## ⚙️ How to Run Locally

Since the project is fully Dockerized, you can run the entire ecosystem (Application + PostgreSQL + Redis) with a single command. No local Java or Database installation is required!

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/efeerturk7/flash-sale-app.git](https://github.com/efeerturk7/flash-sale-app.git)
    cd flash-sale-app
    ```

2.  **Start the infrastructure with Docker Compose:**
    ```bash
    docker-compose up --build -d
    ```

3.  **Access the Application & Test:**
    * **Swagger UI:** `http://localhost:8082/swagger-ui/index.html`
    * *Note: First use the `/register` endpoint to create a user, copy the JWT token, and click the **Authorize** button at the top of Swagger to interact with the secured endpoints.*

---

### 👨‍💻 Author
**Bahadır Efe ERTÜRK** - Backend Developer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/efeerturk7/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/efeerturk7)
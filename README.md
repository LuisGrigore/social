# Social Network Backend

## A platform centered around images, meant to be simple and straightforward.

### 🚀 Main technologies used:

- **Language:** Java  
- **Framework:** Spring Boot  
- **Communication:** REST / Kafka  
- **Service Discovery:** Eureka  
- **Databases & Storage:** MySQL / MinIO / Redis  
- **Containers:** Docker / Docker Compose  

---

## 🧪 System Diagram:

![Logo](docs/SystemDiagram1.png)

---

## 🧪 Microservices

| Service         | Address                                  | Description                                                                 |
|-----------------|------------------------------------------|-----------------------------------------------------------------------------|
| `discovery`     | `http://localhost:8083/eureka`           | Handles service discovery and load balancing between microservice instances |
| `auth`          | `//AUTH/**`                              | Handles authentication and JWT generation/validation                        |
| `user-details`  | `//USER-DETAILS/**`                      | Manages user metadata                                                       |
| `post-persist`  | `//POST-PERSIST/**`                      | Stores and retrieves post blobs from bucket storage                         |
| `post-details`  | `//POST-DETAILS/**`                      | Manages post metadata                                                       |
| `notification`  | `//NOTIFICATION/**`                      | Sends notifications to users                                                |
| `gateway`       | `http://<IP>:8080/api/v<VERSION>/**`     | Entry point to the platform; also handles authorization                     |

---

## ⚙️ Instalation and execution

### Prerequisits

- Docker & Docker Compose


# Social Network Backend

## A platform centered around images, meant to be simple and straightforward.

### 🚀 Main technologies used:

- Lenguaje: Java
- Framework: Spring Boot
- Comunicación: REST / Kafka
- Discovery: Eureka Discovery.
- Base de datos: MySql / MiniIo / Redis
- Contenedores: Docker / Docker Compose

---

## 🧪 Microservices

| Service          | Address                                | Description                                       |
|------------------|----------------------------------------|---------------------------------------------------|
| `auth`           | `//AUTH/**`                            | Authentication and JWT generation                 |
| `user-details`   | `//USER-DETAILS/**`                    | Handles user metadata                             |
| `post-persist`   | `//POST-PERSIST/**`                    | Persists and retrieves post blobs from the bucket |
| `notification`   | `//NOTIFICATION/**`                    | Sends notifications to users                      |
| `gateway`        | `http://<IP>:8080/api/v<VERSION>/**`   | Entry point to the platform                       |


---

## ⚙️ Instalation and execution

### Prerequisits

- Docker & Docker Compose

### Clonar y levantar servicios

```bash

```


## 📌 Variables de Entorno

Cada servicio necesita su propio archivo `.env`. (Estructura detallada para cada uno en su README correspondiente):

**`.env` de `auth-service`:**

```env

```

---

## 🧪 Tests

```bash
# Ejecutar pruebas unitarias de un servicio
cd services/user-service
npm test
```

---

## 🛠️ Endpoints de Ejemplo

```http
POST /auth/login
GET  /users/:id
POST /orders
```

Documentación completa en Swagger: [http://localhost:8001/docs](http://localhost:8001/docs)

---

## 📡 Comunicación entre Servicios

| Origen          | Destino           | Medio     |
|----------------|-------------------|-----------|
| auth-service   | user-service      | HTTP REST |
| order-service  | notification-svc  | RabbitMQ  |

---

## 📄 Licencia

MIT / Apache 2.0 / GPL-3.0 — según lo que uses.

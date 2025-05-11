# Social Network Backend

## A platform centered around images, meant to be simple and straightforward.

### 🚀 Main technologies used:

- Lenguaje: Java
- Framework: Spring Boot
- Comunicación: REST / Kafka
- Base de datos: MySql / MiniIo / Redis
- Contenedores: Docker / Docker Compose

---

## 🧪 Servicios

| Servicio               | Puerto | Descripción                         |
|------------------------|--------|-------------------------------------|
| `auth-service`         | 8001   | Maneja autenticación y JWT          |
| `user-service`         | 8002   | CRUD de usuarios                    |
| `order-service`        | 8003   | Gestión de pedidos                  |
| `notification-service` | 8004   | Envío de correos y notificaciones   |
| `gateway`              | 8080   | Punto de entrada a la plataforma    |

---

## ⚙️ Instalación y Ejecución

### Requisitos

- Docker & Docker Compose
- Node.js / Python (si es necesario fuera de Docker)

### Clonar y levantar servicios

```bash
git clone https://github.com/usuario/proyecto-backend.git
cd proyecto-backend
docker-compose up --build
```

Acceder vía navegador: [http://localhost:8080](http://localhost:8080)

---

## 📌 Variables de Entorno

Cada servicio tiene su propio archivo `.env`. Algunos ejemplos:

**`.env` de `auth-service`:**

```env
PORT=8001
JWT_SECRET=supersecreto
DB_URL=mongodb://auth-db:27017/auth
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

## 🧹 Mantenimiento

- Logs centralizados: ELK Stack / Grafana + Loki
- Monitoreo: Prometheus + Grafana
- Linter & Format: ESLint / Black

---

## 👥 Contribuciones

1. Crea un fork
2. Crea una rama (`git checkout -b feature/nombre`)
3. Haz commit (`git commit -m 'Agrega nueva feature'`)
4. Push (`git push origin feature/nombre`)
5. Crea un Pull Request

---

## 📄 Licencia

MIT / Apache 2.0 / GPL-3.0 — según lo que uses.

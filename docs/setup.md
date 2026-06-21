## Requirements
### Tech Stack
- Java 21
- PostgreSQL
- Docker
- Docker Compose

#### auth-api
- Spring Boot 3.5.15
- Maven
- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Lombok
- Flyway Migration

#### data-api
- Spring Boot 3.5.15
- Maven
- Spring Web
- Validation
- Lombok

---

#### Clone Repository
```
git clone https://github.com/valeriinikolaichuk/winwin_travel_task.git
```

#### Docker
The project is fully containerized and can be built and started using Docker Compose:
```
docker compose up -d --build
```
➡ [docker-compose.yml](https://github.com/valeriinikolaichuk/winwin_travel_task/blob/main/docker-compose.yml)

---

#### PostgreSQL
The database runs in Docker container.  
- Host: localhost
- Port: 5434
- Database: winwin
- Username: postgres
- Password: postgres

---
The database schema is automatically created by Flyway migrations when the application starts. 
➡ [V1__init.sql](https://github.com/valeriinikolaichuk/winwin_travel_task/blob/main/auth-api/src/main/resources/db/migration/V1__init.sql)  
The database dump is also located in the project root: ➡ [dump.sql](https://github.com/valeriinikolaichuk/winwin_travel_task/blob/main/dump.sql)  
To import the database backup, run:
```
docker exec -i postgres psql -U postgres -d winwin < dump.sql
```
---

#### Local run
Windows:
```
mvnw spring-boot:run
```
Mac/Linux:
```
./mvnw spring-boot:run
```

---

#### Application URL
http://localhost:8085  
http://localhost:8086

---

#### Open Swagger UI
Swagger UI is automatically available after starting the application.

It provides interactive API documentation and allows testing endpoints directly from the browser.

Auth API:
```
http://localhost:8085/swagger-ui/index.html
```
Data API:
```
http://localhost:8086/swagger-ui/index.html
```
➡ [API Testing via Swagger](swagger_testing.md)

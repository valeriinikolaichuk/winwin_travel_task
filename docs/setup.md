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

#### Run Backend
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
http://localhost:8080  
http://localhost:8081

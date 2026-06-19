## Swagger API Documentation

The project integrates Swagger UI via Springdoc OpenAPI for interactive API documentation.

Swagger allows you to:
- View all available REST endpoints
- Inspect request and response models
- Execute API requests directly from the browser
- Test secured endpoints using JWT authentication

### Swagger UI

Auth API:
```
http://localhost:8080/swagger-ui/index.html
```

Data API:
```
http://localhost:8081/swagger-ui/index.html

```

### Authentication

- Register a new user using `/api/auth/register`
- Login via `/api/auth/login`
- Copy the returned JWT token
- Click **Authorize** in Swagger UI
- Enter:

Bearer <your-token>

- Access protected endpoints such as `/api/process`
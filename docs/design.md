### Design Decisions

- **Flyway** is used for database versioning and automatic schema migrations.
- **UUID** is used as the primary key for all entities to ensure global uniqueness and scalability.
- **Processing logs** store 'userId' instead of JPA relationships to keep logging independent from user lifecycle and avoid cascading issues.
- **Email field** is marked as 'unique' and indexed to optimize authentication and enable fast user lookup.
- **JWT (JSON Web Token)** is used for stateless authentication without server-side sessions.
- **Internal service-to-service communication** is secured using a shared 'internal token' passed via HTTP headers.

### Exception Handling

The application currently uses basic exception handling via `RuntimeException`.

Examples:
- `RuntimeException("Invalid credentials")` is thrown for authentication failures.

No global exception handler (`@RestControllerAdvice`) is implemented.

### auth-api

#### Responsibilities:
- User registration
- User authentication (`JWT`)
- Protected `/api/process` endpoint
- Communication with `data-api`
- Storing processing logs in PostgreSQL

Runs on:

```
http://localhost:8080
````

#### Environment Variables
| Variable | Description |
|------------|------------|
| POSTGRES_URL | PostgreSQL JDBC URL |
| POSTGRES_USER | Database user |
| POSTGRES_PASSWORD | Database password |
| JWT_SECRET | JWT signing key |
| DATA_API_URL | data-api URL |
| INTERNAL_TOKEN | Internal service token |

---

### Structure

#### client/

Provides communication between `auth-api` and `data-api`  
➡ [client/](auth-api/client.md)

---

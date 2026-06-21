## data-api

Internal service responsible for transforming text received from `auth-api`.

#### Responsibilities:
- Accept requests only from `auth-api`
- Validate internal service token
- validates internal requests using `X-Internal-Token`
- returns processed text back to `auth-api`

It is NOT a public API and is only accessible from `auth-api`.

#### Runs on:

```
http://localhost:8086
```

---

### Endpoint
#### Transform text

`POST /api/transform`

#### Request headers

```
X-Internal-Token: my-internal-token
```

#### Request body

```json
{
  "text": "hello"
}
```

#### Response

```json
{
  "result": "HELLO"
}
```

---

### Business logic

The service performs a simple transformation:
- converts input text to uppercase  
  *(or any simple transformation like reverse/suffix depending on implementation)*

---

### Security

- No `Spring Security` is used
- Access is controlled via a shared internal header:
  `X-Internal-Token`
- If token is invalid or missing → `403 Forbidden`

---

### Environment Variables
| Variable | Description |
|------------|------------|
| INTERNAL_TOKEN | Internal service token |


---

### Docker

Service runs inside docker-compose and is reachable by:

```
http://data-api:8086/api/transform
```

from `auth-api`.

---

### Architecture role

```
auth-api → calls → data-api
            (internal transformation service)
```

---

### Notes

- This service is intentionally simple
- No database required
- No authentication system
- Focus is on internal communication between services

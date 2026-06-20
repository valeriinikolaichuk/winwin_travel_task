### data-api

#### Responsibilities:
- Accept requests only from auth-api
- Validate internal service token
- Transform incoming text
- Return transformed result

Runs on:

```
http://localhost:8081
```

#### Environment Variables
| Variable | Description |
|------------|------------|
| INTERNAL_TOKEN | Internal service token |


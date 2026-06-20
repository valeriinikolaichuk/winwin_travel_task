### DataApiClient

`DataApiClient` is responsible for communication between `auth-api` and `data-api`.

#### Purpose:
- Sends processing requests from `auth-api` to `data-api`
- Adds internal authentication header (`X-Internal-Token`)
- Receives transformed text response
- Hides HTTP communication details from business logic

#### Implementation:
- Uses Spring `RestClient`
- Reads configuration from environment variables
- Communicates with `data-api` via HTTP

#### Configuration:

```yaml
data:
  api:
    url: ${DATA_API_URL:http://localhost:8081}

internal:
  token: ${INTERNAL_TOKEN:my-internal-token}
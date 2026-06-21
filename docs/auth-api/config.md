## Configuration

The application uses dedicated configuration classes to keep infrastructure-related setup separated from business logic.

---

### JpaConfig

Enables JPA auditing support.  

#### Responsibilities:
- Enables automatic entity auditing
- Supports `@CreatedDate`
- Supports `@LastModifiedDate`

#### Used by:
- User
- ProcessingLog

#### Example fields:
- createdAt
- updatedAt

---

### RestClientConfig

Provides a Spring `RestClient` bean.

#### Responsibilities:
- Creates reusable HTTP client instance
- Used for communication with `data-api`
- Centralizes HTTP client configuration

#### Used by:
- DataApiClient

---

### OpenApiConfig

Configures `Swagger` / OpenAPI documentation.

#### Responsibilities:
- API documentation generation
- Swagger UI configuration
- JWT Bearer authentication support in Swagger

#### Features:
- Interactive API testing
- Request/response documentation
- Authorization support via JWT token

#### Swagger URL:

```text
http://localhost:8085/swagger-ui/index.html
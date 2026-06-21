### auth testing

#### Test Configuration
- Spring Boot Test framework
- `Mockito` for mocking dependencies
- `@WebMvcTest` for controller isolation
- `@ExtendWith`(MockitoExtension.class) for unit tests
- Security filters disabled in MVC tests to avoid authentication complexity

The following components are covered by automated tests:

| Module | Tested functionality |
|--------|----------------------|
| AuthService | User registration, login, password validation, JWT generation |
| ProcessService | User lookup, data processing, external service call, processing log creation |
| JwtService | Token generation, validation, and email extraction |
| SecurityConfig | Password encoder configuration |
| OpenApiConfig | OpenAPI and Swagger configuration |
| JwtAuthenticationFilter | JWT token validation from Authorization header and SecurityContext authentication setup |

#### Technologies:
- JUnit 5
- Mockito

#### Running Tests:
cd auth-api
```
mvnw.cmd test
```
To generate coverage report (if configured with `JaCoCo`):
```
mvnw clean test
```

#### Current test coverage:
- Instruction Coverage: 82%
- Classes Covered: 19

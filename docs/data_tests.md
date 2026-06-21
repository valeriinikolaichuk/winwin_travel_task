### data testing

The `data-api` service includes unit and controller tests covering core functionality of the service.

#### Covered components

- **TransformService**
  - text transformation logic (converts input text to uppercase)

- **TransformController**
  - successful transformation request with valid internal token (200 OK)
  - request rejection with invalid internal token (403 Forbidden)

#### Test types

- Unit tests for service layer (TransformService)
- Web layer tests for REST API (TransformController)

#### Running tests

cd data-api
```
mvnw.cmd test
```
To generate coverage report (if configured with `JaCoCo`):
```
mvnw clean test
```

#### Current test coverage:
- Instruction coverage: 89%
- Classes Covered: 5

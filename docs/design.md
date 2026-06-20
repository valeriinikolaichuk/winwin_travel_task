### Design Decisions

- `Flyway` is used for database versioning.
- `UUID` is used as primary key for all entities.
- Processing logs store userId instead of `JPA` relations to keep logging independent from user lifecycle.
- `Email` column is indexed and unique for fast authentication lookups.
- `JWT` is used for stateless authentication.
- Service-to-service communication is protected with an internal token.
## log/

#### Responsibilities
- Storing processing history
- Tracking user requests and generated responses
- Providing data for auditing, monitoring, and analytics
- `ProcessingLog` Entity

Represents a single processing operation performed for a user.

#### Each log entry contains:
- User identifier
- Input text submitted for processing
- Generated output text
- Creation timestamp

#### ProcessingLogRepository
Provides persistence and CRUD operations for processing log records through Spring Data JPA.

#### Database
The `processing_log` table stores all processing records and includes an index on user_id to optimize user-based queries.
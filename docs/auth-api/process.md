## process/

#### Responsibilities
- Handling protected text processing requests from authenticated users
- Calling external `data-api` service for text transformation
- Validating user identity via Spring Security authentication context
- Persisting processing results into the database (`processing_log`)
- Returning transformed data back to the client

---

### ProcessController
Exposes the protected processing endpoint.

#### Endpoint:
`POST /api/process` — processes input text for authenticated users

### Flow:
- Receives `ProcessRequest` containing input text
- Extracts user identity from `Authentication`
- Delegates processing to `ProcessService`
- Returns `ProcessResponse` with transformed result

---

### DTOs

- `ProcessRequest`  
Represents input payload for processing: `text` — raw input string submitted by user

- `ProcessResponse`  
Represents output payload: `result` — transformed text returned from `data-api`

---

### ProcessService
Core business logic of the processing workflow.

#### Responsibilities:
- Retrieves user by email from database
- Calls external `data-api` service via `DataApiClient`
- Sends input text for transformation
- Receives transformed result
- Stores processing record in `processing_log`
- Returns final response to controller

#### Processing Flow:
- Validate user existence by email  
- Call `data-api` /transform with input text  
- Receive transformed result  
- Persist `ProcessingLog`:  
— userId  
— inputText  
— outputText  
— createdAt   
- Return response to client  

---

#### External Integration

`DataApiClient`  
- Communicates with data-api service
- Sends text for transformation
- Requires internal authentication header (`X-Internal-Token`)

---

#### Database Interaction
- Uses `UserRepository` to resolve authenticated user
- Uses `ProcessingLogRepository` to persist processing history

---

#### Security
- Endpoint is protected via `Spring Security`
- User identity is extracted from `JWT`-based Authentication context

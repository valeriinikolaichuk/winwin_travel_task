## user/

#### Responsibilities
- Managing user accounts
- Storing authentication-related data
- Looking up users by `email`
- Tracking account creation, updates, and last login activity
- `User` Entity

Represents a registered user of the system.

#### Each user contains:
- Unique identifier
- Email address
- Hashed password
- Account creation timestamp
- Last update timestamp
- Last login timestamp

Audit fields (`createdAt` and `updatedAt`) are managed automatically through Spring Data JPA Auditing.

#### UserRepository  
Provides persistence and CRUD operations for users through Spring Data JPA.

#### Additional methods:
- `findByEmail(String email)` — retrieves a user by email
- `existsByEmail(String email)` — checks whether an email is already registered

#### Database
The `users` table stores user account information and includes an index on email to optimize authentication and user lookup operations.
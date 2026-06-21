## auth/

The `auth` module is responsible for user registration and authentication using `JWT` tokens.

#### Responsibilities
- Register new users.
- Authenticate existing users.
- Validate user credentials.
- Generate JWT access tokens.
- Update user login information.

### Components

#### AuthController

Exposes authentication endpoints:  

- `POST /api/auth/register`
- `POST /api/auth/login`

### AuthService

Contains authentication business logic:  

- validates credentials
- hashes passwords using BCrypt
- creates users
- generates JWT tokens
- updates last login timestamp

### DTOs

#### RegisterRequest

```
{
  "email": "user@example.com",
  "password": "password"
}
```

#### LoginRequest

```
{
  "email": "user@example.com",
  "password": "password"
}
```

#### LoginResponse

```
{
  "token": "jwt-token"
}
```

### Security ➡ [Security]([docs/security.md](https://github.com/valeriinikolaichuk/winwin_travel_task/blob/main/docs/security.md))
- Passwords are stored as `BCrypt` hashes.
- `JWT` is used for authentication.
- Sensitive data such as passwords and tokens are never logged.

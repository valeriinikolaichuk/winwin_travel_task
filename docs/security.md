## Security

### User Authentication

Authentication is implemented using JWT tokens.  

Passwords are stored using BCrypt hashing.  

Passwords are never stored in plain text.  

---

### Service-to-Service Authentication

Communication between services is protected using:  

`X-Internal-Token`  

Requests without a valid internal token are rejected with:  

`403 Forbidden`

---

### Components

#### JwtService

Responsible for:
- generating `JWT` tokens
- extracting user information from tokens
- validating tokens

#### SecurityConfig

Configures Spring Security:
- registers `PasswordEncoder`
- configures authentication rules
- allows public access to authentication endpoints
- protects secured endpoints
- disables `CSRF` for stateless `API`
- enables `JWT` authentication support

#### JwtAuthenticationFilter
Intercepts every incoming HTTP request and performs JWT-based authentication:
- extracts 'Authorization' header from the request
- validates 'JWT' token using 'JwtService'
- extracts user identity (email) from token
- creates 'Authentication' object
- stores authentication in 'SecurityContext'
- allows request to proceed to controllers
- ensures stateless security (no session-based auth)

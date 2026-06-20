### Security

#### User Authentication

Authentication is implemented using JWT tokens.  

Passwords are stored using BCrypt hashing.  

Passwords are never stored in plain text.  

---

#### Service-to-Service Authentication

Communication between services is protected using:  

`X-Internal-Token`  

Requests without a valid internal token are rejected with:  

`403 Forbidden`
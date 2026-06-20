### Request Flow

1. User registers via:  
`POST /api/auth/register`

2. User logs in via:  
`POST /api/auth/login`  

3. auth-api returns `JWT` token.  

4. User calls: `POST /api/process`  
with: Authorization: `Bearer`  

5. `auth-api`:  
- validates `JWT`
- loads user from database
- sends request to `data-api`

6. `auth-api` includes:  
`X-Internal-Token` header for service-to-service authentication.  

7. `data-api` validates token and processes text.  

8. `auth-api` saves processing log into `PostgreSQL`.  

9. `auth-api` returns transformed result.  

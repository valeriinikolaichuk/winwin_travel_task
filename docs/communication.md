### Communication Between Services

`auth-api` communicates with `data-api` using 
➡ [RestClient](https://github.com/valeriinikolaichuk/winwin_travel_task/blob/main/auth-api/src/main/java/com/winwin/auth_api/client/DataApiClient.java)

Request:  

`POST /api/transform`

Headers:  

`X-Internal-Token`

Body:  

```
{
  "text": "hello"
}
```

```
Response:

{
  "result": "HELLO"
}
```

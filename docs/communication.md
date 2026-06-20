### Communication Between Services

`auth-api` communicates with `data-api` using Spring `RestClient`.

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
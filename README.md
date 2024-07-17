# Data Grid Demo

```shell
curl -X PUT http://localhost:8082/api/cache/1 \
     -H "Content-Type: application/json" \
     -d '{
           "id": "1",
           "routeNumber": "42",
           "startLocation": "Main Street",
           "endLocation": "Broadway"
         }'

curl -X GET "http://localhost:8082/api/cache?key=key1"
curl -X DELETE "http://localhost:8082/api/cache?key=key1"
```
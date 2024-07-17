# Demo Red Hat Data Grid Spring

```shell
curl -X PUT http://localhost:8082/api/cache/1 \
     -H "Content-Type: application/json" \
     -d '{
           "routeId": "1",
           "routeName": "NYC",
           "startLocation": "Main Street",
           "endLocation": "Broadway",
           "operatingHours": "From 8 to 22",
           "busFrequency": 30        
         }'

curl -X GET "http://localhost:8082/api/cache/key1"
curl -X DELETE "http://localhost:8082/api/cache/key1"
```

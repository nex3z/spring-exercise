```
curl -v -X GET http://localhost:8080/static/spring.png --output spring.png
```

```
curl -I -X GET http://localhost:8080/static/spring.png -H "If-Modified-Since: Tue, 12 Mar 2019 13:56:36 GMT"
```

```
curl -v -X GET http://localhost:8080/book/1 -H "Accept: application/json"
```

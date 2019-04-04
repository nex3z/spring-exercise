## books

```bash
curl -X GET 'http://localhost:8080/books'
```

```bash
curl -X GET 'http://localhost:8080/books/1'
```

```bash
curl -X GET 'http://localhost:8080/books?page=0&size=3&sort=price,asc'
```

```bash
curl -X GET 'http://localhost:8080/books/search/findByTitleInOrderById?titles=Dune,God%20Emperor%20of%20Dune'
```

```bash
curl -X POST \
  http://localhost:8080/books \
  -H 'Content-Type: application/json' \
  -d '{
	"title": "The Three-Body Problem",
	"price": 16.99
}'
```

## orders

```bash
curl -X POST \
  http://localhost:8080/orders \
  -H 'Content-Type: application/json' \
  -d '{
	"customer": "Li Lei",
	"state": "INIT",
	"items": ["http://localhost:8080/books/1"]
}'
```

```bash
curl -X POST \
  http://localhost:8080/orders/1/items \
  -H 'Content-Type: application/json' \
  -d '{
	"_links": { "self": { "href": "http://localhost:8080/books/2" } }
}'
```

```bash
curl -X GET 'http://localhost:8080/orders'
```

```bash
curl -X GET 'http://localhost:8080/orders/1/items'
```
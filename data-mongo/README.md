## 1. Pull docker image

```bash
sudo docker pull mongo
```

## 2. Run docker container

```bash
docker run \
    --name mongo \
    -p 27017:27017 \
    -v $PWD/mongo_data:/data/db \
    -e MONGO_INITDB_ROOT_USERNAME=admin \
    -e MONGO_INITDB_ROOT_PASSWORD=secret \
    -d mongo
```

## 3. Login

```bash
docker exec -it CONTAINER_ID bash
```

```bash
mongo -u admin -p secret
```

## 4. Create user

```
> use bookstore;
```

```
db.createUser(
  {
    user: "db_user",
    pwd: "db_secret",
    roles: [
      { role: "readWrite", db: "bookstore" }
    ]
  }
)
```

## 5. Check data

```
> show dbs;
admin        0.000GB
bookstore    0.000GB
config       0.000GB
local        0.000GB
```

```
> use bookstore;
switched to db bookstore
```

```
> show users;
{
	"_id" : "bookstore.db_user",
	"user" : "db_user",
	"db" : "bookstore",
	"roles" : [
		{
			"role" : "readWrite",
			"db" : "bookstore"
		}
	],
	"mechanisms" : [
		"SCRAM-SHA-1",
		"SCRAM-SHA-256"
	]
}
```

```
> show collections;
book
```

```
> db.book.count()
1
```

```
> db.book.find();
{ "_id" : ObjectId("5c83368d00f29217e45c41c4"), "title" : "The Three-Body Problem", "price" : { "money" : { "currency" : { "code" : "CNY", "numericCode" : 156, "decimalPlaces" : 2 }, "amount" : "20.00" } }, "createTime" : ISODate("2019-03-09T03:44:13.304Z"), "updateTime" : ISODate("2019-03-09T03:44:14.429Z"), "_class" : "com.nex3z.examples.spring.data.mongo.model.Book" }
```

```
> db.book.remove({"title": "The Three-Body Problem"})
WriteResult({ "nRemoved" : 1 }
```

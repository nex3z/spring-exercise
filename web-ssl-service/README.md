## Build key store
```bash
keytool -genkey -alias mykeystore -storetype PKCS12 -keyalg RSA -keysize 2038 -keystore mykeystore.p12 -validity 265
```

## Query

```bash
curl -k -v -X GET \
  https://127.0.0.1:8443/book/ \
  -H 'Accept: application/json'
```

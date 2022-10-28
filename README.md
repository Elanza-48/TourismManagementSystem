# Tourism Management System.


This is an educational project that replicates tourism management system.
This is a stand-alone monolithic web app that implements server side REST api provider.

This monolithic system will be decomposed into several microservices in near future.

The Documentation is not ready and the ETA is not decided.

This project uses ```Java Lombok``` for boilerplate generator.

Use appropriate ```Lombok Plugin``` for IDE used accordingly.

## Profiles

### Production [prod]

For production profile, TLS certificate and trust store of `pkcs12` type  must be generated
and the path must be stated by setting the environment variable `SERVER_SSL_KEY-STORE` 
or by specifying the file names in the production properties file.
The tomcat server supports HTTP2 protocol. You can also enable/disable HTTP2 in the  prod properties file.

Use either __Keytools__ or __Openssl__ to generate certificate keystore/truststore: 

```bash
# --------- Using Openssl ---------- 
# Sample RSA-4096 ssl certificate keystore generation using openssl.
# can provide -days <no of days> argument to set certificate validity.
$ openssl req -x509 -out localhost.crt -keyout localhost.pem \
  -newkey rsa:4096 -nodes -sha256 \
  -subj '/CN=localhost' -extensions EXT -config <( \
   printf "[dn]\nCN=localhost\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:localhost\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth")

# generate PKCS12 keystore/truststore file that contains both our certificate and key.
$ openssl pkcs12 -export -in localhost.crt -inkey localhost.pem -name tms_ssl \
    -passout pass:password -out keystore.p12

# [Optional] convert .crt to readable .pem format
$ openssl x509 -in localhost.crt -out localhost.crt.pem -outform pem 

# --------- Using Keytools ---------- 
# Sample RSA-4096 ssl certificate keystore generation using keytools.
# can provide -validity <no of days> argument to set certificate validity.
$ keytool -genkeypair -alias tms_ssl -keyalg RSA -keysize 4096 \
  -dname "CN=localhost , O=elanza48 , C=IN , S= Karnataka" -keypass password -keystore keystore.p12 \
  -storeType PKCS12 -storepass password
  
# [Optional] extract certificate.
# Use -rfc flag to genrate in readable .pem format 
$ keytool -exportcert -keystore keystore.p12 -storetype PKCS12 \
    -storepass password -alias tms_ssl -file localhost.crt
```
[java-jwt](https://github.com/auth0/java-jwt) library is used to generate and verify JWT tokens using ECDSA P-512 key value pairs.


The ECDSA P-512 key pair has to be generated and the keypair path must be stated by setting an environment variable
`SECURITY_KEYPAIR_EC512_JWT_PATH` or in the production properties file.

Keypair generation steps:

```bash
# Sample ECDSA P-512 Key-value generation using openssl.
$ openssl ecparam -genkey -name secp521r1 -noout -out keypair.pem

# [Optional] generate public key from private keypair. 
$ openssl ec -in keypair.pem -pubout -out public.pem

# [Optional] convert pkcs#1 to pkcs#8
$ openssl pkcs8 -topk8 -inform pem -outform pem -in keypair.pem -out private.pem -nocrypt
```

*sample key-value pairs:* [auth0](https://github.com/auth0/java-jwt/tree/master/lib/src/test/resources)


### Development [dev]

For Development profile, TLS certificate is not required and the ECDSA P-512 key pair is auto-generated using java security API with [bouncycastle](https://www.bouncycastle.org/java.html) module provider.*

#### Swagger OpenAPI-3 urls:
http://host:port/swagger-ui
http://host:port/v3/api-docs

#### For Spring dev-tool auto-restart:
Terminal-1:
```bash
$ ./gradlew build --continuous
```

Terminal-2:
```bash
$ ./gradlew bootRun
```
___
*This project is under [MIT](./LICENSE.md) license.*
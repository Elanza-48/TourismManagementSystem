# A Spring boot Application


This is a project that replicates tourism management system.
This is a server side REST api provider.

This project uses ```Java Lombok``` for boilerplate generator.

Use appropriate ```Lombok Plugin``` for IDE used accordingly.

The tomcat server uses HTTP2 protocol. So SSL certificate and key value pair must be generated 
under `resources/keys` directory and specify the file names in the properties file. 
You can also disable HTTP2 in the properties file.

Use either __Keytools__ or __Openssl__ to generate certificate keystore/truststore:
``` shell
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

The Spring Security uses [java-jwt](https://github.com/auth0/java-jwt) library to generate and verity JWT tokens using ECDSA P-512 key value pairs.
~~Generate them under `resources` directory and add the file names in the properties file.~~
``` shell
# Sample ECDSA P-512 Key-value generation using openssl.
$ openssl ecparam -genkey -name secp521r1 -noout -out private-keypair.pem

# [Optional] generate public key from private keypair. 
$ openssl ec -in private-keypair.pem -pubout -out public.pem

# [Optional] convert pkcs#1 to pkcs#8
$ openssl pkcs8 -topk8 -inform pem -outform pem -in private-keypair.pem -out private.pem -nocrypt
```

***note: Now ECDSA P-512 key pair is auto-generated using java [bouncycastle](https://www.bouncycastle.org/java.html) library.*

*sample key-value pairs:* [auth0](https://github.com/auth0/java-jwt/tree/master/lib/src/test/resources)

__This project is under [MIT](./LICENSE.md) license.__
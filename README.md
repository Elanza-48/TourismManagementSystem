# A Spring boot Application

*under development*

Generate ECDSA P-521 key value pairs under `resources` directory:
```
$ openssl ecparam -genkey -name secp521r1 -out private.pem
$ openssl ec -in private.pem -pubout -out public.pem
```

*sample key-value pairs:* [auth0](https://github.com/auth0/java-jwt/tree/master/lib/src/test/resources)

__This project is under [MIT](./LICENSE.md) license.__
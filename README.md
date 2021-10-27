# A Spring boot Application


This is a project that replicates tourism management system.
This is a server side REST api provider.

This project uses ```Java Lombok``` for boilerplate generator.

Use appropriate ```Lombok Plugin``` for IDE used accordingly.

Generate ECDSA P-521 key value pairs under `resources` directory:
```
$ openssl ecparam -genkey -name secp521r1 -out private.pem
$ openssl ec -in private.pem -pubout -out public.pem
```

*sample key-value pairs:* [auth0](https://github.com/auth0/java-jwt/tree/master/lib/src/test/resources)

__This project is under [MIT](./LICENSE.md) license.__
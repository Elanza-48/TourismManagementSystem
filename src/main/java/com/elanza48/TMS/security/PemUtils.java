package com.elanza48.TMS.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

@Slf4j
public class PemUtils {

    public static KeyPair getECKeyVal(File pemFile) throws IOException {
        if (!pemFile.isFile() || !pemFile.exists()) {
            throw new FileNotFoundException(
                    String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
        }
        return generateECKeyPair(pemFile);
    }

    private static KeyPair generateECKeyPair(File pemFile){
        Security.addProvider(new BouncyCastleProvider());

        try{
            PEMParser pemParser = new PEMParser(new InputStreamReader(new FileInputStream(pemFile)));
            PEMKeyPair pemKeyPair= (PEMKeyPair) pemParser.readObject();

            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            return converter.getKeyPair(pemKeyPair);
        }catch (PEMException e){
            log.error("KEY_PAIR_PARSE: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        }catch (IOException e){
            log.error("KEY_PAIR_READER: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        }
        return null;
    }
}

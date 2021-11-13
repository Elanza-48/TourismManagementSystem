package com.elanza48.TMS.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.*;
import java.security.*;

@Slf4j
public class PemUtils {

    public static AsymmetricCipherKeyPair generateEC512KeyPair(){
        ECNamedCurveParameterSpec ecNamedCurveParameterSpec =
                ECNamedCurveTable.getParameterSpec("secp521r1");
        ECDomainParameters domainParameters = new ECDomainParameters(
                ecNamedCurveParameterSpec.getCurve(),
                ecNamedCurveParameterSpec.getG(),
                ecNamedCurveParameterSpec.getN(),
                ecNamedCurveParameterSpec.getH(),
                ecNamedCurveParameterSpec.getSeed()
        );

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParameters , new SecureRandom()));
        return generator.generateKeyPair();
    }

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

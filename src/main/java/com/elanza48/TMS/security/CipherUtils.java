package com.elanza48.TMS.security;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Utility class to handle Cryptographic keys.
 * Uses {@link org.bouncycastle}
 *
 * @author Elanza-48
 */
@Log4j2
@Service
public class CipherUtils {

    private BouncyCastleProvider bcProvider=null;
    private void addBouncyCastleProvider(){
        if(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
            if(this.bcProvider==null){
                this.bcProvider=new BouncyCastleProvider();
            }
            Security.addProvider(bcProvider);
        }
    }

    /**
     * <p>SHA-256 hash generator.</p>
     *
     * @param message
     * @return {@link String}
     */

    public String hashSHA1(String message){
        this.addBouncyCastleProvider();

        SHA1Digest  digester=new SHA1Digest();
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        digester.update(messageBytes, 0, messageBytes.length);
        byte[] hashedBytes = new byte[20];
        digester.doFinal(hashedBytes, 0);
        return Hex.toHexString(hashedBytes);
    }

    /**
     * Generates new ECDSA P-512 Keypair.
     * @return {@link AsymmetricCipherKeyPair}
     */
    private AsymmetricCipherKeyPair generateEC512KeyPair(){
        this.addBouncyCastleProvider();

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

    private  KeyPair BcToJsKeyPairConverter(AsymmetricCipherKeyPair bcKeyPair, String signatureScheme) throws Exception{
        byte[] pkcs8Encoded = PrivateKeyInfoFactory.createPrivateKeyInfo(bcKeyPair.getPrivate()).getEncoded();
        PKCS8EncodedKeySpec pkcs8KeySpec= new PKCS8EncodedKeySpec(pkcs8Encoded);

        byte[] spkiEncoded = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(bcKeyPair.getPublic()).getEncoded();
        X509EncodedKeySpec spkiKeySpec= new X509EncodedKeySpec(spkiEncoded);

        KeyFactory factory = KeyFactory.getInstance(signatureScheme);
        return new KeyPair(factory.generatePublic(spkiKeySpec), factory.generatePrivate(pkcs8KeySpec));
    }

    public KeyPair getNewEC512KeyPair() throws Exception{
        return BcToJsKeyPairConverter(generateEC512KeyPair(), "EC");
    }

    /**
     * Parse ECDSA P-512 Key from external file in `pem` format.
     *
     * @param pemInputStream
     * @return {@link KeyPair}
     * @throws IOException
     */

    public KeyPair getECKeyPair(InputStream pemInputStream) throws IOException {
        return generateECKeyPairFromFile(pemInputStream);
    }

    private KeyPair generateECKeyPairFromFile(InputStream pemInput){
        this.addBouncyCastleProvider();

        try{
            PEMParser pemParser = new PEMParser(new InputStreamReader(pemInput));
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

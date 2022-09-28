package com.elanza48.TMS.security;


import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPKCS8Generator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Map;

@Service
@Profile("dev")
@Log4j2
public class CipherUtilsDev extends CipherUtilsBase {

    private JcaPKCS8Generator jcaPKCS8Generator = null;

    CipherUtilsDev(){
        super();
    }

    /**
     * Generates new ECDSA P-512 Keypair.
     * @return {@link AsymmetricCipherKeyPair}
     */
    private KeyPair generateEC512KeyPair(){
        KeyPairGenerator generator =null;
        ECGenParameterSpec curve= new ECGenParameterSpec("secp521r1");

        try{
            generator = KeyPairGenerator.getInstance("EC",
                BouncyCastleProvider.PROVIDER_NAME);
            generator.initialize(curve, SecureRandom.getInstanceStrong());
        }catch(NoSuchAlgorithmException e){
            log.error(e.getMessage());
        }catch(NoSuchProviderException e){
            log.error(e.getMessage());
        }catch(InvalidAlgorithmParameterException e){
            log.error(e.getMessage());
        }
        return generator.genKeyPair();
    }

    @Override
    public KeyPair getEC512KeyPair() throws Exception{
       return generateEC512KeyPair();
    }

    /**
     * <div>Converts EC Keypair to PKCS8 PEM format.</div> 
     * @return {@value String}
     */
    public Map<String, String> keyPairToPEM(KeyPair keyPair) throws IOException{

        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
        Map<String, String> pemKeyPairs= new HashMap<>();
          
        try{
            if(jcaPKCS8Generator==null){
                jcaPKCS8Generator = new JcaPKCS8Generator(keyPair.getPrivate(), null);
            }

            jcaPEMWriter.writeObject(keyPair.getPublic());
            jcaPEMWriter.flush();
            pemKeyPairs.put("public", stringWriter.toString());
            stringWriter.getBuffer().setLength(0);

            jcaPEMWriter.writeObject(jcaPKCS8Generator.generate());
            jcaPEMWriter.flush();
            pemKeyPairs.put("private", stringWriter.toString());
            stringWriter.getBuffer().setLength(0);

        }finally{
            jcaPEMWriter.close();
        }
        return pemKeyPairs;
    }
}

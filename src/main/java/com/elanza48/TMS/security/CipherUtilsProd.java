package com.elanza48.TMS.security;

import lombok.extern.log4j.Log4j2;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;

@Log4j2
@Service
@Profile("prod")
public class CipherUtilsProd extends CipherUtilsBase{

    /**
     * Parse ECDSA P-512 Key from external file in `pem` format.
     *
     * @param pemInputStream
     * @return {@link KeyPair}
     * @throws IOException
     */

    private String KeyPairPath;

    @Autowired
    public void setKeyPairPath(@Value("${security.keypair.ec512.jwt.path:null}") String keyPairPath) {
      this.KeyPairPath = keyPairPath;
    }

    @Override
    public KeyPair getEC512KeyPair() throws IOException {
        return generateECKeyPairFromFile(new FileInputStream(new File(KeyPairPath)));
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

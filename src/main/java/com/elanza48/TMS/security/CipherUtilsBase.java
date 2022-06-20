package com.elanza48.TMS.security;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * Utility class to handle Cryptographic keys.
 * Uses {@link org.bouncycastle}
 *
 * @author Elanza-48
 */
abstract class CipherUtilsBase {

    private BouncyCastleProvider bcProvider=null;
    protected final void addBouncyCastleProvider(){
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

    public String hashSHA256(String message){
        this.addBouncyCastleProvider();

        SHA256Digest digester = new SHA256Digest();
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        digester.update(messageBytes, 0, messageBytes.length);
        byte[] hashedBytes = new byte[digester.getDigestSize()];
        digester.doFinal(hashedBytes, 0);
        return Hex.toHexString(hashedBytes);
    }

    public abstract KeyPair getEC512KeyPair() throws Exception;
}

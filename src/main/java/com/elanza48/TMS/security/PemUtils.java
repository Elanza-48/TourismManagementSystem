/**
 * This a partially modified 3rd party code.
 * reference: https://gist.github.com/lbalmaceda/9a0c7890c2965826c04119dcfb1a5469
 */

package com.elanza48.TMS.security;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
@Slf4j
public class PemUtils {

    private static byte[] parsePEMFile(File pemFile) throws IOException {
        if (!pemFile.isFile() || !pemFile.exists()) {
            throw new FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
        }
        PemReader reader = new PemReader(new FileReader(pemFile));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
    }

    private static PublicKey getPublicKey(byte[] keyBytes, String algorithm) {
        PublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            publicKey = kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("PUBLIC_KEY_ALGORITHM: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        } catch (InvalidKeySpecException e) {
            log.error("PUBLIC_KEY_SPECS: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        }

        return publicKey;
    }

    private static PrivateKey getPrivateKey(byte[] keyBytes, String algorithm) {
        PrivateKey privateKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("PRIVATE_KEY_ALGORITHM: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        } catch (InvalidKeySpecException e) {
            log.error("PRIVATE_KEY_SPECS: [status: {}, message: {} ]","error", e.getLocalizedMessage());
        }

        return privateKey;
    }

    public static PublicKey readPublicKeyFromFilePath(String filepath, String algorithm) throws IOException {
        byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
        return PemUtils.getPublicKey(bytes, algorithm);
    }

    public static PublicKey readPublicKeyFromFile(File file, String algorithm) throws IOException {
        byte[] bytes = PemUtils.parsePEMFile(file);
        return PemUtils.getPublicKey(bytes, algorithm);
    }

    public static PrivateKey readPrivateKeyFromFilePath(String filepath, String algorithm) throws IOException {
        byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
        return PemUtils.getPrivateKey(bytes, algorithm);
    }

    public static PrivateKey readPrivateKeyFromFile(File file, String algorithm) throws IOException {
        byte[] bytes = PemUtils.parsePEMFile(file);
        return PemUtils.getPrivateKey(bytes, algorithm);
    }

}

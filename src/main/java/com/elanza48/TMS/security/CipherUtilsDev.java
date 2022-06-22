package com.elanza48.TMS.security;


import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
@Profile("dev")
public class CipherUtilsDev extends CipherUtilsBase {

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

    private KeyPair BcToJsKeyPairConverter(AsymmetricCipherKeyPair bcKeyPair, String signatureScheme) throws Exception{
        byte[] pkcs8Encoded = PrivateKeyInfoFactory.createPrivateKeyInfo(bcKeyPair.getPrivate()).getEncoded();
        PKCS8EncodedKeySpec pkcs8KeySpec= new PKCS8EncodedKeySpec(pkcs8Encoded);

        byte[] spkiEncoded = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(bcKeyPair.getPublic()).getEncoded();
        X509EncodedKeySpec spkiKeySpec= new X509EncodedKeySpec(spkiEncoded);

        KeyFactory factory = KeyFactory.getInstance(signatureScheme);
        return new KeyPair(factory.generatePublic(spkiKeySpec), factory.generatePrivate(pkcs8KeySpec));
    }

    @Override
    public KeyPair getEC512KeyPair() throws Exception{
        return BcToJsKeyPairConverter(generateEC512KeyPair(), "EC");
    }
}

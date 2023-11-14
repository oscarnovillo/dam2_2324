package cliente.asimetrico;

import com.google.common.io.Files;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CertBouncy {

    public static void main(String[] args) throws CertificateException, InvalidKeyException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeySpecException {
        X509Certificate selfSignedX509Certificate = new CertBouncy().generateSelfSignedX509Certificate();

        Files.write(selfSignedX509Certificate.getEncoded(), new File("auto.crt"));
        System.out.println(selfSignedX509Certificate);



        PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(Files.asByteSource(new File("dam1024.privada")).read());
        KeyFactory keyFactoryRSA = KeyFactory.getInstance("RSA");
        PrivateKey clavePrivadaCA = keyFactoryRSA.generatePrivate(clavePrivadaSpec);

        X509Certificate signedX509Certificate = new CertBouncy().generateSelfSignedX509Certificate(clavePrivadaCA);

        Files.write(signedX509Certificate.getEncoded(), new File("caSign.crt"));

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert2 = (X509Certificate)cf.generateCertificate(new FileInputStream("caSign.crt"));

//        X509Certificate certDeDisco =  X509CertUtils.parse(Files.asByteSource(new File("caSign.crt")).read());

        X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(Files.asByteSource(new File("dam1024.publica")).read());
        PublicKey clavePublicaCA = keyFactoryRSA.generatePublic(clavePublicaSpec);

        cert2.verify(clavePublicaCA);
        System.out.println(cert2);


    }

    public X509Certificate generateSelfSignedX509Certificate() throws CertificateException, InvalidKeyException, IllegalStateException,
            NoSuchProviderException, NoSuchAlgorithmException, SignatureException {
        addBouncyCastleAsSecurityProvider();
        // generate a key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(4096, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // build a certificate generator
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        X500Principal dnName = new X500Principal("cn=example");
        X500Principal dnSubject = new X500Principal("cn=example");

        // add some options
        certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        certGen.setSubjectDN(dnSubject);
        certGen.setIssuerDN(dnName); // use the same
        // yesterday
        certGen.setNotBefore(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        // in 2 years
        certGen.setNotAfter(Date.from(LocalDate.now().plus(1, ChronoUnit.YEARS).atStartOfDay().toInstant(ZoneOffset.UTC)));
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
//        certGen.addExtension(X509Extensions.ExtendedKeyUsage, true,
//                new ExtendedKeyUsage(KeyPurposeId.id_kp_timeStamping));

        // finally, sign the certificate with the private key of the same KeyPair
        X509Certificate cert = certGen.generate(keyPair.getPrivate());

        cert.verify(keyPair.getPublic());
        return cert;
    }

    public X509Certificate generateSelfSignedX509Certificate(PrivateKey signCertificateKey) throws CertificateException, InvalidKeyException, IllegalStateException,
            NoSuchProviderException, NoSuchAlgorithmException, SignatureException {
        addBouncyCastleAsSecurityProvider();
        // generate a key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(4096, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // build a certificate generator
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        X500Principal dnName = new X500Principal("cn=example");
        X500Principal dnSubject = new X500Principal("cn=example");

        // add some options
        certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        certGen.setSubjectDN(dnSubject);
        certGen.setIssuerDN(new X509Principal("CN=tuputamadre")); // use the same
        // yesterday
        certGen.setNotBefore(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        // in 2 years
        certGen.setNotAfter(Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
        certGen.setPublicKey(keyPair.getPublic());
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
//        certGen.addExtension(X509Extensions.ExtendedKeyUsage, true,
//                new ExtendedKeyUsage(KeyPurposeId.id_kp_timeStamping));

        // finally, sign the certificate with the private key of the same KeyPair
        X509Certificate cert = certGen.generate(signCertificateKey);


        //cert.verify
        return cert;
    }

    public void addBouncyCastleAsSecurityProvider() {
        System.out.println(Security.addProvider(new BouncyCastleProvider()));
    }
}

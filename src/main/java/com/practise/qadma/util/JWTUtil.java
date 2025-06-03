package com.practise.qadma.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.entity.QadmaUserAuthority;
import com.practise.qadma.auth.entity.QadmaUserAuthorityType;
import com.practise.qadma.auth.payload.QadmaUserAuthorityDTO;
import com.practise.qadma.auth.payload.QadmaUserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.List;


//  See how to generate keystore file and settings at the bottom of class
@Component
public class JWTUtil {

    @Value("${jwt.keystore.path}")
    private Resource keystore;

    @Value("${jwt.keystore.password}")
    private String keystorePassword;

    @Value("${jwt.key.alias}")
    private String keyAlias;

    @Value("${jwt.key.password}")
    private String keyPassword;

    @Value("${jwt.issuer}")
    private String issuer;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream is = keystore.getInputStream()) {
            keyStore.load(is, keystorePassword.toCharArray());
        }

        privateKey = (RSAPrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
        publicKey = (RSAPublicKey) keyStore.getCertificate(keyAlias).getPublicKey();
    }

    public String generateToken(QadmaUserDTO user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withClaim("user-id", user.getId())
                .withArrayClaim("user-roles", user.getAuthorities().stream()
                        .map(auth-> auth.getId() + ":" + auth.getAuthority())
                        .toArray(String[]::new))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.RSA256(publicKey, null))
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public QadmaUserDTO extractUser(String token) {

        List<QadmaUserAuthorityDTO> authorities = JWT.decode(token).getClaim("user-roles").asList(String.class)
                .stream().map(auth -> {
                    QadmaUserAuthorityDTO authority = new QadmaUserAuthorityDTO();
                    String[] authParts = auth.split(":");
                    authority.setId(Integer.parseInt(authParts[0]));
                    authority.setAuthority(QadmaUserAuthorityType.valueOf(authParts[1]));
                    return authority;
                }).toList();

        return new QadmaUserDTO(
                JWT.decode(token).getClaim("user-id").asInt(),
                JWT.decode(token).getSubject(),
                authorities);
    }
}

//      1 - Generate key store file using default Windows KeyTool
//                       These parameters must be changed to your own before running command
//                          -keystore name of the file      example: jwt-keystore.p12
//                          -storepass yourpassword
//                          -keypass yourpassword
//                          -dname
//                                  CN	    Common Name (usually a hostname or person name)
//                                  OU	    Organizational Unit (e.g., team or department)
//                                  O	    Organization (e.g., your company name)
//                                  L	    Locality (e.g., city)	city
//                                  ST	    State or province	state
//                                  C	    Country (2-letter ISO code)	US
//          command:     keytool -genkeypair -alias jwt-key -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore jwt-keystore.p12 -validity 3650 -storepass CHANGE IT -keypass CHANGE IT -dname "CN=jwt, OU=dev, O=qadma, L=regoly, ST=tolna, C=HU"


//      2 - Add settings to the application.properties file
//              jwt.keystore.path=classpath:jwt-keystore.p12
//              jwt.keystore.password=  must match your -storepass password
//              jwt.key.alias=jwt-key
//              jwt.key.password=   must match your -keypass password
//              jwt.issuer=QADMA









package com.sparta_spring.sparta_spring3.security.jwt;

import com.sparta_spring.sparta_spring3.security.UserDetailsImpl;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.*;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)

    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        String token = null;
        Map<String, Object> payloads = new HashMap<>();
        payloads.put(CLAIM_USER_NAME, userDetails.getUsername());
        try {
            token = Jwts.builder()
                    .setIssuer("sparta")
                    .setSubject(CLAIM_USER_NAME)
                    .setClaims(payloads)
                    .signWith(signatureAlgorithm, getSecretKeySpec(DatatypeConverter.parseBase64Binary(JWT_SECRET)))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .compact();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return token;
    }

    public static SecretKeySpec getSecretKeySpec(byte[] secretKeyBytes) {
        return new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    }
}

package com.sparta_spring.sparta_spring3.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;

import static com.sparta_spring.sparta_spring3.security.jwt.JwtTokenUtils.*;

/*
 *  JwtDecoder 토큰을 검증하기 위한 곳
 * 토큰의 시크릿키는  Configure Service/Property File 등의 방법으로 안전하게 관리
 * */
@Component
public class JwtDecoder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String decodeUsername(String token) {
        Claims claimMap = null;
        try {
            claimMap = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET)) // Set Key
                    .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            //Date expiration = claims.get("exp", Date.class);
            //String data = claims.get("data", String.class);
        } catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            System.out.println(e);
        } catch (Exception e) { // 그외 에러났을 경우
            System.out.println(e);
        }
        System.out.println(claimMap.get(CLAIM_USER_NAME).toString());
        return claimMap.get(CLAIM_USER_NAME).toString();
    }
}


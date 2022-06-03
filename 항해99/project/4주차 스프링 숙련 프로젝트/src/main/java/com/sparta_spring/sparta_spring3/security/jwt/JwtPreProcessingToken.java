package com.sparta_spring.sparta_spring3.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/*
 * UsernamePasswordAuthenticationToken 클래스를 상속받은 클래스로 생성되며,
 * principal 속성에 토큰에 대한 정보를 저장하도록 설계했다.
 * credentials 에는 토큰의 길이를 저장하는데 아직 이해는 못함
 * */
public class JwtPreProcessingToken extends UsernamePasswordAuthenticationToken {

    private JwtPreProcessingToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreProcessingToken(String token) {
        this(token, token.length());
    }
}

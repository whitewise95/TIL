package com.sparta_spring.sparta_spring3.security.filter;

import com.sparta_spring.sparta_spring3.security.jwt.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

/**
 * Token 을 내려주는 Filter 가 아닌  client 에서 받아지는 Token 을 서버 사이드에서 검증하는 클레스 SecurityContextHolder 보관소에 해당
 * Token 값의 인증 상태를 보관 하고 필요할때 마다 인증 확인 후 권한 상태 확인 하는 기능
 */

/*
 * AbstractAuthenticationProcessingFilter 를 상속받고
 * WebSecurityConfig 에서 Filter Chain 에 포함시킬 Filter 를 만든다.
 * attemptAuthentication, successfulAuthentication, unsuccessfulAuthentication 세 개의 메서드를 기본적으로 오버라이딩 한다.
 * */
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final HeaderTokenExtractor extractor;

    public JwtAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                         HeaderTokenExtractor extractor) {
        super(requiresAuthenticationRequestMatcher);
        this.extractor = extractor;
    }

    /*
     * HTTP Request가 해당 Filter에 도착하면 attemptAuthentication 메서드가 호출
     * JWT 토큰을 Authentication 객체(token)에 저장한 후 Provider의 authenticate(token) 메서드를 호출
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException {
        /*
         * JWT 값을 담아주는 변수 TokenPayload
         * FormLoginAuthProvider에서 "Authorization: Bearer { Token }" 와 같이 만들지기 때문에
         * 파싱한 후 Authentication 객체 token을 생성한다.
         * */
        String tokenPayload = request.getHeader("Authorization");
        if (tokenPayload == null) {
            response.sendRedirect("/user/loginView");
            return null;
        }

        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(extractor.extract(tokenPayload, request));
        // 이 객체를 Provider의 authenticate() 메서드에 전달함으로써 인증코드를 수행한다.
        return super.getAuthenticationManager().authenticate(jwtToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        /*
         *  SecurityContext 사용자 Token 저장소를 생성합니다.
         *  SecurityContext 에 사용자의 인증된 Token 값을 저장합니다.
         */
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        // SecurityContextHolder에 Authentication 객체를 저장해 해당 스레드에 대해서 인증된 상태라는 것을 명시
        SecurityContextHolder.setContext(context);

        // FilterChain chain 해당 필터가 실행 후 다른 필터도 실행할 수 있도록 연결실켜주는 메서드
        chain.doFilter(request, response);
    }

    /*
     *
     * unsuccessfulAuthentication 메서드에서는 clearContext() 를 호출해 해당 스레드에 대한
     * Authentication 객체를 초기화해 인증되지 않은 스레드임을 명시
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        /*
         *	로그인을 한 상태에서 Token값을 주고받는 상황에서 잘못된 Token값이라면
         *	인증이 성공하지 못한 단계 이기 때문에 잘못된 Token값을 제거합니다.
         *	모든 인증받은 Context 값이 삭제 됩니다.
         */
        SecurityContextHolder.clearContext();

        super.unsuccessfulAuthentication(request, response, failed);
    }
}

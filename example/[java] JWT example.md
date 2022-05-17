# 글을 쓴 이유
- 이번에 지인들과 게임을 만들기로 했다 나는 서버를 맡았다. 로그인 정보를 넘겨주면 DB와 확인 후 토큰을 발급해주고 토큰을 확인해주는 서비스를 만들어야했기에 이번에 기회에 세션만 사용했지만 jwt를 알아보게되었다.

# dependensy  
- [gradle클릭](https://mvnrepository.com)
```
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.18.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
```  

# controller
```
package com.spring.boot.api.controller;

import com.spring.boot.api.service.MemberService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @PostMapping("/requestJwt")
    public String createJwt(@RequestParam("id") String id) {
        return memberService.createJwt(id);
    }

    @PostMapping("responseJwt")
    public String getJwt(@RequestParam("jwt")String jwt) {
        return memberService.getJwt(jwt);
    }

}
```  

# Service
- createJwt()
> setSubject  = 어떤변수를 토큰으로 만들것인지 변수값을 넘겨주면된다.  
> signWith  =  해싱 알고리즘과 시크릿 키를 설정할 수 있습니다.  
> setExpiration = 만료시간을 설정  
- getSecretKeySpec()
> 시크릿키를 byte로 변환한 값을 주어 객체를 받아온다.
- getJwt()
> parseClaimsJws = 토큰을 세팅  
> getSubject = decoding된 토큰의 subject를 볼 수 있다.  여기서는 id를 볼 수 있는것이다.
```
package com.spring.boot.api.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.Date;

@Service
public class MemberService {

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public String createJwt(String id) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(signatureAlgorithm, getSecretKeySpec(DatatypeConverter.parseBase64Binary("secretKey")))
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 1000 * 60))
                .compact();
    }

    public String getJwt(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("secretKey"))
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("parseError : " + e.getMessage());
        }
    }

    public SecretKeySpec getSecretKeySpec(byte[] secretKeyBytes) {
        return new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    }
}
```

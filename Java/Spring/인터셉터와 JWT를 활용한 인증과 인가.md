목차
1) [로그인-기능](#로그인-기능)
2) [토큰 흐름](#토큰-흐름)
3) [HandlerInterceptor 코드](#handlerinterceptor)
4) [HandlerInterceptor 제외](handlerinterceptor-제외)
5) [전체 코드](#전체)

<br>
<br>

# 로그인 기능
1. 로그인을 요청하면 회원의 아이디와 비밀번호를 동일한지 확인하다.
```java
    @Transactional(readOnly = true)
    public TokenResponseDto login(UserRequestDto userRequestDto) {
        Users user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("회원가입되지 않은 아이디입니다."));

        if (!bCryptPasswordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        return createTokens(user.getUsername());
    }
```
2. createTokens()에서 발급받은 엑세스토큰과 리플레쉬토큰을 가공한다.
```java
    public TokenResponseDto createTokens(String username) {
        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(username),
                jwtTokenProvider.createRefreshToken(username)
        );
    }
```

3. createAccessToken()
```java
    public String createAccessToken(String username) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put(CLAIMS_KEY, username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + MINUTE * 30)) // 30분
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 사용할 암호화 알고리즘과
                .compact();
    }
```
4.createRefreshToken()
```java
    public String createRefreshToken(String username) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put(CLAIMS_KEY, username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + MINUTE * 60)) // 60분
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 사용할 암호화 알고리즘과
                .compact();
    }
```



<br>
<br>



# 토큰 흐름  
```
1. HandlerInterceptor를 implements한 클래스에서 요청한 API에 접근하기전 토큰을 인터셉터한다.

2. 토큰을 Decode 하여 HttpServletResponse.setAttribute() 한다.

3. 요청 API에서 HttpServletResponse.getAttribute()를 사용해 토큰에 저장된 유저아이디를 얻을 수 있다.
``` 
![Request (1)](https://user-images.githubusercontent.com/81284265/176068513-33e3c2fc-09c5-43f2-bdbe-0c2e44ed429d.png)


<br>
<br>


# HandlerInterceptor
```java
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    public final static String JWT_HEADER_KEY = "Authorization";
    public final static String BEARER = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoInJwt userInfoInJwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String authorization = request.getHeader(JWT_HEADER_KEY);                      //클라이언트 요청 헤더에 토큰을 받는다.
        String accessToken = authorization.substring(7);                               // Bearer 를 제거한다.

        if (authorization == null) {                                                    // 헤더에 토큰이 없는 경우
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }
 
        if (!authorization.startsWith(BEARER)) {                                         // Bearer 가 토큰에 없는 경우
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }

        if (!jwtTokenProvider.isValidAccessToken(accessToken)) {                         //토큰이 유효하지 않을경우
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }

        request.setAttribute(JWT_HEADER_KEY, userInfoInJwt.getEmail_InJWT(authorization));
        return true;
    }
}
```
- 유효성 검사
```java
    public boolean isValidAccessToken(String token) {
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access email: " + accessClaims.get(CLAIMS_KEY));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired email : " + exception.getClaims().get(CLAIMS_KEY));
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        }
    }
```



<br>
<br>

# HandlerInterceptor 제외 
- excludePathPatterns() 메소드는 인터셉터에 제외시킬 요청 uri를 설정한다.
```java

    private final String[] JWT_INTERCEPTOR_URI = {
        "/user/signup",
        "/user/emailDupCheck/**",
        "/user/nameDupCheck/**",
        "/user/login"
    };


    //토큰을 받아야 하는 서비스 설정
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(jwtTokenInterceptor) //로그인이 필요한 서비스 요청시 Interceptor가 그 요청을 가로챔
                .excludePathPatterns(JWT_INTERCEPTOR_URI);
    }

```

<br>
<br>


# 전체코드
- JwtTokenInterceptor
```java
@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    public final static String JWT_HEADER_KEY = "Authorization";
    public final static String BEARER = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoInJwt userInfoInJwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String authorization = request.getHeader(JWT_HEADER_KEY);
        String accessToken = authorization.substring(7);

        if (authorization == null) {
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }

        if (!authorization.startsWith(BEARER)) {
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }

        if (!jwtTokenProvider.isValidAccessToken(accessToken)) {
            throw new RuntimeException("유효하지 않은 않은 토큰입니다.");
        }

        request.setAttribute(JWT_HEADER_KEY, userInfoInJwt.getEmail_InJWT(authorization));
        return true;
    }
}
```
- JwtTokenProvider
```java
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String SECRET_KEY = "sec";
    public final static String CLAIMS_KEY = "username";

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    @PostConstruct
    protected void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createAccessToken(String username) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put(CLAIMS_KEY, username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + MINUTE * 30)) // 30분
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 사용할 암호화 알고리즘과
                .compact();
    }

    public String createRefreshToken(String username) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put(CLAIMS_KEY, username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + MINUTE * 60)) // 60분
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 사용할 암호화 알고리즘과
                .compact();
    }

    //AccessToken 유효성 검사
    public boolean isValidAccessToken(String token) {
        try {
            Claims accessClaims = getClaimsFormToken(token);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access email: " + accessClaims.get(CLAIMS_KEY));
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired email : " + exception.getClaims().get(CLAIMS_KEY));
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        }
    }

    //JWT 구문분석 함수
    public Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
}
```
- UserInfoInJwt
```java
@RequiredArgsConstructor
@Component
public class UserInfoInJwt {

    private final JwtTokenProvider jwtTokenProvider;

    public String getEmail_InJWT(String authorization) {
        String accessToken = authorization.substring(7);
        Claims accessClaims = jwtTokenProvider.getClaimsFormToken(accessToken);
        return (String) accessClaims.get(CLAIMS_KEY);
    }
}

```
- webConfig
```java
@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final String[] JWT_INTERCEPTOR_URI = {
        "/user/signup",
        "/user/emailDupCheck/**",
        "/user/nameDupCheck/**",
        "/user/login"
    };

    private final JwtTokenInterceptor jwtTokenInterceptor;


    //토큰을 받아야 하는 서비스 설정
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(jwtTokenInterceptor) //로그인이 필요한 서비스 요청시 Interceptor가 그 요청을 가로챔
                .excludePathPatterns(JWT_INTERCEPTOR_URI);
    }
}

```










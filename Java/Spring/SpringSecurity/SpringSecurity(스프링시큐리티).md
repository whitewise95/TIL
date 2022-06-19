# 목차
1. [들어가는 글](#들어가는-글)
2. [스프링 시큐리티란?](#스프링-시큐리티란)
3. [WebSecurityConfigurerAdapter](#websecurityconfigureradapter)
4. [인증 아키텍쳐](#인증-아키텍쳐)
5. [시큐리티의 주요 filter](#시큐리티의-주요-filter)
6. [마치는 글](#마치는-글)

<br> 
<br> 
<br> 

# #들어가는 글
스프링 시큐리티는 책을 보고 한번 따라서 해보고 어렵지 않다라고 생각하고 넘어갔지만 ... 이번 항해99 프로젝트에서 쓰게되었는데 모든 로직이 스프링 시큐리티에 맞춰있지 않은 상태에서, 시큐리티를 넣었더니 생각보다 어려운 프레임워크라는 걸 알게 되었다. 그래서 스프링 시큐리티에서 공부하게 되었고 정말 어려운 프레임워크라는 걸 인지했다.  
<br>  
생각보다 시큐리티 공식 문서가 번역기로 돌려서 보면 볼만하다.  
[시큐리티 공식 문서](https://spring.io/projects/spring-security)
<br> 
#### ***결론은 아직 정확하게 이해하지 못했지만 내가 이해한 부분을 최대한 간략하게 정리하고 싶었다.***
<br>  
<br> 


# #스프링 시큐리티란?
📍 [인증과 인가](https://github.com/whitewise95/TIL/blob/main/Security/%EC%9D%B8%EC%A6%9D%EA%B3%BC%20%EC%9D%B8%EA%B0%80.md)를 제공해주는 스프링 하위 프레임워크이다.  
 >    서블릿 필터와 이들로 구성된 필터체인으로의 위임모델을 사용한다.   
 >    보안과 관련해서 체계적으로 많은 옵션을 제공해주기 때문에 개발자 입장에서는 일일이 보안관련 로직을 작성하지 않아도 된다.


![화면 캡처 2022-06-04 112723](https://user-images.githubusercontent.com/81284265/171975425-23fa7079-9de6-42b2-b1ae-1517286c6be4.png)

# #WebSecurityConfigurerAdapter
```java
@EnableWebSecurity  //스프링시큐리티 사용을 위한 어노테이션선언

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
//WebSecurityConfigurerAdapter 상속
    
    private AuthenticationProvider authenticationProvider;
    
    public SpringSecurityConfig(/*UserDetailsService userDetailsService, 
                                PasswordEncoder passwordEncoder,*/
                                AuthenticationProvider authenticationProvider) {

        this.authenticationProvider = authenticationProvider;
    }
    
    /*
     * 스프링 시큐리티가 사용자를 인증하는 방법이 담긴 객체.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * AuthenticationProvider 구현체
         */
        auth.authenticationProvider(authenticationProvider);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    
    /*
     * 스프링 시큐리티 룰을 무시하게 하는 Url 규칙(여기 등록하면 규칙 적용하지 않음)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/css/**")
            .antMatchers("/vendor/**")
            .antMatchers("/js/**")
            .antMatchers("/favicon*/**")
            .antMatchers("/img/**")
        ;
    }
    
    /*
     * 스프링 시큐리티 규칙
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//보호된 리소스 URI에 접근할 수 있는 권한을 설정
            .antMatchers("/login*/**").permitAll() //전체 접근 허용
            .antMatchers("/logout/**").permitAll()
            .antMatchers("/myPage").hasRole("ADMIN")//admin이라는 롤을 가진 사용자만 접근 허용
            .antMatchers("/chatbot/**").permitAll()
            .anyRequest().authenticated()
        .and().logout()
              .logoutUrl("/logout")
              .logoutSuccessHandler(logoutSuccessHandler())
        .and().csrf()//csrf 보안 설정을 비활성화
              .disable()//해당 기능을 사용하기 위해서는 프론트단에서 csrf토큰값 보내줘야함
        .addFilter(jwtAuthenticationFilter())//Form Login에 사용되는 custom AuthenticationFilter 구현체를 등록
        .addFilter(jwtAuthorizationFilter())//Header 인증에 사용되는 BasicAuthenticationFilter 구현체를 등록
        .exceptionHandling()
              .accessDeniedHandler(accessDeniedHandler())
              .authenticationEntryPoint(authenticationEntryPoint())
        ;
    }
    
    /*
     * SuccessHandler bean register
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/index");
        return successHandler;
    }
    
    /*
     * FailureHandler bean register
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/loginPage?error=error");
        return failureHandler;
    }
    
    /*
     * LogoutSuccessHandler bean register
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        CustomLogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/loginPage?logout=logout");
        return logoutSuccessHandler;
    }
    
    /*
     * AccessDeniedHandler bean register
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/error/403");
        return accessDeniedHandler;
    }
    
    /*
     * AuthenticationEntryPoint bean register
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint("/loginPage?error=e");
    }
    
    /*
     * Form Login시 걸리는 Filter bean register
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        jwtAuthenticationFilter.setUsernameParameter("username");
        jwtAuthenticationFilter.setPasswordParameter("password");
        
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        
        jwtAuthenticationFilter.afterPropertiesSet();
        
        return jwtAuthenticationFilter;
    }
    
    /*
     * Filter bean register
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager());
        return jwtAuthorizationFilter;
    }
    @Bean
	public PasswordEncoder passwordEncoder() {//간단하게 비밀번호 암호화
		return new BCryptPasswordEncoder(); 
	}
}
```

###  antMatchers()  
```
📌 다음은로 지정할 수 있는 항목들입니다.  
```
### hasRole() or hasAnyRole()
```
📌특정 권한을 가지는 사용자만 접근할 수 있습니다.  
```
### hasAuthority() or hasAnyAuthority()
```
📌특정 권한을 가지는 사용자만 접근할 수 있습니다.  
```
hasIpAddress()
```
📌특정 아이피 주소를 가지는 사용자만 접근할 수 있습니다.  
```
permitAll() or denyAll()
```
📌접근을 전부 허용하거나 제한합니다.  
```
rememberMe()
```
📌리멤버 기능을 통해 로그인한 사용자만 접근할 수 있습니다.  
```
anonymous()
```
📌인증되지 않은 사용자가 접근할 수 있습니다.  
```
authenticated()
```
📌인증된 사용자만 접근할 수 있습니다.  
```

> Role은 역할이고 Authority는 권한이지만 사실은 표현의 차이입니다. Role은 “ADMIN”으로 표현하고 Authority는 “ROLE_ADMIN”으로 표기합니다.  


<br> 
<br> 
<br> 


# #인증 아키텍쳐  
### 📍 Spring Security Authentication Architecture  

`스프링 시큐리티를 이용`하게 되면 모든 요청은 `Session을 발급` 받는다. (시큐리티는 세션과 쿠키를 이용한 기술)    
Session을 발급받으면 `클라이언트 쿠키에 JSESSIONID라는 키로 SessionId가 저장`된다.  
아래 설명하게 될 `AuthenticationFiter는 해당 요청의 JSESSIONID를 확인`하여 매핑되는 인증 정보가    
SecurityContext에 있는지 판단 후 `없으면 LOGIN페이지로 이동`시킨다.  
![인증 아키텍쳐](https://user-images.githubusercontent.com/81284265/171974679-bae65236-63d7-4f65-bc4f-77f61e566c7f.png)   



1. 사용자가 ***Form을 통해 로그인 정보*** 를 입력하고 인증 요청을 보낸다.  
```javascript
 $.ajax({
            type: "POST",
            url: `/user/login`,
            contentType: "application/json",
            data: JSON.stringify({
                username: "admin",
                password: "testPassword"
            }),
            success: function (response, textStatus, request) {
                alert("성공")
            }
        })
```

2. AuthenticationFilter(사용할 구현체 UsernamePasswordAuthenticationFilter)가 HttpServletRequest에서 사용자가 보낸 아이디와 패스워드를 인터셉트한다. 패스워드를 진짜 인증을 담당할 AuthenticationManager 인터페이스(구현체 - ProviderManager)에게 인증용 객체(UsernamePasswordAuthenticationToken)로 만들어줘서 위임한다.  

```java
/*
 * UsernamePasswordAuthenticationFilter는 사용자가 보낸 아이디와 비밀번호를 인터셉트한다.
 * */
public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
    final private ObjectMapper objectMapper;

    public FormLoginFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /*
     *
     * UsernamePasswordAuthenticationToken 객체는 클라이언트에서 가져온 정보를 진짜 인증을 담당할
     * AuthenticationManager(ProviderManager) 인터페이스에게
     * 인증용 객체(UsernamePasswordAuthenticationToken)로 만들어 주는 로직이다.
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest;
        try {
            JsonNode requestBody = objectMapper.readTree(request.getInputStream());
            String username = requestBody.get("username").asText();
            String password = requestBody.get("password").asText();
            authRequest = new UsernamePasswordAuthenticationToken(username, password);
        } catch (Exception e) {
            throw new RuntimeException("username, password 입력이 필요합니다. (JSON)");
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
```  

3. AuthenticationFilter에게 인증용 객체(UsernamePasswordAuthenticationToken)을 전달받는다.  
4. 실제 인증을 할 AuthenticationProvider에게 Authentication객체(UsernamePasswordAuthenticationToken)을 다시 전달한다.  
5. DB에서 사용자 인증 정보를 가져올 UserDetailsService 객체에게 사용자 아이디를 넘겨주고 DB에서 인증에 사용할 사용자 정보(사용자 아이디, 암호화된 패스워드, 권한 등)를 UserDetails(인증용 객체와 도메인 객체를 분리하지 않기 위해서 실제 사용되는 도메인 객체에 UserDetails를 상속하기도 한다.)라는 객체로 전달 받는다.
6. AuthenticationProvider는 UserDetails 객체를 전달 받은 이후 실제 사용자의 입력정보와 UserDetails 객체를 가지고 인증을 시도한다.
> 4~6번이 AuthenticationProvider가 담당한다.

```java
/*
 * AuthenticationFilter에게 인증용객체(UsernamePasswordAuthenticationToken)를 전달 받는다.
 * */
public class FormLoginAuthProvider implements AuthenticationProvider {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    public FormLoginAuthProvider(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /*
     *  DB에서 사용자 인증 저보를 가져올 UserDetailsService 객체에게 사용자 아이디를 넘겨준다.
     *  리턴값으로 UserDetails(인증용 객체)를 받고,
     *  인증용 객체와 도메인 객체를 분리하지 않기 위해서 실제 사용되는 도메인 객체에 UserDetails를 상속하기도 한다
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // FormLoginFilter 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String username = token.getName();
        String password = (String) token.getCredentials();

        // UserDetailsService 를 통해 DB에서 username 으로 사용자 조회
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(null, null, userDetails.getAuthorities());
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```
```java
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user);
    }
}
```
```java
public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }
}
```

10. 인증이 완료되면 사용자 정보를 가진 Authentication 객체를 SecurityContextHolder에 담은 이후 AuthenticationSuccessHandle를 실행한다.(실패시 AuthenticationFailureHandler를 실행한다.)
```java
/*
 * 인증이 완료되면 사용자 정보를 가진 Authentication 객체를
 * SecurityContextHolder에 담은 이후 AuthenticationSuccessHandle로직이 수행된다.
 * */
public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {

        if (!Optional.ofNullable(authentication.getPrincipal()).isPresent()) {
            response.addHeader(AUTH_HEADER, null);
        } else {
            final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
           
           // Token 생성
            final String token = JwtTokenUtils.generateJwtToken(userDetails);
            response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        }
    }

}
```

<br> 
<br> 
<br> 


# #시큐리티의 주요 filter 
> 현재 총 33개의 Spring Security Filter가 존재한다고 합니다.  

![화면 캡처 2022-06-04 124108](https://user-images.githubusercontent.com/81284265/171980596-65d69911-5d32-476c-b529-59bc76e7efe7.png)  

### SecurityContextPersistenceFilter
```
request가 발생하면 SecurityContext 객체의 생성, 저장, 조회를 담당하는 필터입니다.  

익명의 사용자의 경우 , 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder에 저장합니다.  

AnonymousAuthenticationFilter에서 AnonymousAuthenticationToken 객체를 SecurityContext에 저장합니다.  

인증 시, 새로운 SecurityContext를 생성하여 SecurityContextHolder에 저장합니다.  

UsernamePasswordAuthentication Filter에서 인증 성공 후  SecurityContext에 UsernamePasswordAuthentication 
객체를 Authentication 객체와 함께 저장합니다. 인증이 완료되면 Session에 SecurityContext를 저장하고 Response합니다.  

인증 후 , Session에서 SecurityContext를 꺼내 SecurityContextHolder에 저장합니다.
SecurityContext내 Authentication 객체가 있으면 인증을 유지합니다.  
```
### LogoutFilter
```
유저의 로그아웃을 진행합니다. 설정된 로그아웃 URL로 오는 요청을 감시하여, 해당 유저를 로그아웃 처리합니다.
```

### UsernamePasswordAuthenticationFilter
```
설정된 로그인 URL로 오는 요청을 감시하며, 유저 인증을 처리합니다. 
승인되면 Authentication을 부여하고 이동 할 페이지로 이동한다. 
인증 실패시, AuthenticationFailureHandler를 실행합니다.
```

### DefaultLoginPageGenerationgFilter
```
사용자가 별도의 로그인 페이지를 구현하지 않은 경우, 스프링에서 기본적으로 설정한 로그인 페이지를 처리합니다.
```

### BasicAuthenticationFilter
```
HTTP 요청의 (BASIC)인증 헤더를 처리하여 결과를 SecurityContextHolder에 저장합니다.
```

### RememberMeAuthenticationFilter
```
SecurityContext에 인증(Authentication) 객체가 있는지 확인하고 RememberMeservices를 구현한 객체의 요청이 있을 경우, 
RememberMe인증 토큰으로 컨텍스트에 주입합니다.
```
> 아직 Authentication 인증이 안된 경우라면 RememberMe 쿠키를 검사해서 인증 처리해준다.

### SecurityContextHolderAwareRequestFilter  
```
보안 관련 Servlet 3 스펙을 지원하기 위한 필터라고 한다.
```
### AnonymousAuthenticationFilter  
```
앞선 필터를 통해 인증이 아직도 안되었으면 해당 유저는 익명 사용자라고 Authentication을 정해주는 역할을 한다.
(Authentication이 Null인 것을 방지!!)
```
### SessionManagementFilter  
```
서버에서 지정한 세션정책에 맞게 사용자가 사용하고 있는지 검사하는 역할을 한다.
```
### ExcpetionTranslationFilter  
```
해당 필터 이후에 인증이나 권한 예외가 발생하면 해당 필터가 처리를 해준다.
```
### FilterSecurityInterceptor  
```
사용자가 요청한 request에 들어가고 결과를 리턴해도 되는 권한(Authorization)이 있는지를 체크한다. 
해당 필터에서 권한이 없다는 결과가 나온다면 위의 ExcpetionTranslationFilter필터에서 Exception을 처리해준다.
```
### HeaderWriterFilter 
```
Request의 Http 해더를 검사하여 header를 추가하거나 빼주는 역할을 한다.
```
### CorsFilter  
```
허가된 사이트나 클라이언트의 요청인지 검사하는 역할을 한다.
```
### CsrfFilter  
```
Post나 Put과 같이 리소스를 변경하는 요청의 경우 내가 내보냈던 리소스에서 올라온 요청인지 확인한다.
```
### ConcurrentSessionFilter 
```
동시 접속을 허용할지 체크한다.
```
### BearerTokenAuthenticationFilter 
```
Authorization 해더에 Bearer 토큰을 인증해주는 역할을 한다.
```
### BasicAuthenticationFilter 
```
Authorization 해더에 Basic 토큰을 인증해주는 역할을 한다.
```
### RequestCacheAwareFilter 
```
request한 내용을 다음에 필요할 수 있어서 Cache에 담아주는 역할을 한다. 다음 Request가 오면 이전의 Cache값을 줄 수 있다.
```


<br> 
<br> 
<br> 

# #마치는 글

스프링 시큐리티 5.7.x 부터 WebSecurityConfigurerAdapter 는 Deprecated 되었다. 그래서 앞으로 시간이 날때마다  
WebSecurityConfigurerAdapter로 구성된 WebSecurityConfig를 [SecurityConfig](#securityconfig)와 같은 코드로 수정하려고한다. 
<br>
<br>
<br>
만약 [인증과 인가](https://github.com/whitewise95/TIL/tree/main/Security) 에 대해서도 알고 싶다면 읽어보는 것도 좋다.
<br>
<br>
<br>
이 글을 쓰기 옮겨적기 전 까지 공부하고 참고한 블로그와 영상들입니다.  
[[10분 테코톡] 🐻작은곰의 Spring Security](https://www.youtube.com/watch?v=aEk-7RjBKwQ)  
[백기선님 1부](https://www.youtube.com/watch?v=zANzxwy4y3k)  
[백기선님 1부](https://www.youtube.com/watch?v=fG21HKnYt6g )  
[Bottlehs Tech Blog](https://www.bottlehs.com/springboot/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-spring-security%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9D%B8%EC%A6%9D-%EB%B0%8F-%EA%B6%8C%ED%95%9C%EB%B6%80%EC%97%AC/)  
[seongwon97.log](https://velog.io/@seongwon97/Spring-Security-Filter%EB%9E%80)  
[{   튜나 개발일기   }](https://devuna.tistory.com/59)  
[upsw-p.tistory](https://upsw-p.tistory.com/57)  


### SecurityConfig
- [스프링시큐리티 관련 문서](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccountService accountService;
    private final DataSource dataSource;

    /**
     * Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter 는 Deprecated.
     * -> SecurityFilterChain, WebSecurityCustomizer 를 상황에 따라 빈으로 등록해 사용한다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/check-email", "/check-email-token",
                        "/email-login", "/check-email-login", "login-link", "/profile/*").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .rememberMe().userDetailsService(accountService).tokenRepository(tokenRepository())
                .and().build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
```


# #[CORS란?](https://github.com/whitewise95/TIL/blob/main/Security/CORS%EB%9E%80%3F.md) 
> 프론트와 백엔드을 혼자서 개발할 때는 몰랐는데 각각 서버가 다르다면 CORS라는 문제를 겪는다... 이번 기회에 지겹도록 깨닫게 되었다.

# #예제
```
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("프로그램에서 제공하는 URL ex) /**")
                .allowedOrigins("허용할 주소 ex) https/naver.com")
                .allowedMethods("허용할 메소드 ex) POST, OPTIONS")
                .allowedHeaders("허용할 헤더 ex) Content-Type, Custom-Header, Authorization")
                .allowCredentials(false) // 쿠키 요청을 허용한다(다른 도메인 서버에 인증하는 경우에만 사용해야하며, true 설정시 보안상 이슈가 발생할 수 있다)
                .maxAge(5); // preflight 요청에 대한 응답을 브라우저에서 캐싱하는 시간;
    }
}

```

# #COMMENT
스프링 시큐리티도 사용한다면 시큐리티 config에도 따로 설정을 해줘야한다.  
[스프링 시큐리티 CORS 설정하는 방법](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/JWT%20%EC%99%80%20Security%20%EC%84%A4%EC%A0%95.md
)

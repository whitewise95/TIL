# #[CORS란?](https://github.com/whitewise95/TIL/blob/main/Security/CORS%EB%9E%80%3F.md)
> CORS를 설정을 다 했는데 그래도 CORS에러가 발생했다... 이유는 스프링시큐리티도 사용하고 있어서 그랬다 만약 스프링 시큐리티를 사용한다면 스프링시큐리티에 설정을 해줘야한다.

# #예제
```
  // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader(AUTH_HEADER);
        configuration.addAllowedHeader(AUTH_HEADER);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
```

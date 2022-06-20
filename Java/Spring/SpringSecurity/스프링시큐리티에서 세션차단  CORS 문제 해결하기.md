# # 목차
1) [들어가는 말](#들어가는-말)
2) [CORS문제 없을 경우](#cors문제-없을-경우)
3) [CORS문제](#cors문제)
4) [서버에서 설정](#서버에-설정)
5) [프로튼에서 설정](#프론트에서-설정)
6) [Comment](#comment)

<br>
<br>
<br>

# #들어가는 말
[스프링시큐리티](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/SpringSecurity(%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0).md)를 이용해 [세션방식](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%20%EC%84%B8%EC%85%98%20%EC%98%88%EC%A0%9C.md)으로 로그인을 구현한다면 `프론트와 서버가 합쳐져있는 프로젝트라면 CORS 문제가 없어 세션이 자동으로 쿠키에 저장`이 된다. 하지만 `프론트를 나누기 시작` 하면 CORS 문제로 `세션이 받아지지도 않고 자동으로 쿠키에 저장이 되지 않는다`  그 문제를 해결할 것이다.


<br>
<br>
<br>

# #CORS문제 없을 경우
회원가입 후 -> 로그인 -> 서버에서 시큐리티 로직을 타게되어 아래와 같이 세션이 쿠키에 저장된다.  

![화면 캡처 2022-06-20 154817](https://user-images.githubusercontent.com/81284265/174541685-d0f67c2f-582e-4d40-a673-0bf52d914118.png)


<br>
<br>
<br>

# #CORS문제
회원가입 후 -> 로그인 -> 서버에서 시큐리티 로직을 타고 내려와도 쿠키에 저장이 되지 않고 네트워크탭에서는 확인이 가능하지만 HttpOnly 이기때문에 프론트에서 접근할 방법이 없다.  

![화면 캡처 2022-06-20 155041](https://user-images.githubusercontent.com/81284265/174542196-8d15d2cd-866b-4070-9f44-f382eeb6991a.png)  
![화면 캡처 2022-06-20 155031](https://user-images.githubusercontent.com/81284265/174542206-dc5fb3af-978b-437c-b1d8-24c5bb90f781.png)


<br>
<br>
<br>

# #서버에 설정
- cors 설정에 `configuration.setAllowCredentials(true);` 를 추가해준다.

```java
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedOriginPattern("http://localhost:3000");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
```

- form 로그인은 쓰지 않으니 `.formLogin().disable();` 해준다.

```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource());

        http.addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/idCheck/**").permitAll()
                .antMatchers("/user/nicknameCheck/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();

    }
```


<br>
<br>
<br>


# #프론트에서 설정
> axios를 사용함

- 전역으로 `withCredentials를 true` 로 설정해준다.

```html
axios.defaults.withCredentials = true; //전역으로 적용됨
```

- axios 3번째 변수로 ` withCredentials: true // 쿠키 cors 통신 설정` 설정을 해준다.
```javaScript
        axios.post("http://localhost:8090/user/login",
        data, 
        { 
            withCredentials: true // 쿠키 cors 통신 설정
        }
```

<br>
<br>
<br>

# #Comment
서버와 프론트가 동시에 설정해줘야지만 세션을 받을 수 있다.
처음에 안받아져서 당황을 했지만 이렇게 간단한 방법이 있었다...





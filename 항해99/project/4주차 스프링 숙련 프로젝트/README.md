# #프로젝트 소개 [다운로드링크](https://drive.google.com/file/d/12vV45ZiK4d7q9ipw7qiQkwEjneGA4mhf/view?usp=sharing)

스프링 입문 프로젝트 " [MyBlog](https://github.com/whitewise95/TIL/tree/main/%ED%95%AD%ED%95%B499/project/sparta_spring) " 에서 스프링 시큐리티와 JWT를 사용하여 회원관리 및 로그인 유지와 댓글CRUD도 가능하게 develop 하는 프로젝트 입니다. [MyBlog](https://github.com/whitewise95/TIL/tree/main/%ED%95%AD%ED%95%B499/project/sparta_spring)  링크를 타시면 저번 주차에 어떻게 했는지 확인이 가능합니다.  

# #이번 프로젝트에서 배운점 및 알게된 점
> 링크를 타고 들어가면 정리해둔 내용을 확인 할 수 있습니다.

1. [스프링 시큐리티](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/SpringSecurity(%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0).md)와 [인증과 인가](https://github.com/whitewise95/TIL/blob/main/Security/%EC%9D%B8%EC%A6%9D%EA%B3%BC%20%EC%9D%B8%EA%B0%80.md)에  대해서도 알게되었다. 기존에 " [스프링 부트와 AWS로 구현하는 웹서비스 따라하기](https://github.com/whitewise95/JPA_JUnitTest_Gradle_Oauth2_Practice) " 라는 책 따라서 한번 구현 해본적이 있지만 이렇게 깊이 공부까지 못했고 나의 기존 서비스에 적용해보고 난 에러들을 눈으로 볼 수 있었다.

```
  첫번째 - 기존의 우분투환경에 이미지를 업로드하는 로직도 png 파일이 깨져서 표시되었다 이런 사소한 부분까지
          시큐리티에서 인가와 인증을 설정 해줘야한다.
          
  두번째 - 모든 API에 대한 인가와 인증관리를 해줘야한다.
```  

2. [스프링 시큐리티를 이용한 JWT](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/JWT%20%EC%99%80%20Security%20%EC%84%A4%EC%A0%95.md)를 알게 되었다. 지금까지 JWT는 단독으로 사용하는 줄 알았는데 Filter Chain에 포함시킬 Filter를 만들어 인가와 인증을 적용시킬 수 있다.  

3. Ajax에 헤더에 데이터를 태워 통신하는 법을 알게되었다. 토큰을 헤더 담아 서버에서 받는데 Ajax 기본적이 통신만 해보았지 헤더를 설정할 수 있다는걸 알게되었다.  

# #보안할 점 및 문제해결한 부분

### RestFul하게 API설계
RestFul하게 API설계하고 싶어서 느리더라도 천천히 프로그래밍을 했다. 하지만 다 끝나고 생각해보니 계층구도로 URI를 잘못 설계한 느낌이다. 보안을 하고싶다.  
<br>
현재 기술매니저님께 "데이터를 쓰지 않았도 아래 보안할 부분처럼 글 ID를 보내야하나요?" 라고 질문을 남겼는데 답변을 기다리고있다.
```
현재 uri 구성
/comment/{댓글ID}

보안하고싶은 부분
/comment/{글ID}/{댓글ID}
```

### dependensy 버전 문제?!
기존에 JWT 라이브러리를 'com.auth0:java-jwt:3.13.0' 를 써서 JWT를 구현했는데 우분투환경에 배포하면 아래와 같이 에러가 났다.  
<br>
나와 같은 문제를 겪는 사람은 아직 나의 서치능력으론 못찾았지만 group: 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1' 라이브러리로  
변경하여 JWT부분을 다시 구현하였다.
```
Caused by: java.lang.ClassNotFoundException: com.auth0.jwt.interfaces.JWTVerifier
```





# # 추가된 API명세  

### 📍 회원관리 API  

| 기능   | method | url           | request | response |
|------|--------|---------------|---------|----------|
| 회원가입 | POST   | /user/signup  | 타입1     | 타입3      |
| 로그인  | POST   | /user         | 타입2     | 타입3      |  

```JSON
⭐️ 타입1

{
  "usernaem" : " ",
  "password" : " ",
  "passwordCheck" : " ",
  "email" : " "
}
```  

```JSON
⭐️ 타입2

{
  "usernaem" : " ",
  "password" : " "
}
```  

```JSON
⭐️ 타입3

{
  "id" : 
  "content" : ""
  "password" : ""
  "imgUrl" : ""
  "writer" : ""
  "createDate" : ""
  "ModifiedDate" : ""
}
```


### 📍 댓글CURD API  

| 기능    | method | url                   | request | response |
|-------|--------|-----------------------|---------|----------|
| 댓글 조회 | GET    | /comment/{blogId}     |         | 타입5      |
| 댓글 쓰기 | POST   | /comment/{blogId}     | 타입4     | 타입6      |
| 댓글 삭제 | DELETE | /comment/{commentId}  | 타입4     | 타입6      |
| 댓글 수정 | PUT    | /comment/{commentId}  | 타입4     | 타입6      |

```JSON
⭐️ 타입4

Header =  Authorization : "토큰"

{
  "comment" : "sadasd222"
}
```

```JSON
⭐️ 타입5
{
    "createDate": "2022.06.03 11:22:43",
    "modifiedDate": "2022.06.03 11:22:43",
    "id": 4,
    "comment": "214512412",
    "blogId": 1,
    "userId": "admin"
}
```  
```JSON
⭐️ 타입6
    
{
  "statusCode": 200 또는 500,
  "message": "메세지"
}
```

# #스프링 시큐리티를 사용해 회원관리하는 방법을 순서도로 

### 📍 회원가입 순서도  
![success Handler (2)](https://user-images.githubusercontent.com/81284265/171804120-62c32569-1d77-4f6e-8ba7-5249de8f0a14.png)  

### 📍 로그인 순서도
![success Handler (1)](https://user-images.githubusercontent.com/81284265/171804126-ad4d6207-18c5-49a8-b97f-cc33d9307741.png)

### 📍 토큰으로 로그인유지 순서도
![success Handler](https://user-images.githubusercontent.com/81284265/171804128-b8254796-4f4c-4fd6-a9e5-27e482a47771.png)





# #추가된 UI  

### 📍 로그인화면  
![로그인화면](https://user-images.githubusercontent.com/81284265/171799772-09e29d2b-0b20-4fff-acb8-5f645c7bfe99.png)  
### 📍 로그아웃 버튼  
![로그아웃](https://user-images.githubusercontent.com/81284265/171799777-852d9a82-9a36-4b64-8cdd-2094599360a8.png)  
### 📍 댓글 모달 열기 가능  
![화면 캡처 2022-06-03 145403](https://user-images.githubusercontent.com/81284265/171799776-4a082174-8440-4c40-863f-63cb8c4e8cec.png)  
### 📍 댓글CURD  
![댓글남기기](https://user-images.githubusercontent.com/81284265/171799800-21b372b8-db04-4f7a-aa01-6d24cbf4ab05.png)  

# #시큐리티 와 JWT 로직을 위한 생성된 구조
```
프로젝트\SRC\MAIN\JAVA\COM\SPARTA_SPRING\SPARTA_SPRING3\SECURITY
│  FilterSkipMatcher.java
│  FormLoginSuccessHandler.java
│  UserDetailsImpl.java
│  UserDetailsServiceImpl.java
│  WebSecurityConfig.java
│
├─filter
│      FormLoginFilter.java
│      JwtAuthFilter.java
│
├─jwt
│      HeaderTokenExtractor.java
│      JwtDecoder.java
│      JwtPreProcessingToken.java
│      JwtTokenUtils.java
│
└─provider
        FormLoginAuthProvider.java
        JWTAuthProvider.java

```




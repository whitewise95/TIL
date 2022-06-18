# TIL  
> Today I Learned 
- 최대한 이해한 것까지만 적도록 하며 아니여도 중요한 내용이라 앞으로 공부하려고 적어놓는 경우도 있습니다.
<br>
<br>
<br>
<br> 


# 목록
1. 자바
    - [자바](#java)
    - [객체지향](#객체지향)
    - [컬렉션](#컬렉션)
    - [스트림(Stream)](#스트림stream)
    - [spring](#spring)
    - [springsecurity](#springsecurity스프링-시큐리티)
2. [DataBase](#database)
    - [JPA](#jpa)
3. [aws](#aws)
    - [EC2](#ec2)
    - [RDS](#rds)
4. [css](#css)
5. [html](#html)
6. [python](#파이썬)
7. [security](#security)
8. [algorithm](#알고리즘)
9. [항해99](#항해99)
10. [템플릿엔진](#템플릿엔진)
11. [소스](#소스)
12. [그 외](#그-외)

<br>
<br>
<br>
<br> 

## JAVA
#### java
- [Iterator란?](https://github.com/whitewise95/TIL/blob/main/Java/Iterator%EB%9E%80%3F.md)
- [자바 Arrays](https://github.com/whitewise95/TIL/blob/main/Java/Arrays.md)
- [JSON 파싱하기](https://github.com/whitewise95/TIL/blob/main/Java/JSON%20%ED%8C%8C%EC%8B%B1.md)
- [JVM,JRE,JDK](https://github.com/whitewise95/TIL/blob/main/Java/JVM.md)
- [String, StringBuffer, StringBuilder 차이 및 장단점](https://github.com/whitewise95/TIL/blob/main/Java/String%2C%20StringBuffer%2C%20StringBuilder%20%EC%B0%A8%EC%9D%B4%20%EB%B0%8F%20%EC%9E%A5%EB%8B%A8%EC%A0%90.md)
- [날짜와 시간 다루기(date,LocalDate)](https://github.com/whitewise95/TIL/blob/main/Java/%EB%82%A0%EC%A7%9C%EC%99%80%20%EC%8B%9C%EA%B0%84%20%EB%8B%A4%EB%A3%A8%EA%B8%B0.md)  
- [제네릭스](https://github.com/whitewise95/TIL/blob/main/Java/%EC%A0%9C%EB%84%A4%EB%A6%AD%EC%8A%A4.md)  
#### 스트림(Stream)
- [스트림(Stream)의 개념](https://github.com/whitewise95/TIL/blob/main/Java/%EC%8A%A4%ED%8A%B8%EB%A6%BC(Stream)/%EC%8A%A4%ED%8A%B8%EB%A6%BC%EC%9D%98%20%EA%B0%9C%EB%85%90.md)
- [스트림 사용방법](https://github.com/whitewise95/TIL/blob/main/Java/%EC%8A%A4%ED%8A%B8%EB%A6%BC(Stream)/%EC%8A%A4%ED%8A%B8%EB%A6%BC%20%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95.md)
#### 객체지향
- [객체지향이란?](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D.md)  
- [JVM](https://github.com/whitewise95/TIL/blob/main/Java/JVM.md)  
- [클래스,인스턴스,메소드](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4%20%20%EB%A9%94%EC%86%8C%EB%93%9C.md)  
- [생성자(constructor)](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EC%83%9D%EC%84%B1%EC%9E%90(constructor).md)  
- [상속](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EC%83%81%EC%86%8D.md)  
- [오버로딩 vs 오버라이딩](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EC%98%A4%EB%B2%84%EB%A1%9C%EB%94%A9(overloading)%20vs%20%EC%98%A4%EB%B2%84%EB%9D%BC%EC%9D%B4%EB%94%A9(overriding).md)  
- [접근제어자](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EC%A0%91%EA%B7%BC%EC%A0%9C%EC%96%B4%EC%9E%90.md)  
- [인터페이스 vs 추상클래스](https://github.com/whitewise95/TIL/blob/main/Java/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5/%EC%B6%94%EC%83%81%ED%81%B4%EB%9E%98%EC%8A%A4%2C%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4.md)  
#### 컬렉션
- [리스트(list),셋(set),맵(map)](https://github.com/whitewise95/TIL/blob/main/Java/%EC%BB%AC%EB%A0%89%EC%85%98/%EB%A6%AC%EC%8A%A4%ED%8A%B8(list)%2C%EC%85%8B(set)%2C%EB%A7%B5(map).md)  
- [스택(stack), 큐(queue), ArrayDeque](https://github.com/whitewise95/TIL/blob/main/Java/%EC%BB%AC%EB%A0%89%EC%85%98/%EC%8A%A4%ED%83%9D(stack)%2C%20%ED%81%90(queue)%2C%20ArrayDeque.md)  
#### SPRING
- [IOC, DI, Bean](https://github.com/whitewise95/TIL/blob/main/Java/Spring/IOC%20,%20DI,%20Bean.md)
- [DTO, DAO, VO에 대해서](https://github.com/whitewise95/TIL/blob/main/Java/Spring/DTO%2C%20DAO%2C%20VO%EC%97%90%20%EB%8C%80%ED%95%B4%EC%84%9C.md)
- [@Scheduled 스케쥴러 애노테이션](https://github.com/whitewise95/TIL/blob/main/Java/Spring/%40Scheduled.md)
- [Spring Boot 프로젝트 외부 경로 파일 접근(webConfig)](https://github.com/whitewise95/TIL/blob/main/Java/Spring/Spring%20Boot%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%99%B8%EB%B6%80%20%EA%B2%BD%EB%A1%9C%20%ED%8C%8C%EC%9D%BC%20%EC%A0%91%EA%B7%BC(webConfig).md)
- [MVC패턴](https://github.com/whitewise95/TIL/blob/main/Java/Spring/MVC%20%ED%8C%A8%ED%84%B4.md)  
- [스프링부트 CORS 설정](https://github.com/whitewise95/TIL/blob/main/Java/Spring/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%20CORS%20%EC%84%A4%EC%A0%95.md)
- [controller에서 데이터 바인딩하는 방법](https://github.com/whitewise95/TIL/blob/main/Java/Spring/controller%EC%97%90%EC%84%9C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EB%B0%94%EC%9D%B8%EB%94%A9%ED%95%98%EB%8A%94%20%EB%B0%A9%EB%B2%95.md)
#### SpringSecurity(스프링 시큐리티)
- [스프링 시큐리티란?](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/SpringSecurity(%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0).md)  
- [JWT 와 Security 설정](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/JWT%20%EC%99%80%20Security%20%EC%84%A4%EC%A0%95.md)  
- [스프링시큐리티 CORS설정](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%20CORS%EC%84%A4%EC%A0%95.md)
- [스프링시큐리티에서 세션차단 CORS 문제 해결하기](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%97%90%EC%84%9C%20%EC%84%B8%EC%85%98%EC%B0%A8%EB%8B%A8%20%20CORS%20%EB%AC%B8%EC%A0%9C%20%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0.md)
- [스프링시큐리티 세션 예제](https://github.com/whitewise95/TIL/blob/main/Java/Spring/SpringSecurity/%EC%8A%A4%ED%94%84%EB%A7%81%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%20%EC%84%B8%EC%85%98%20%EC%98%88%EC%A0%9C.md)
<br>
<br>
<br>
<br>  

## DataBase
-  [JPA와 SQL](https://github.com/whitewise95/TIL/blob/main/DataBase/ORM%EA%B3%BC%20SQL.md)  
#### JPA
- [JPA란?](https://github.com/whitewise95/TIL/blob/main/DataBase/JPA/JPA%EB%8C%80%ED%95%B4%EC%84%9C.md)  
- [JPA로 Pageable사용해서 페이징 처리하기](https://github.com/whitewise95/TIL/blob/main/DataBase/JPA/JPA%EB%A1%9C%20Pageable%EC%82%AC%EC%9A%A9%ED%95%B4%EC%84%9C%20%ED%8E%98%EC%9D%B4%EC%A7%95%20%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0%20.md)  




<br>
<br>
<br>
<br>  


## AWS
#### EC2
- [EC2구매부터 배포까지](https://github.com/whitewise95/TIL/blob/main/AWS/EC2/EC2%20%EA%B5%AC%EB%A7%A4%20%EB%B6%80%ED%84%B0%20%EB%B0%B0%ED%8F%AC%EA%B9%8C%EC%A7%80.md)  
#### RDS
- [RDS구매 부터 포트 열어주기](https://github.com/whitewise95/TIL/blob/main/AWS/RDS/RDS%EA%B5%AC%EB%A7%A4%20%EB%B6%80%ED%84%B0%20%ED%8F%AC%ED%8A%B8%20%EC%97%B4%EC%96%B4%EC%A3%BC%EA%B8%B0.md)  
- [RDS 인텔리제이와 연결](https://github.com/whitewise95/TIL/blob/main/AWS/RDS/RDS%20%EC%9D%B8%ED%85%94%EB%A6%AC%EC%A0%9C%EC%9D%B4%EC%99%80%20%EC%97%B0%EA%B2%B0.md)


<br>
<br>
<br>
<br>  


## CSS
- [MyBlog](https://github.com/whitewise95/TIL/tree/main/CSS/MyBlog)


<br>
<br>
<br>
<br>  



## HTML
- [twbspagination예제 및 옵션](https://github.com/whitewise95/TIL/blob/main/HTML/paging/twbspagination%EC%98%88%EC%A0%9C%20%EB%B0%8F%20%EC%98%B5%EC%85%98.md)
- [SweetAlert2](https://github.com/whitewise95/TIL/blob/main/HTML/SweetAlert2.md)


<br>
<br>
<br>
<br>  

## 파이썬
- [PyMongo](https://github.com/whitewise95/TIL/blob/main/Python/PyMongo(MongoDB).md).
- [PyJWT](https://github.com/whitewise95/TIL/blob/main/Python/PyJWT(JWT).md).
- [Flask](https://github.com/whitewise95/TIL/blob/main/Python/Flask.md)
- [Requests](https://github.com/whitewise95/TIL/blob/main/Python/Requests.md)
- [f-string formatting](https://github.com/whitewise95/TIL/blob/main/Python/f-string%20formatting.md)

<br>
<br>
<br>
<br>   

## Security
- [인증과 인가](https://github.com/whitewise95/TIL/blob/main/Security/%EC%9D%B8%EC%A6%9D%EA%B3%BC%20%EC%9D%B8%EA%B0%80.md)  
- [JWT](https://github.com/whitewise95/TIL/blob/main/Security/JWT.md)
- [CORS에 대해서](https://github.com/whitewise95/TIL/blob/main/Security/CORS%EB%9E%80%3F.md)


<br>
<br>
<br>
<br>  


## 알고리즘
- [프로그래머스](https://github.com/whitewise95/TIL/tree/main/algorithm).


## 그 외
- [API](https://github.com/whitewise95/TIL/blob/main/the_rest/API.md)
- [REST API](https://github.com/whitewise95/TIL/blob/main/the_rest/REST%20API.md)
- [나의 프로젝트 구성을 한 눈에](https://github.com/whitewise95/TIL/blob/main/the_rest/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%ED%8F%B4%EB%8D%94%20%EA%B5%AC%EC%A1%B0.md)
- [클라이언트에서 서버까지 그림으로](https://github.com/whitewise95/TIL/blob/main/the_rest/%ED%81%B4%EB%9D%BC%EC%9D%B4%EC%96%B8%ED%8A%B8%EC%97%90%EC%84%9C%20%EC%84%9C%EB%B2%84%EA%B9%8C%EC%A7%80%20%EC%A7%84%ED%96%89.md)  

<br>
<br>
<br>
<br>  

## 항해99
- [1주차 웹 미니프로젝트(Python)](https://github.com/whitewise95/voyage99/tree/main/1%EC%A3%BC%EC%B0%A8%20%ED%8C%8C%EC%9D%B4%EC%8D%AC%20%EB%AF%B8%EB%8B%88%20%EC%9B%B9%EA%B0%9C%EB%B0%9C%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)  
- [2주차 객체지향 과제](https://github.com/whitewise95/voyage99/tree/main/2%EC%A3%BC%EC%B0%A8%20%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)  
- [3주차 스프링입문 프로젝트](https://github.com/whitewise95/voyage99/tree/main/3%EC%A3%BC%EC%B0%A8%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)  
- [4주차 스프링숙련 프로젝트](https://github.com/whitewise95/voyage99/tree/main/4%EC%A3%BC%EC%B0%A8%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%88%99%EB%A0%A8%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)
- [5주차 스프링심화 프로젝트](https://github.com/whitewise95/voyage99/tree/main/5%EC%A3%BC%EC%B0%A8%20%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%8B%AC%ED%99%94%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)  
- [6주차 자바 및 리액트 미니 프로젝트](https://github.com/whitewise95/voyage99/tree/main/6%EC%A3%BC%EC%B0%A8%20%EC%9E%90%EB%B0%94%20%EB%B0%8F%20%EB%A6%AC%EC%95%A1%ED%8A%B8%20%EB%AF%B8%EB%8B%88%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)

<br>
<br>
<br>
<br>  


## 템플릿엔진
#### python
- [jinja2](https://github.com/whitewise95/TIL/blob/main/Template%20Engine/jinja2/jinja2%EA%B8%B0%EC%B4%88.md).


<br>
<br>
<br>
<br>  

## 소스
- [스프링 부트와 AWS로 구현하는 웹서비스 따라하기 example](https://github.com/whitewise95/JPA_JUnitTest_Gradle_Oauth2_Practice)  
- [전자정부프레임워크 CRUD 게시판 example](https://github.com/whitewise95/egovframe_example)
- [JPA를 이용한 CRUD API 소스](https://github.com/whitewise95/TIL/tree/main/Java/example/JPA_CRUD_%20examples/Only%20API%20exists)
- [Excel Upload and Download](https://github.com/whitewise95/TIL/tree/main/Java/example/Excel%20in%20java)
- [이미지 업로드(서버의 폴더(o) SFTP(x))](https://github.com/whitewise95/TIL/blob/main/Java/example/%5Bjava%5D%EC%9D%B4%EB%AF%B8%EC%A7%80%20%EC%97%85%EB%A1%9C%EB%93%9C%20(imageUpload).md)


<br>
<br>
<br>
<br>  


## 메모장
- 상속과 구현에 대해 더 알아보기
- supur() 에대해서 알아보기
- 서버 사이드렌더링 vs 클라이언트 사이드 렌더링 알아보기



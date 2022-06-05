# 목차
<br>  

1. [영속성](#영속성)
2. [영구적인 객체](#영구적인-객체)
3. [Persistence Framework](#Persistence-Framework)
4. [ORM이란?]()

<br>  
<br>  

## #영속성
>(Persistence)
데이터를 생성한 프로그램이 종료되더라도 사라지지 않는 데이터의 특성을 말한다.
영속성을 갖지 않는 데이터는 단지 메모리에서만 존재하기 때문에 프로그램을 종료하면 모두 잃어버리게 된다.

<br>  
<br>  

## #영구적인 객체
메모리 상의 데이터를 파일 시스템, 관계형 테이터베이스 혹은 객체 데이터베이스 등을 활용하여 영구적으로 저장하여 영속성 부여한다.
대표적으로 3가지 방법이 있는데 그 중에 `Persistence Framework를 정리`했습니다.  
  
데이터를 데이터베이스에 저장하는 3가지 방법  
```
🌑1) JDBC (java에서 사용)
🌑2) Spring JDBC (Ex. JdbcTemplate)
🌕3) Persistence Framework (Ex. Hibernate, Mybatis 등)
```


JDBC 프로그래밍의 복잡함이나 번거로움 없이 간단한 작업만으로 데이터베이스와 연동되는  

시스템을 빠르게 개발할 수 있으며 안정적인 구동을 보장한다.  

Persistence Framework는 `SQL Mapper`와 `ORM`으로 나눌 수 있다.  

`Ex) JPA, Hibernate, Mybatis 등 ` 






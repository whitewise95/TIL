# 목차
<br>  


1. [들어가는 글](#들어가는-글)  
2. [영속성](#영속성)  
3. [영구적인 객체](#영구적인-객체)  
4. [Persistence Framework](#persistence-framework)  
5. [SQL이란?](#sql이란)  
6. [SQL의 규칙](#sql의-규칙)  
7. [ORM이란?](#orm이란)  
8. [ORM의 규칙](#orm의-규칙)  
9. [마치는 글](#마치는-글)  
 

<br>  
<br>  

## #들어가는 글   
취업하고 첫 프로젝트를 대구은행 어드민 서버개발를 진행했는데 프로젝트가 이미 4개월 진행된 상태에서 중간투입이 되었던 터라  
JPA를 사용한다고 그래서 좋은기회라 생각해 참여했지만 프로젝트마감이 얼마 남지 않은 상황에 JPA를 제대로 사용해보지 못하고   
나만 Mybatis로 변경하여 SQL를 작성했던 기억이 난다. 그 때 좀 써보려고 JPA의 관련 책을 사서 한 두번 써보았지만   
항해99에 와서 제대로 JPA에 대해 배우고 프로젝트마다 사용하다보니 SQL보다 너무 간편하고 코드가 간결해져서 너무 편리하단 생각을 했다.  

<br>  
<br>  


## #영속성
데이터를 생성한 프로그램이 종료되더라도 사라지지 않는 데이터의 특성을 말한다.
`영속성을 갖지 않는 데이터는 단지 메모리에서만 존재하기 때문에 프로그램을 종료하면 모두 잃어버리게 된다.`

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

<br>  
<br>  


## #Persistence Framework  

JDBC 프로그래밍의 복잡함이나 번거로움 없이 간단한 작업만으로 데이터베이스와 연동되는  

시스템을 빠르게 개발할 수 있으며 안정적인 구동을 보장한다.  

Persistence Framework는 `SQL Mapper`와 `ORM`으로 나눌 수 있다.  

`Ex) JPA, Hibernate, Mybatis 등 ` 

<br>  
<br>  

## #SQL이란?
> Object와 SQL의 필드을 매핑하여 데이터를 객체화하는 기술.  

객체와 테이블간의 관계를 매핑하는 것이 아니라,  

`SQL문을 직접 작성`하고 `쿼리 수행결과를 어떠한 객체에 매핑하여 줄 지 바인딩`하는 방법. 즉 `SQL 의존적인 방법`이다.   

<br>  
<br>  


## #SQL의 규칙
📍 SQL은 명령이 끝나는 부분을 세미콜론(;)으로 구분합니다. 
```sql
	-- 단일 라인 명령
SELECT * FROM tTable;
 
-- 멀티 라인 명령
SELECT * 
FROM tTable
WHERE city = 'Seoul';

```  
📍 단일 라인(Single Line) 주석: 맨 앞에 --를 붙여서 사용
```sql

-- Select Example (이 부분은 실행되지 않음)
SELECT * FROM tTable;
```

📍 블록 주석(Multi Line): /* */로 감싸줍니다. 
```sql
	/* 여러줄 주석 예시
Select All Records from tTable
Where city in Seoul (SQL의 조건문)
*/
SELECT * FROM tTable WHERE city = 'Seoul';

```

📍 SQL은 보통 대소문자를 가리지 않습니다. 
```sql
-- 아래 두 명령어는 동치
SELECT * FROM tTable;
Select * from ttable;
```


<br>  
<br>  


## #ORM이란?  
>Object와 DB테이블을 매핑하여 데이터를 객체화하는 기술.  

CRUD 관련 메소드를 사용하면 `자동으로 SQL이 만들어져 개발자가 반복적인 SQL을 직접 작성하지 않아도 되고`, 

`DBMS에 종속적이지 않다`. 또한 `복잡한 쿼리의 경우 JPQL을 사용하거나 SQL Mapper를 혼용`하여 사용할 수 있다.  

Java ORM 기술에 대한 인터페이스 `표준을 JPA`라고 하고, 이를 구현한 가장 `대표적인 기술이 Hibernate`이다.  

ex) Hibernate, EclipseLink, DataNucleus




<br>  
<br>  



## #ORM의 규칙 
> 간단하게 3가지로 정의
📍 @Entity 어노테이션으로 테이블을 자동으로 생성
```JAVA
@Entity
public clss Data {
  private String data;
}
```
📍 @Id 어노테이션으로 PK값을 자동으로 설정
```JAVA
@Entity
public clss Data {

    @Id
    private String data;
}
```
📍 @column 어노테이션으로 PK값을 자동으로 설정
```JAVA
@Entity
public clss Data {

    @Id
    private String data;
  
    @column
    private String data2;
}
```

📍 키워드로 자동으로 쿼리를 생성 ex) findAll()
```java
List<Data> dataList = jpa.findAll();
```  
```sql
자동으로 생성되는 SQL(MYSQL 기준)
 SELECT * FROM DATA;
```




<br>  
<br>  




## #SQL VS ORM

![SQL을 직접 다룰 때의 문제점 반복적인 코드의 작성 SQL 의존적 개발 패러다임의 불일치 객체 그래프 탐색](https://user-images.githubusercontent.com/81284265/172038331-49f267e1-6738-407a-9a35-d12901bd2396.png)



<br>  
<br>  


## #마치는 글
SQL과 JPA의 비교하면서 장단점을 비교하기엔 너무 다른 속성을 가지고 있다고 생각이든다.    
그리고 SQL에서 JPA로 바꾸는 이유는 간결한 코드와 유지보수면에서 우수하기도하지만 제일 큰 것은 패러다임의 불일치라 생각이 많이든다  
이 `패러다임의 불일치는 깊게 공부 후에 다시한번 정리하고싶어서 이번 내용에 제대로 다루지 못하였다.!`  

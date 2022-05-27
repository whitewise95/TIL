# 인텔리제이와 RDS 연결
- 첫번째 : [이동](https://ap-northeast-2.console.aws.amazon.com/rds/home?region=ap-northeast-2#databases:) 후 Springboot- database 클릭
- 두번째 : 엔드포인트 확인 후 드래그하여 복사

![화면 캡처 2022-05-27 134933](https://user-images.githubusercontent.com/81284265/170631581-47b65497-6194-43fe-800f-64746c1afd46.png)

- 세번째 : 우측 Database 탭을 클릭합니다
- 네번째 : Data Source > MySQL 을 클릭합니다. (제일 상단이 아니라면 중간 즈음 있을 거에요!)

![화면 캡처 2022-05-27 134951](https://user-images.githubusercontent.com/81284265/170631648-2705445f-6c39-4cbd-ab4a-616d8a19f595.png)

- 다섯번째 : DB정보 입력해주기
> Name: springboot-database  
> Host: 나의 엔드포인트  
> User: 나의 Username  
> Password: 나의 비밀번호  
> Database: myselectshop    

![화면 캡처 2022-05-27 135003](https://user-images.githubusercontent.com/81284265/170631744-8e295cf5-4eec-4718-942b-4d5594927ace.png)

- 여섯번째 : Test Connection 을 클릭하고, 아래와 같이 성공적으로 연결되면 OK 를 클릭합니다.  

![화면 캡처 2022-05-27 135017](https://user-images.githubusercontent.com/81284265/170631770-dbca4f8b-7845-47f2-9d0c-7492c7c2cc46.png)

- 일곱번째 : 스프링 부트 설정의 대부분은 application.properties 에서 관리합니다.
```
spring.datasource.url=jdbc:mysql://나의엔드포인트:3306/myselectshop
spring.datasource.username=나의USERNAME
spring.datasource.password=나의패스워드
spring.jpa.hibernate.ddl-auto=update
```

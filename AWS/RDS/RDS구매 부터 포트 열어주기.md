# RDS 구매하기 
- [RDS이동](https://ap-northeast-2.console.aws.amazon.com/console/home?region=ap-northeast-2#)
- 첫번쩨 : RDS를 검색하고 결과를 클릭합니다.
- 두번째 : "데이터베이스 생성"을 클릭합니다.  

![화면 캡처 2022-05-27 133831](https://user-images.githubusercontent.com/81284265/170630019-2cac1533-caa8-4263-8aeb-f0f75ad0b29c.png)

- 세번째 : "표준생성" 과 "MySQL"을 클릭합니다.


![화면 캡처 2022-05-27 133927](https://user-images.githubusercontent.com/81284265/170630105-66ae86aa-bc37-41d3-b354-0c714fe04216.png)
- 네번째 : "프리 티어"를 선택합니다.


![화면 캡처 2022-05-27 133937](https://user-images.githubusercontent.com/81284265/170630126-f67f4250-11c7-4005-8f44-1df72b2edd3c.png)
- 다섯번째 : 설정 입력하기
> DB 인스턴스 식별자에 "springboot-database" 라고 입력합니다. (원하는 이름으로 바꾸셔도 돼요!)  
> 마스터 사용자 이름과 암호에 만들고 싶은 계정의 아이디, 비밀번호를 입력합니다. DB 접속용으로 사용해야 하니 꼭 기억해주세요!  



![화면 캡처 2022-05-27 134047](https://user-images.githubusercontent.com/81284265/170630248-4a8462f7-9cd8-465c-a9a1-fbd5e9b817c9.png)  

- 여섯번째 : [중요] 연걸 > 추가 연결 구성 탭을 클릭합니다.
> 퍼블릭 액세스 기능: "예" 선택  


  ```
  이 설정이 되어있어야 우리 컴퓨터에서 AWS RDS 의 MySQL과 연결이 가능합니다. 꼭 퍼블릭 액세스 설정을 확인해주세요!
  ```
  
  
>  VPC 보안 그룹: "새로 생성" 선택  
>  새 VPC 보안 그룹 이름: springboot-db-security  
>  가용 영역: 옵션 중 아무거나 선택하셔도 괜찮아요.  


![화면 캡처 2022-05-27 134208](https://user-images.githubusercontent.com/81284265/170630423-e5636d2f-48fd-4055-bccb-1b0f338dc37c.png)  

- 일곱번째 : 추가 구성 > 초기 데이터베이스 이름 에 "myselectshop" 을 입력한다.


![화면 캡처 2022-05-27 134248](https://user-images.githubusercontent.com/81284265/170630504-6e227e32-e660-48a0-961c-2c11b9143b16.png)

- 마지막 : 데이터베이스 생성 클릭

# 포트 열어주기
- 첫번째 : springboot-database 클릭 > 연결 & 보안 > 보안 > VPC 보안 그룹 의 springboot-db-security 클릭 > 보안 그룹 ID 클릭  


![화면 캡처 2022-05-27 134451](https://user-images.githubusercontent.com/81284265/170630736-96964395-e04c-49f6-b558-0ed9875fee1b.png)  
- 두번째 : 인바운드 규칙 편집 클릭
- 세번째 : 소스 > 위치 무관 클릭해서 0.0.0.0/0, ::/0 생성 확인 후 "규칙 저장" 클릭!  3306포트번호도 입력


[화면 캡처 2022-05-27 134544](https://user-images.githubusercontent.com/81284265/170630809-8965041a-3224-4da5-b86f-4ca56aa94422.png)












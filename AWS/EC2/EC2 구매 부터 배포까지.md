 # EC2 구매하기
 [EC2 이동](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#Instances:)
 
 - 첫번째 : 이동한 사이트가 인스턴스관리 카테고리지만 혹시나 다른 곳으로 이동했다면 왼쪽 상단 검색에 EC2를 검색해서 인스턴스로 들어가 인스턴스 시작 버튼을 클릭한다.
 - 두번째 : 프리티어(1년간 정해진 범위안까지는 무료)로 진행하기 때문에 아래 설정 그대로가준다.  

 ![인스턴스 선택](https://user-images.githubusercontent.com/81284265/170616421-e12156bb-e6b1-4da9-a46e-a46f0a897f5d.png)

- 세번째 : 인스턴스 유형과 키페어 설정
> 유형도 프리티어부분을 선택해서 진행하면되고 키페어는 있다면 그 키페어를 사용하면 되지만 없다면 키페어 생성을 누르고 네번째로 넘어가서 보자   


![인스턴스 유형 및 키페어](https://user-images.githubusercontent.com/81284265/170616527-00248ca5-4361-4e28-ba2b-cf6ae87c5fd8.png)

- 네번째 : 키페어 생성
> 아래 사진 설정대로 해주고 키페어이름은 알아서 정해주면된다. 생성된 키페어는 인스턴스 서버에 접속하기위한 키라서 잘 챙겨두고 남에게 보여주면안된다. 해킹당한다.  

 ![키페어](https://user-images.githubusercontent.com/81284265/170616671-eb29a220-5114-46c7-b87f-954492090554.png)
 
 - 다섯번째 : 여기까지 설정이 되었다면 오른쪽 하단에서 중간사이에 인스턴스 시작버튼을 눌러 완료해주시면 된다.

 

# EC2 포트 열어주기
- 처음 인스턴스를 구매하면 외부에서 접근을 못한다. 포트를 열여줘야지 그 포트에 외부에서 접근이 가능하다.

- 첫번째 : 자신의 인스턴스를 클릭하면 하단에 보안탭에 들어가 보안그룹을 클릭한다.

![보안그룹](https://user-images.githubusercontent.com/81284265/170618340-ff9c5f39-e10d-4e79-bdc5-408c08cadb16.png)

- 두번째 : 인바운드 큐칙 편집을 클릭해 다음 정보를 입력한다.
> 주의 5000번 포트는 당시 5000포트를 사용하기때문에 5000포트를 열어준것이다. 자신이 사용하는 포트번호를 5000이라고 입력된 부분에 입력한다.   

![인바운드 규칙편집](https://user-images.githubusercontent.com/81284265/170618491-06a0a935-00d9-43ea-b637-77e1a47eea28.png)

- 세번째 : 완료되었다면 하단 왼쩍에 저장버튼을 클릭해 나온다.


# EC2 서버에 접속하고 기본프로그램 설치하기
> git bash를 이용  

- 첫번째 : 자신 서버로 ssh 접속하기
> 아래 명령어를 입력한다.  
```				 
ssh -i {ssh키페어 있는경로} ubuntu@{퍼블릭id}

ex)  ssh -i C:/Users/me/Desktop/aws/aws.pem ubuntu@10.34.678.236
```

- 두번째 : OpenJDK8 설치
> 나는 스프링프로젝트를 배포하기 때문에 자바를 설치할 것이다.
```
사용 가능한 패키지와 버전의 리스트를 업데이트(즉, 최신 버전이 있는지 확인하는 과정)
sudo apt-get update
```
```
설치 중 계속 진행할 것인지 물어보는데 'Y'를 입력하고 엔터를 누르면 계속 설치가 진행됩니다. 설치가 완료되면 JDK 버전을 확인하세요.
sudo apt-get install openjdk-8-jdk
```
```
JDK 버전 확인  버전이 확인이되면 설치가 완료된 것!
java -version
```

# -jar파일 배포하기
- 첫번째 : [파일질라 다운로드](https://filezilla-project.org/download.php)
- 두번째 : 파일질라에서 인스턴스 연결하기  
> new site 버튼 클릭 후 이름은 자유롭게  
> 프로토콜은 SFTP로 지정  
> 호스트는 퍼블릭아이디  
> 포트는 22  
> 사용자 unbunt
> 키파일 경로정해주고 연결 버튼 클릭  

![화면 캡처 2022-05-27 131851](https://user-images.githubusercontent.com/81284265/170628470-696eae0b-cc87-43ba-9834-6f6546ef7af2.png)  

- 네번째  : 연결이 완료되면 /home/ubuntu/ 경로안에 폴더 하나를 생성해주고 그 폴더에 로컬 사이트에서 리모트 사이트로 jar파일을 옯겨 넣어준다.

# jar 파일 실행시키기
- 첫번째 : git bash에서 ssh 연결해주고 파일질라에서 자신이 만들 폴더 경로 까지 이동한다. 보통 ubuntu 부터시작하기 때문에 명령어 는 아래와 같을 같은 것이다.
```
$ cd {자신이만든폴더명} 

ex)  cd temp
```
- 두번째 : jar 파일 실행 명령어 입력 그러면 자신의 애플리케이션이 실행되는것을 확인할 수 있을것이다.
```
$ java -jar {jar파일명}.jar
```

그 외 명령어
```
자신이 EC2에서 포트 5000번 또는 8080을 열어주었을 것이다. 
그러면 그 ip:8080 이나 5000을 입력해야 자신의 애플리케이션을 확인 할수 있는데 ip만 입력해도 작동하게해주는 명령어이다.

👉🏻 sudo iptables -t nat -A PREROUTING -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 5000

앱 유지
👉🏻 nohup java -jar {jar파일명}.jar
👉🏻nohup java -jar {jar파일명}.jar --spring.profiles.active=prod & 

앱 끄기
👉🏻ps -ef | grep '{jar파일명}' | awk '{print $2}' | xargs kill

포트 중복일경우 서버내리기
👉🏻 $ netstat -ntlp | grep :8080
//tcp  0  0 :::8080      :::*     LISTEN     23921/java
//23921: 포트번호 8080의 프로세스

👉🏻 $ kill -15 23921
```

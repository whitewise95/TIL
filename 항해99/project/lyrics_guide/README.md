# 가사 도우미

> 가사도우미 / 자신이 좋아하는 가사 한 줄을 공유하고 소통할 수 있는 공간
> - [와이어프레임 및 간단한 소개](https://whitewise95.tistory.com/66).
> - [프로젝트를 마치고 느낀점](https://github.com/whitewise95/TIL/tree/main/%ED%95%AD%ED%95%B499/1%EC%A3%BC%EC%B0%A8%20%EB%AF%B8%EB%8B%88%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8).


## 프로젝트 팀원 밑 역활
> 팀원
- 최봉규님 - 글을 쓰는 기능 , 삭제 , 수정  
- 박찬님 - 로그인과 회원가입
- 이현석님 - 댓글쓰기 , 삭제, 수정
- 백현명(나) - 메인페이지 , 글 디테일

>역활

|    |             기능                     |  method |    url            |                   request                                                            |   response                                    |
|:------:|:--------------------------------------:|:----:|:---------------------:|:-------------------------------------------------------------------------------------:|---------------------------------------|
| 백현명 | 분류를 클릭하면 그 분류에 맞는 글 조회 |  GET |   /main-page/<genre>  |                                genre = 각 보고싶은 장르                               |         분류에 맞는 글 데이터         |
|        |               전체글 조회              |  GET |   /main-page/<genre>  |                                      genre= 'all'                                     |             모든 글 데이터            |
|        | 디테일페이지이동(댓글정보,글정보 조회) |  GET |     /detail/<num>     |                                       num= 'num'                                      |    하나의 가사게시물과 댓글들 정보    |
|        |           글쓰기로 가는 기능           |  GET |     /lyrics/<num>     |                                      num = 'new'                                      |                                       |
|  박찬  |               로그인 화면              |  GET |           /           |                                                                                       |                                       |
|        |                 로그인                 | POST |        /sign-in       |            { 'username_give':username_give 'password_give':password_give }            | {'result': 'success', 'token': token} |
|        |                회원가입                | POST |     /sign-up/save     |            { 'username_give':username_give 'password_give':password_give }            |          {'result':'success'}         |
|        |               ID중복확인,              | POST |   /sign-up/check-dup  |                          { , 'username_give':username_give }                          | {'result':'success', 'exists':exists} |
| 최봉규 |               글쓰기 기능              | POST |         /board        | { lyrics : data , genre : genre, singer : singer , subject : subject , writer : id  } |          {'result':'success'}         |
|        |              글 삭제 기능              | POST |  /board/lyrics/<num>  |                                                                                       |          {'result':'success'}         |
|        |              글 수정 기능              | POST |     /board/lyrics     |   { lyrics : data , genre : genre, singer : singer , subject : subject , num : num }  |          {'result':'success'}         |
|        |    자신의 글이 맞는지 확인하는 기능    |  GET |  /board/lyrics/<num>  |                                                                                       |    {'result': 'fail', 'msg': msg.'}   |
| 이현석 |             댓글 쓰는 기능             | POST |  /detail/comment/new  |                             { writer : id comment: 댓글  }                            |          {'result':'success'}         |
|        |                댓글 삭제               | POST | /detail/comment/<num> |                                       url에 num                                       |          {'result':'success'}         |
|        |             댓글 수정 기능             | POST |    /detail/comment    |                      { comment : 업데이트된 글 num : 고유번호  }                      |          {'result':'success'}         |
|        |          좋아요 횟수 저장 기능         | POST |  /detail/comment/like |                       { comment : 업데이트된 글 num : 고유번호 }                      |          {'result':'success'}         |
  
  
## 개발환경
  
> 언어
- [python]().
  
> 프레임워크
- [flask 2.1.2]().
  
> css 
- [부트스트랩 5.0 cdn사용](https://getbootstrap.kr/).
- [구글폰트](https://fonts.google.com/?subset=korean).
  
> 템플릿엔진
- [jinja2 3.1.2]().
  
> 설치한 패키지
- [pymongo 4.1.1]().
- [PyJWT 2.3.0]().
- [dnspython 2.2.1]().
- [requests 2.27.1]().
  
> 서버환경
-  AWS EC2 (Ubuntu 18.04 LTS)
  
> 소스형상관리 및 툴 그리고 DB
- [소스트리](https://www.sourcetreeapp.com/).
- [github](https://github.com/whitewise95).
- [클라우드 MongoDB](https://account.mongodb.com/account/login?n=%2Fv2%2F62667bbc0b9a421d41bddc02&nextHash=%23metrics%2FreplicaSet%2F62667dba3b87b90e46457881%2Fexplorer%2Fdbsparta%2Fusers%2Ffind)
  
> 그 외  
- jQuery
- Ajax
  
  
## 중요한 기능구현 소개 
> 로그인
- [jwt토큰]() 을 이용하여 쿠키에 저장하는 방식으로 로그인연결 유지했다.

> html 화면단
- [jinja2 3.1.2]()를 이용한 [서버 렌더링 사이드방식]()을 이용하여 view단을 구성했다.
  
## 이후 추가하고싶은 기능
- 유저의 프로필 및 정보 수정
- 게시글에 사진도 추가할 수 있는 기능
- 가사 마디마다 색각을 각각 다르게해 또는 작성자 편의로 가사를 수정할 수 있는 기능
  






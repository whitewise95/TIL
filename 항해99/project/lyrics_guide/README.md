# 가사 도우미 [소스 다운로드](https://drive.google.com/file/d/1-ybW3psV_lkmppWUAOXjgVe2UNA4hTyd/view?usp=sharing)

> 가사도우미 / 자신이 좋아하는 가사 한 줄을 공유하고 소통할 수 있는 공간
> - [와이어프레임 및 간단한 소개](https://whitewise95.tistory.com/66).


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
-  AWS EC2 (Ubuntu 22.04 LTS)
  
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
  
## Comment

이 프로젝트는 4명이 한 조를 이룬다.  한번도 해본 적 없던 팀장의 역활을 맡아 사실 당황스러웠다.  서로 어색해서 프로젝트 주제선정이 원활하지 못했지만 그래도 팀장역활이 있으니 내가 좀 더 앞장서 프로젝트 설계에 적극적으로 팀원에게 질문하고 결정해 나갔다. 

나름 괜찮은 아이디어가 나왔고 와이어 프레임도 그럴 듯하게 만들어졌다.  

각자 기능구현을 분담했고 진행했다. 역활분담이 내가 팀원들에게 먼저 선택권을 주고 남은것을 가져갔는데 그것이 메인페이지 와 디테일 화면이다.  나는 웹개발은 살짝 하다가 이 프로젝트에 참여 했지만 다른 분들은 처음이라고한다.

그래서 처음 개발이 느렸지만 팀원들이 부족한 나를 잘 따라와주고 예민한 상황들도 많이 있었지만 서로 배려하며 이해와 설득그리고 겸손한 분들이라 프로젝트가 잘 마무리 되었다.

프로젝트 중에 기능을 잘 구현하지못하는 분 또는 에러를 대처를 못하시는 분들께 도움을 많이드렸는데 이 항해99 라는 프로그램의 목적이 스스로 해결하는 거라고 생각한다. 그부분에서 내가 해결해주고 알려주는게 좋은 판단이 맞았는지 의문이 들긴하다. 

그래도 처음엔 나도 마음이 급해서 에러나 기능구현 기술을 좀 알려주고 했지만 2일차부터 어느정도 진도가 있고 마감일 까지 여유가 있겠다 싶어서 팀원들께 이것은 이런방법으로 더 해보고 안되면 질문을 더주세요 라는 방식으로 하려고 노력을 했다.

솔직히 이 행동이....건방진 행동인거같다... 나도 내 코가 석자인데.. 누가 누굴 교육시키려하는지 ...ㅎㅎ 그래도 많은 경험을 한 4일이였다. 

나는 항상 두렵다 로컬환경에서 되던게 서버에 배포하고나면 보통 서버가 Linus 나 ubuntu인데 안되는 경우가 많았다. 이번에도 ㅎㅎ 그런 에러가 터져 당황스러웠지만 다행이 빠르게 해결되었다.

우리 프로젝트를 제출하기전 다른 조에서 프로젝트완료된게 slack에 올라왔다. 엄청 잘했다고 생각했다. 오히려 팀장 잘못만나 css 부분 디자인부분이 너무 다른 조에 비해 떨어진다고 생각했다. 

팀원들 정말 밤 낯 없이 열심히 하는데.... 남은 시간 2시간남았을 때 혼자서 css를 이것 저것 고쳤다... 그것이 후회가 된다.  우선 팀원들과 상의도 없이 그렇게 소스에 손을 댓다는 것 과 시간이 없을 때 이미 완성되었다고 한 소스를 건드려 뭔가 후회가 남는다는 것에 후회다.

많이 배운 이번 프로젝트 다음에는 좀 더 좋은 팀원이나 팀장이되어 힘이 되주고싶다. 




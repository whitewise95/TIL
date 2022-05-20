![화면 캡처 2022-05-15 193554](https://user-images.githubusercontent.com/81284265/168468530-69a63d7f-f5ab-42c9-95f9-560853933847.png)

## REST API (Representational State Transfer)란?
- 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미한다
> HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미한다.  
>> ![화면 캡처 2022-05-15 194017](https://user-images.githubusercontent.com/81284265/168468692-c18c4a6f-4c3c-446f-bb4a-0ec8d86cb19d.png)

## REST의 장단점
- 장점 
> 1. HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구출할 필요가 없다.  
> 2. HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.  
> 3. HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.  
> 4. Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.  
> 5. REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.  
> 6. 여러가지 서비스 디자인에서 생길 수 있는 문제를 최소화한다.  
> 7. 서버와 클라이언트의 역할을 명확하게 분리한다.  
- 단점
표준이 존재하지 않는다.
> 1. 사용할 수 있는 메소드가 4가지 밖에 없다.  
> 2. HTTP Method 형태가 제한적이다.  
> 3. 브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.  
> 4. 구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.  
>>> PUT, DELETE를 사용하지 못하는 점  
>>> pushState를 지원하지 않는 점  

## REST가 필요한 이유
- 애플리케이션 분리 및 통합
- 다양한 클라이언트의 등장
- 최근의 서버 프로그램은 다양한 브라우저와 안드로이폰, 아이폰과 같은 모바일 디바이스에서도 통신을 할 수 있어야 한다.
- 이러한 멀티 플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색한 결과, REST에 관심을 가지게 되었다.  

## REST 구성 요소
- 자원: URL
> 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.  
> 자원을 구별하는 ID는 ‘/groups/:group_id’와 같은 HTTP URI 다.  
> Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.  
- 행위: HTTP Method  
> HTTP 프로토콜의 Method를 사용한다.  
> HTTP 프로토콜은 GET,POST,PUT,DELETE와 같은 메소드를 제공한다.  
- 표현
> Client가 자원의 상태에 대환 조작을 요청하면 Server는 이에 적절한 응답을 보낸다.
> REST에서 하나의 자원은 JSON,XML,TEXT,RES등 여러 형태의 Representation으로 나타내어 질 수 있다.
> Json 혹은 XML를 통해 데이터를 주고 받는 것이 일반적이다.

## REST의 특징
- Uniform (유니폼 인터페이스)
> Uniform Interface는 URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍처 스타일을 말합니다.
- Stateless (무상태성)
> REST는 무상태성 성격을 갖습니다. 다시 말해 작업을 위한 상태정보를 따로 저장하고 관리하지 않습니다. 세션 정보나 쿠키정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 단순히 처리하면 됩니다. 때문에 서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순해집니다.
- Cacheable (캐시 가능)
> REST의 가장 큰 특징 중 하나는 HTTP라는 기존 웹표준을 그대로 사용하기 때문에, 웹에서 사용하는 기존 인프라를 그대로 활용이 가능합니다. 따라서 HTTP가 가진 캐싱 기능이 적용 가능합니다. HTTP 프로토콜 표준에서 사용하는 Last-Modified태그나 E-Tag를 이용하면 캐싱 구현이 가능합니다.
- Self-descriptiveness (자체 표현 구조)
> REST의 또 다른 큰 특징 중 하나는 REST API 메시지만 보고도 이를 쉽게 이해 할 수 있는 자체 표현 구조로 되어 있다는 것입니다.
- Client - Server 구조
> REST 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 직접 관리하는 구조로 각각의 역할이 확실히 구분되기 때문에 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어들게 됩니다.
- 계층형 구조
> REST 서버는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있고 PROXY, 게이트웨이 같은 네트워크 기반의 중간매체를 사용할 수 있게 합니다.

## REST API 디자인 가이드
- URI는 정보의 자원을 표현해야 한다.
- 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.

## URI 설계 시 주의할 점
- 슬래시 구분자(/)는 계층 관계를 나타내는 데 사용
- URI 마지막 문자로 슬래시(/)를 포함하지 않는다.
- 하이픈(-)은 URI 가독성을 높이는데 사용
- 밑줄(_)은 URI에 사용하지 않는다.
- URI 경로에는 소문자가 적합하다.
- 파일 확장자는 URI에 포함시키지 않는다.


## 읽어보면 좋은 블로그 및 출처
- [REST API 제대로 알고 사용하기](https://meetup.toast.com/posts/92)
- [REST API란?](https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html)
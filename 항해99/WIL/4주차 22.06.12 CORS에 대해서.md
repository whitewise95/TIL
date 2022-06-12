# 목차  

1) [CORS란?](#-cors란)
2) [CORS는 왜 필요한가요?](#-cors는-왜-필요한가요)
3) [CORS는 어떻게 동작하나요?](#-cors는-어떻게-동작하나요)
     - [simple-requests인-경우](#simple-requests인-경우)
     - [preflight 요청일 경우 ](#preflight-요청일-경우)

4) [출처는 어떻게 비교되나요?](#-출처는-어떻게-비교되나요)



<br>
<br>

# # CORS란?
Cross-Origin Resource Sharing 의 줄인말로 한국어로 직역하면 교차 출처 리소스 공유라고 해석할 수 있다.  
여기서 “교차 출처”라고 하는 것은 “다른 출처”를 의미하는 것인데, 다른 출처의 리소스 공유하는 것입니다.  
조금 더 쉬운 이해를 위해 교차 출처라는 말 대신 “다른 출처”라는 단어를 사용해서 정리 할 예정이다.  


<br>
<br>


# # CORS는 왜 필요한가요?
CORS가 없이 모든 곳에서 데이터를 요청할 수 있게 되면, 다른 사이트에서 원래 사이트를 흉내낼 수 있게 됩니다  
CORS가 없다면 아래와 같은 일이 일어나게 됩니다.  
```
ex) 
    **은행과 완전히 동일하게 동작하도록 만든 웹사이트, 가짜**은행을 이용자가 사용하도록 한다.
    가짜**은행을 이용하게 된다면 로그인했던 세션이나 중요한 정보를 탈취할 수 있게된다.
```


<br>
<br>

# # CORS는 어떻게 동작하나요?

### Simple requests인 경우
- 서버로 요청을 합니다.
- `서버의 요청`이 왔을 때 `브라우저가 요청한 Origin과 응답한 헤더 Access-Control-Request-Headers의 값을 비교`하여 `유효한 요청`이라면  
`리소스를 응답`합니다. 만약 `유효하지 않은 요청`이라면 `브라우저에서 이를 막고 에러가 발생`합니다.

### Simple requests란?
```
⭐️ HTTP method가 다음 중 하나이면서

GET
HEAD
POST
자동으로 설정되는 헤더는 제외하고, 설정할 수 있는 다음 헤더들만 변경하면서

Accept
Accept-Language
Content-Language
Content-Type이 다음과 같은 경우

application/x-www-form-urlencoded
multipart/form-data
text/plain
Simple requqets라고 부릅니다. 

이 요청은 추가적으로 확인하지 않고 바로 본 요청을 보냅니다.
```


<br>
<br>

### preflight 요청일 경우  

- `Origin헤더에 현재 요청하는 origin과, Access-Control-Request-Method헤더에 요청`하는  
`HTTP method와 Access-Control-Request-Headers요청 시 사용할 헤더를 OPTIONS 메서드로 서버로 요청`합니다. 
이때 `내용물은 없이 헤더만 전송`합니다.  

- `브라우저가 서버에서 응답한 헤더를 보고 유효한 요청인지 확인`합니다.  
만약 `유효하지 않은 요청이라면 요청은 중단되고 에러`가 발생합니다.   
만약 `유효한 요청이라면 원래 요청으로 보내려던 요청을 다시 요청하여 리소스를 응답`받습니다.  

### preflight 요청이란?
```
⭐️ Simple requests가 아닌 cross-origin요청은 모두 preflight 요청을 하게 되는데,
실제 요청을 보내는 것이 안전한지 확인하기 위해 먼저 OPTIONS 메서드를 사용하여 cross-origin HTTP 요청을 보냅니다. 
이렇게 하는 이유는 사용자 데이터에 영향을 미칠 수 있는 요청이므로 사전에 확인 후 본 요청을 보냅니다.

📍 요청 헤더 목록
* Origin

* Access-Control-Request-Method
     preflight 요청을 할 때 실제 요청에서 어떤 메서드를 사용할 것인지 서버에게 알리기 위해 사용됩니다.
     
* Access-Control-Request-Headers
     preflight요청을 할 때 실제 요청에서 어떤 header를 사용할 것인지 서버에게 알리기 위해 사용됩니다.
     
     
📍 응답 헤더 목록
* Access-Control-Allow-Origin
     브라우저가 해당 origin이 자원에 접근할 수 있도록 허용합니다. 
     혹은 *은 credentials이 없는 요청에 한해서 모든 origin에서 접근이 가능하도록 허용합니다.
     
* Access-Control-Expose-Headers
     브라우저가 액세스할 수 있는 서버 화이트리스트 헤더를 허용합니다.
     
* Access-Control-Max-Age
     얼마나 오랫동안 preflight요청이 캐싱 될 수 있는지를 나타낸다.
     
* Access-Control-Allow-Credentials
    Credentials가 true 일 때 요청에 대한 응답이 노출될 수 있는지를 나타냅니다.
    preflight요청에 대한 응답의 일부로 사용되는 경우 실제 자격 증명을 사용하여 실제 요청을 수행할 수 있는지를 나타냅니다.
    간단한 GET 요청은 preflight되지 않으므로 자격 증명이 있는 리소스를 요청하면 헤더가 리소스와 
    함께 반환되지 않으면 브라우저에서 응답을 무시하고 웹 콘텐츠로 반환하지 않습니다.
    
* Access-Control-Allow-Methods
    preflight`요청에 대한 대한 응답으로 허용되는 메서드들을 나타냅니다.
    
* Access-Control-Allow-Headers
    preflight요청에 대한 대한 응답으로 실제 요청 시 사용할 수 있는 HTTP 헤더를 나타냅니다.
```

<br>
<br>


# # 출처는 어떻게 비교되나요?  
출처는 아래 이미지에서 protocol + Host + Path를 합친것을 의미한다. 
![url](https://user-images.githubusercontent.com/81284265/173223279-a9306616-1bbe-4d55-b775-ae16a1f486a3.png)







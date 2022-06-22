# #목차
1) [인터셉터란?](#인터셉터란)
2) [왜 사용하는가?](#왜-사용하는가)
3) [Filter, Interceptor, AOP의 흐름](#filter,-interceptor,-aop의-흐름)
4) [예제](#예제)
5) [참고](#참고)

<br>
<br>
<br>

# #인터셉터란?

컨트롤러(Controller)의 '핸들러(Handler)'를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 일종의 필터  

<br>
<br>
<br>

# #왜 사용하는가?

개발자는 특정 Controller의 핸들러가 실행이 되기 전이나 후에 추가적인 작업을 원할 때 Interceptor를 사용합니다.

<br>
<br>
<br>

# #Filter, Interceptor, AOP의 흐름

1. 사용자는 서버에 자신이 원하는 작업을 요청하기 위해 url을 통해서 Request 객체를 보냅니다.  

2. '핸들러 맵핑(HandlerMapping)' 에게 사용자의 요청을 처리할 핸들러를 찾도록 요청합니다. 그 결과로 핸들러 실행 체인(HandlerExecutionChanin)이 동작하게 되는데, 이 핸들러 실행 체인은 하나 이상의 핸들러 인터셉터를 거쳐서 컨트롤러가 실행될 수 있도록 구성되어 있습니다.  

3. 핸들러 인터셉터를 등록하지 않았다면 곧바로 컨트롤러가 실행이 됩니다. 반대로 하나 이상의 인터셉터가 지정이 되어 있다면 지정된 순서에 따라서 인터셉터를 거쳐서 컨트롤러를 실행합니다.


![Request (요청)](https://user-images.githubusercontent.com/81284265/174918427-71102f6a-e160-4b8f-baa5-3c99ba5f6c91.png)


<br>
<br>
<br>

# #예제
1. WebMvcConfigurer를 implements한 클래스를 하나 생성해서 addInterceptors를 Override해준다.
```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()) // 앞으로  implements HandlerInterceptor 하는 클래스를 매개변수로 사용
                .addPathPatterns("/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .excludePathPatterns("/boards"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
}
```

<br>
<br>
<br>

2. 사용할 메소드를 재정의합니다.

  -  `PreHandle`(HttpServletRequest request, HttpServletResponse response, Object handler)  
컨트롤러에 진입하기 전에 실행됩니다. 반환 값이 true일 경우 컨트롤러로 진입하고 false일 경우 진입하지 않습니다.  
Object handler는 진입하려는 컨트롤러의 클래스 객체가 담겨있습니다.  

  - `PostHandle`(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  
컨트롤러 진입 후  View가 랜더링 되기 전에 수행됩니다.  

  - `afterComplete`(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)  
컨트롤러 진입 후 view가 랜더링 된 후에 실행되는 메소드입니다.  

  - `afterConcurrentHandlingStarted`(HttpServletRequest request, HttpServletResponse response, Object h)  
비동기 요청 시 PostHandle과 afterCompletion이 수행되지 않고 afterConcurrentHandlingStarted가 수행됩니다.   

```java
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        logger.info("[MYTEST] preHandle");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        logger.info("[MYTEST] postHandle");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception ex
    ) throws Exception {
        logger.info("[MYTEST] afterCompletion");
    }
}
```

<br>
<br>
<br>

# #참고
- [Bamdule](https://bamdule.tistory.com/149)
- [Kim VamPa](https://kimvampa.tistory.com/127)

# 스토리
> 문제발생 전
- 이미지를 업로드하는 기능을 추가했다.  
- /static/images 폴더 안에 이미지를 추가한다.  
- 리눅스 환경 서버에 배포  
> 문제발생  
- jar 파일로 올리기는 도중에 갑작이 식은 땀이 흘렀다.
- jar 파일이기에 resources/static/image 경로를 못찾는다.
- 아~ 새로운 경로에 만들고 절대 경로로 이미지를 띄어야지 생각해서 실행함
- 안됨
> 문제 해결
- 지인 2명에게 물어보기도 하고 이리저리 검색 해도 해결 못함 .... 그러다가 갑작이 자신이 쓴 이미지 업로드 코드인데 한번 참조해봐라 함
- 업로드 경로가 누가봐도 서버에 톰캣을 설치후 톰캣 경로에 업로드하는 코드네 ㅠㅠ....하다가 앗?! 나는 내장톰캣
- 내장톰캣, 업로드, 이미지, 경로 라는 키워드로 서치중 Spring Boot 프로젝트 외부 경로 파일 접근 이라는 글을 봄
- 결국 6시간만에 해결함

## 결론
- 결론은 실서버 운영시 이미지등의 파일 업로드의 경로는 웹 애플리케이션 외부의 별도의 공간으로 설정해야 한다는 것입니다.
```
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///home/ubuntu/upload/");
    }
}
```

👉addResourceHandler   
```
 : 리소스와 연결될 URL path를 지정합니다. (클라이언트가 파일에 접근하기 위해 요청하는 url)  
localhost:8080/upload/** 
```
👉 addResourceLocations  
```
   : 실제 리소스가 존재하는 외부 경로를 지정합니다.  
경로의 마지막은 반드시 " / "로 끝나야 하고, 로컬 디스크 경로일 경우 file:/// 접두어를 꼭 붙여야 합니다.  
 ```

이렇게 설정하면 클라이언트로부터 http://호스트 주소:포트/upload/testImage.jpg 와 같은 요청이 들어 왔을 때 /home/ubuntu/upload/testImage.jpg 파일로 연결됩니다.

 

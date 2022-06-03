

# My Blog  [src다운로드](https://drive.google.com/file/d/1Z_qjCLHaHg7VOgWbpKGjkhE1PaWGFpT6/view?usp=sharing)
## 간단한 글과 이미지를 업로드할 수 있는 웹 
![화면 캡처 2022-05-26 152101](https://user-images.githubusercontent.com/81284265/170429416-7c76d86e-a24b-4d21-bc06-0f5cf2d4cbc7.png)

## 개발 환경
> 언어
- [JAVA](https://github.com/whitewise95/TIL/tree/main/Java)
> 프레임워크
- [Spring](https://github.com/whitewise95/TIL/tree/main/Java/Spring)
- [JPA]()
> view 
- HTML5
> 라이브러리
- [SweetAlert2](https://github.com/whitewise95/TIL/blob/main/HTML/SweetAlert2.md)
- [twbspagination](https://github.com/whitewise95/TIL/blob/main/HTML/paging/twbspagination%EC%98%88%EC%A0%9C%20%EB%B0%8F%20%EC%98%B5%EC%85%98.md) 
> css  
- [부트스트랩 5.0 cdn사용](https://getbootstrap.kr/).
- [구글폰트](https://fonts.google.com/?subset=korean).  
> 서버환경
-  AWS EC2 (Ubuntu 22.04 LTS)
> DB
- AWS RDS(MySql)

> 그 외  
- jQuery
- Ajax  

## DB설계
| DB : blog | 타입            | 컬럼명          | 설명         |
|-----------|---------------|--------------|------------|
| NotNull   | Long (PK)     | id           | 글의 고유번호    |
| NotNull   | String        | content      | 글 내용       |
| NotNull   | String        | password     | 글 비밀번호     |
|           | String        | imgUrl       | 이미지path    |
| NotNull   | String        | writer       | 작성자        |
| NotNull   | LocalDateTime | createDate   | 글 작성 날짜    |
| NotNull   | LocalDateTime | modifiedDate | 최근 글 수정 날짜 |


## API정의서

| 기능                         | method | url                                                  | request | response |
|----------------------------|--------|------------------------------------------------------|---------|----------|
| 전체 게시글 목록 조회 API           | GET    | blog                                                 |         | 1타입      |
| 페이징 처리한 게시글 목록 조회 (Size 6) | GET    | blogByPage?size=6&page=${page}&sort=createDate,desc  |         | 1타입      |
| 게시글 작성 API                 | POST   | blog                                                 | 2타입     | id       |
| 게시글 수정 API                 | PUT    | blog/{id}                                            | 2타입     | id       |
| 게시글 삭제 API                 | DELETE | blog/{id}                                            | 3타입     | id       |


1타입
```
 {
    "createDate": "2022.05.26 04:21:49",
    "modifiedDate": "2022.05.26 04:21:49"
    "id": 96,
    "content": "먼 산 언저리 마다",
    "password": "1234",
    "imgUrl": "upload/9c08ec98d98e4281a5faea236e67a6a8.png",
    "writer": "백현명",
  },
```

2타입
```
 {
    "content": "먼 산 언저리 마다",
    "password": "1234",
    "imgUrl": "upload/9c08ec98d98e4281a5faea236e67a6a8.png", (필수 아님)
    "writer": "백현명"
  },
```
3타입
```
 {
    "password": "1234"
  },
```

## 프로젝트 구성
```
SPARTA_SPRING3
└─src
   └─main
      ├─java
      │  └─com
      │      └─sparta_spring
      │          └─sparta_spring3
      │              │  SpartaSpring3Application.java
      │              │
      │              ├─components
      │              │      Components.java
      │              │
      │              ├─controller
      │              │      BlogController.java
      │              │      CommonController.java
      │              │
      │              ├─domain
      │              │      Blog.java
      │              │      BlogRepository.java
      │              │      RequestBlogDto.java
      │              │      ResponseBlogDto.java
      │              │      Timestamped.java
      │              │
      │              ├─service
      │              │      BlogService.java
      │              │      CommonService.java
      │              │
      │              └─utiles
      │                      InitialComponents.java
      │                      WebConfig.java
      │
      └─resources
          │  application.properties(.gitIfnore)
          │  application.yml
          │
          ├─static
          │  │  index.html
          │  │
          │  ├─images
          │  │      no-image.png
          │  │
          │  └─js
          │          Gruntfile.js
          │          jquery.twbsPagination.js
          │          jquery.twbsPagination.min.js
          │
          └─templates
```


## HTML, Controller, Service, 
- 전형적인 CRUD 형태의 코드들로 이루어져있습니다.

## 라이브러리
⭐️  Paging 처리를 위한 twbspagination 를 사용
```
<div class="paging-div">
  <ul class="pagination" id="pagination">
    
  </ul>
</div>
```
```
  $('#pagination').twbsPagination({
      totalPages: totalPage, // 전체 페이지
      startPage: page, // 시작(현재) 페이지
      visiblePages: 10, // 최대로 보여줄 페이지
      prev: "‹", // Previous Button Label
      next: "›", // Next Button Label
      first: '«', // First Button Label
      last: '»', // Last Button Label
      onPageClick: function (event, page) { // Page Click event
		    	//클릭 이벤트
				console.log("클릭");
      }
  });
```

⭐️alert창을 이쁘게 하고싶어서 SweetAlert2 사용 
```
 Swal.fire("massage");
```

## Linux서버에 이미지 업로드를 위한 WebConfig 설정
⭐️ 전에 스프링부트가 아닌 아파치를 사용했기 때문에 이미지 업로드를 했을 경우 문제가 없었지만 스프링부트는 내장톰캣이기에 이 설정이 필요하다는 걸 삽질을 5시간정도 하고 알아냈다.
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

## yml을 이용해 데이터 주입을  InitialConponents.class 에서 관리
⭐️InitialConponents
```
package com.sparta_spring.sparta_spring3.utiles;

import com.sparta_spring.sparta_spring3.components.Components;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
public class InitialComponents {

    @Bean
    @ConfigurationProperties(prefix = "components")
    public Components components() {
        return new Components();
    }

}
``` 
⭐️ yml  - prod 환경과 local 환경을 나누어 파일업로드할 때 경로를 따로 설정해두었다.
```
components:
  slide: "\\"
  base-url: "\\src\\main\\resources\\static"
  src-url: "images"



---
spring:
  profiles: prod
components:
  slide: "/"
  base-url: "/home/ubuntu"
  src-url: "upload"
```
⭐️Components - 빈관리는  InitialConponents에서 하고있다.
```
package com.sparta_spring.sparta_spring3.components;

public class Components {
    private String baseUrl;
    private String slide;
    private String srcUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Components setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getSlide() {
        return slide;
    }

    public Components setSlide(String slide) {
        this.slide = slide;
        return this;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public Components setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
        return this;
    }
}
```

## CommonService 의 fileUpload() 
⭐️ 아마 내장톰켓 문제 때문에 제일 시간이 오래걸렸던 기능이다.
```
package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.components.Components;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommonService {

    public final Components components;

    public String fileUpload(MultipartFile attcFile) {
        String rtnVal = "";
        try {
            String storedFileName = makeFileName(attcFile);
            String folder = makeDir();

            File f = new File(folder);

            if (!f.exists()) {
                f.mkdirs();
            } //폴더가 존재하지 않으면 경로 생성

            attcFile.transferTo(new File(folder + components.getSlide() + storedFileName));
            rtnVal += components.getSrcUrl() + components.getSlide() + storedFileName;
        } catch (Exception e) {
            rtnVal = "";
        }
        return rtnVal;
    }

    public String makeDir() {
        String folder = "";
        if (components.getSlide().equals("\\")) {
            folder += new File("").getAbsolutePath();
        }
        folder += components.getBaseUrl() + components.getSlide() + components.getSrcUrl();
        return folder;
    }

    public String makeFileName(MultipartFile attcFile) {
        String attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
        String attcFileOriNm = attcFile.getOriginalFilename();
        String attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
        return attcFileNm + attcFileOriExt;
    }
}

```










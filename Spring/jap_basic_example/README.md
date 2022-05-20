# jap_basic_example
-  __프로젝트 간단한 __ 
```
프로젝트\SRC\MAIN
├─java
│  └─com
│      └─sparta
│          └─sparta_spring
│              │  SpartaSpringApplication.java
│              │
│              ├─controller
│              │      CourseController.java
│              │
│              ├─domain
│              │      Course.java
│              │      CourseRepository.java
│              │      CourseRequestDto.java
│              │      Timestamped.java
│              │
│              └─service
│                      CourseService.java
│
└─resources
    │  application.properties

```  

- __의존성__ (build.gradle)
> JSON  
> Spring web  
> Spring Data JPA  
> Lombok  
> MySql Driver  
> H2 DAtabase
```
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    //JSON
    implementation group: 'org.json', name: 'json', version: '20220320'
```  
- __properties__  
```
#h2 데이터베이스
spring.h2.console.enabled=true  // H2 데이터베이스의 데이터를 확인 할 수 있다.
spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL  // spring boot 2.1.10 버전 이후부턴 mode=mysql 명시해줘야 mysql 테이블 쿼리가 작동한다

# sql 보이도록 설정
spring.jpa.show-sql=true //인텔리제이 또는 자신이 쓴는 툴의 콘솔창에 쿼리가 어떻게 실행되었는지 확인이 가능하다.
```  
- __domain/*.java__
```
1. Course.java - DB와 직접적으로 닿는 Domain이면서 Timestamped.java를 상속받고 있다.
2. CourseRequestDto.java - 클라이언트에서 데이터를 받을 때 사용하는 DTO
3. CourseRepository.java(Impl) - DB와 연결을 도와주며 JpaRepository를 상속받는 인터페이스 
4. Timestamped.java(abstract) - 생성일자와 수정일자 변수가 들어가 있고 @MappedSuperclass 와 @EntityListeners(AuditingEntityListener.class) 를 사용해 자동으로 DB에 저장됨
                              - *Application.java 에 @EnableJpaAuditing // 수정일자 생성일자 자동으로 등록 할 수 있게 해준다. 애노테이션을 추가해줘야 한다.

```


- __Controller__
```
 GET(조회), POST(생성), PUT(수정), DELETE(삭제) Maping 에노테이션을 사용하여 각 메소드 기능 구분 
```
```
package com.sparta.sparta_spring.controller;

import com.sparta.sparta_spring.domain.*;
import com.sparta.sparta_spring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/api/courses")
    public Course createCourse(@RequestBody CourseRequestDto requestDto) {
        // requestDto 는, 생성 요청을 의미합니다.
        // 강의 정보를 만들기 위해서는 강의 제목과 튜터 이름이 필요하잖아요?
        // 그 정보를 가져오는 녀석입니다.

        // 저장하는 것은 Dto가 아니라 Course이니, Dto의 정보를 course에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Course course = new Course(requestDto);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return courseRepository.save(course);
    }

    @GetMapping("/api/courses")
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PutMapping("/api/courses/{id}")
    public Long updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto requestDto) {
        return courseService.update(id, requestDto);
    }

    @DeleteMapping("/api/courses/{id}")
    public Long deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return id;
    }
}
```

- __SpartaSpringApplication.java__
```
SpartaSpringApplication 클래스에 이미 main메소드를 실행하면 자동으로 데이터 하나가 들어가도록 했다.
포스트맨같은 프로그램을 사용하여 API만 쏘면 테스트가 
```







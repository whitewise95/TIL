# # 들어가는 글

이번에 프로젝트를 하면서 컨트롤러에서 데이터를 바인딩하는방법을 @RequestBody 및 @PathVariable만 주로 썼지만 다른 방법도 있다는 것도 공유하고싶었다

### # @RequestBody
- @RequestBody는 클라이언트 측에서 보낸 데이터를 Java 코드에서 사용할 수 있는 오브젝트로 만들어 받을 수 있습니다.
- HTTP 요청 본문(JSON 및 XML 등)을 Java 오브젝트로 변환하는 것입니다. HTTP 요청 본문 데이터는 Spring에서 제공하는 HttpMessageConverter를 통해 타입에 맞는 객체로 변환됩니다.
```
@PostMapping("/blog")
public Long blogWrite(@RequestBody RequestBlogDto blog) {
    return blogRepository.save(blog).getId();
}
```
```
@Getter
@Setter
@NoArgsConstructor
public class RequestBlogDto {
    private String content;
    private String writer;
    private String password;
    private String imgUrl;
}
```
```
📍 @RequestBody를 사용하면 요청 본문의 JSON, XML, Text 등의 데이터가 적합한 HttpMessageConverter를 통해 파싱되어 

Java 객체로 변환 된다 
   
@RequestBody를 사용할 객체는 필드를 바인딩할 생성자나 setter 메서드가 필요없다  다만 직렬화를 위해 기본 생성자는 필수다. 

또한 데이터 바인딩을 위한 필드명을 알아내기 위해 getter나 setter 중 1가지는 정의되어 있어야 한다.
```



### # @ModelAttribute
- @ModelAttribute 애너테이션의 역할은 클라이언트가 보내는 HTTP 파라미터들을 특정 Java Object에 바인딩(맵핑) 하는 것입니다.
- /modelattribute?name=req&age=1 같은 Query String 형태 혹은 요청 본문에 삽입되는 Form 형태의 데이터를 처리합니다.
```
@GetMapping("/modelattribute")
public ResponseEntity<ModelAttributeDto> testModelAttribute(@ModelAttribute ModelAttributeDto modelAttributeDto) {
    return ResponseEntity.ok(modelAttributeDto);
}
```
```
public class ModelAttributeDto {

    private String name;
    private long age;
    private String password;
    private String email;

    public ModelAttributeDto(String name, long age, String password, String email) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.email = email;
    }

    //Getter만 정의
}
```

```
📍 @ModelAttribute를 사용하면 HTTP 파라미터 데이터를 Java 객체에 맵핑한다. 

따라서 객체의 필드에 접근해 데이터를 바인딩할 수 있는 생성자 혹은 setter 메서드가 필요하다.

Query String 및 Form 형식이 아닌 데이터는 처리할 수 없다.


⭐️JSON 형태의 데이터를 전송하면 데이터가 바인딩되지 않거나 415 Unsupported Media Type 에러가 발생합니다.

⭐️contentType을 x-www-form-url-encoded로, 요청 본문 내용을 Form 형식으로 보내도록 테스트 진행하면 
  데이터가 잘 바인딩됨을 확인할 수 있습니다.
```

### # @RequestParam
- Http request의 content-type는 application/x-www-form-urlencoded 이다. 즉, Json으로 받지 못하다
- 보통 객체로 받지 않고 단독으로 받을 때 사용한다.
```
    @GetMapping("/hello")
    public String hello(@RequestParam String username,
                        @RequestParam int age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }
```

### # @RequestHeader
- Header로 데이터를 담아 보낼 경우 아래와 같이 어노테이션으로 받을 수 있다.
```
    @RequestMapping(method=RequestMethod.GET)
    public void getMethod(@RequestHeader("Test-Header") String value) {
        System.out.println("Test-Header=" + value);
    }
```
> curl으로 테스트하기
```
$ curl -H "Test-Header: hoge" http://localhost:8080/hello

Test-Header=hoge
```
### # @PathVariable
- uri에 데이터를 포합해 보내는 방식이다.
- 경로의 특정 위치 값이 고정되지 않고 달라질 때 사용한다.
```
@GetMapping("/test/{cnt}")
public int methodName(@PathVariable int cnt){
  // TODO..

  return cnt;
```

> 테스트
```
$ curl http://localhost:8080/test/1

1
```

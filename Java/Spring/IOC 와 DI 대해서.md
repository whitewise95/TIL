# 글을 쓰게 된 이유
- IOC와 DI는 처음 스프링을 배웠을 때 이해가 안되었지만 문제없이 사용하고 있어서 대충 알고 넘어갔다. 하지만 지금 다시 배우게 내용를 다우게 되었고 그 동안 스프링을 사용하는데 IOC 와 DI를 모르면 안되지 라는 말을 많이 들어가지고 이번에는 꼭 이해하고 넘어가고싶어서 내가 이해한 내용을 누군가 이해시킨다는 생각으로 정리해보았다.

# IoC(Inversion of Control, 제어의 역전)
- 스프링의 컨테이너가 필요에 따라 개발자 대신 생성하고 Bean들을 관리(제어)해주는 행위


# 예제로 알아보기

📍 아래 코드는 service의 더하기 라는, 숫자1 과 숫자2를 받아 콘솔에 합산값을 출력하는 메소드입니다. 특징을 살펴보겠습니다.  

![서비스로직](https://user-images.githubusercontent.com/81284265/170695405-b1f248f2-b676-4a14-ba2e-5d1984669768.png)


```
- 단독적으로 실행되지 않습니다. = controller에서 servic.더하기(숫자1, 숫자2) 로 실행할 수 있습니다.
- 매개변수 숫자1 과 숫자2는  service에서 직접만들지 않고  controller단에서 생성되어 넘겨줍니다.  
```

📍 controller단의 코드입니다.  
> 숫자1 과 숫자2 를 생성해서 service의 더하기 메소드에 넘겨주었습니다.  

![컨트롤러 21](https://user-images.githubusercontent.com/81284265/170693511-3de97a70-ce27-435c-9354-6a6cc5c285e2.png)  
```
service의 더하기 메소드에 숫자1 과 숫자2는 service에서 생성된게 아닌 controller에 더하기 메소드에서 생성된 것이라는게 증명이 되었습니다.
 ```

⭐️여기서 주목할 부분! 
- service의 더하기 메소드에 매개변수 숫자1과 숫자2는 controller에 더하기메소드가 생성해준 변수입니다.


# IOC 예제
>  이해를 돕기위해 생성자주입으로 진행했습니다.  

![화면 캡처 2022-05-27 210820](https://user-images.githubusercontent.com/81284265/170696397-6558b935-eb04-4a93-8f42-9e6e1a3ea5a1.png)


📍 여기서 질문! 
- 생성자 매개변수로 있는 BlogService 와 BlogRepository를 보면 더하기 예제에서 본 service의 더하기 메소드와 같이 어디선가 생성해준 객체를 받은것입니다. 그렇다면 BlogService 와 BlogRepository를
### 누가 생성을 해주었을까요? 
- 찾아보세요 만약 개발자가 직접 생성했다면 이런 코드가 있을 것입니다.
```
BlogService blogService = new BlogService();
BlogRepository blogRepository = new BlogRepository();

BlogController controller = new BlogController(blogService, blogRepository);
```

### 개발자가 만들어준 코드가 보이지 않는다면?!
⭐️ "스프링에서 생성해준 객체를 꺼내어 사용했다" 라는 말이 정답입니다.

# 그렇다면 어떻게 스프링이 생성해준거지?!
```
⭐️ 개발자가 스프링에게 이건 빈이야 라고 알려주면 스프링이 직접 그 객체를 생성해서 컨테이너라는 곳에 모아두고 필요할 때마다 꺼내어 줍니다.
    IoC(Inversion of Control, 제어의 역전)  스프링의 컨테이너가 필요에 따라 개발자 대신 생성하고 Bean들을 관리(제어)해주는 행위
```

# 어떻게 스프링에게 알려준거지?

![public static void main(String  args) { SpringApplication run(SpartaSpring3Application class, args); }](https://user-images.githubusercontent.com/81284265/170700036-c2471f1e-2b5b-498f-b2ff-9cba32b7ec86.png)

⭐️ 아래의 애노테이션으로 스프링에게 빈이라고 알려줄 수 있다.
```
@Component
@Repositoty
@Service
@Controller
@Configuration
```
# 빈으로 등록해서 생성됬었다면 어떻게 꺼내 쓰나요?
- 3가지 방법으로 주입할 수 있다. 이것을 바로 DI(의존 주입) 입니다.

⭐️ @Autowired 또는 @inject 를 사용해 필드에서 주입시키는 방법입니다.
```
@Autowired
private BlogRepository blogRepository;

@inject
private BlogService blogService;
```
⭐️ 생성자로 주입합니다.
```
public BlogController(BlogService blogService, BlogRepository blogRepository) {
    this.blogService = blogService;
    this.blogRepository = blogRepository;
}
``` 
⭐️ lombok을 사용한다면 롬복의 애노테이션을 이용
```
@RequiredArgsConstructor
```

# 왜 ioc를 사용할까? 
> 아래 의 장점들은 구글에 검색하면 나오는 여러가지 이유를 모았습니다.
- 객체 간 결합도를 낮춘다.
- 유연한 코드 작성이 가능하다.
- 가동성 증진
- 코드 중복 방지
- 유지 보수 용이
- 개발자는 객체 관리에 덜 신경쓸 수 있게 되어 다른 부분에 더 집중할 수 있게 됨

```
📍 혹시 이해가 가십니까?! 저는 이렇습니다.
  이해 간 부분 아래와 같습니다. 그 외에는 깊게 개발을 하지 못하거나 예제를 보지 못하면 이해하기가 어렵다고 생각합니다.
  
  1. 가독성 증진                                                               =   코드가 읽기 편해진다.
  2. 코드 중복 방지                                                            =    코드가 중복되지 않게 도와준다. <런타임 에러>
  3. 개발자는 객체 관리에 덜 신경쓸 수 있게 되어 다른 부분에 더 집중할 수 있게 됨   =    스프링이 자동으로 생성해주고 관리해주기 떼문에
```

# 나머지 장점을 이해 해보자
- 객체 간 결합도를 낮춘다.,  유연한 코드 작성이 가능하다. , 유지 보수 용이하다. 는 연쇄적인 장점인데요

👉🏻 memberUser1 메소드는 강한 결합 (개발자가 직접 new 생성자를 통해 주입해줬다.)  

👉🏻 memberUser2 메소드는 약한 결함 (스프링이 직접 생성해주고 컨테이너에서 꺼내와 주입)  

![화면 캡처 2022-05-27 214701](https://user-images.githubusercontent.com/81284265/170702096-d4d67f7d-9f10-4458-b259-b2c4ecb43ff2.png)

📍  자 여기서 Member 클래스에 생성자가 public 에서 private으로 변하면 어떻게 될까요?
- private으로 변경하자 바로 강한결합에는 빨간줄이 생겼습니다. 😱 
![화면 캡처 2022-05-27 215232](https://user-images.githubusercontent.com/81284265/170702890-806af88e-23d7-4874-8403-f7298d08f88c.png)

# 위 예제로 알 수 있는 것!
```
⭐️ 첫번째 : 의존주입(DI)은 약한 결합을 의미한다.  = 약한 결합도

   두번째 :  Member 클래스에 변화을 주었더니  강한 에 빨간줄이 떴습니다. ㅡ만약 Member를 new 하고있는 코드가 100만개라면? 
            100만개를 다 수정해줘야합니다.
              ㄴ 강한 결함 -  유지보수가 힘들다.
              ㄴ 약한 결함 -  유지보수가 쉽다.
              
   세번째 : 결합도가 낮아 빨간줄(컴파일 에러)가 적게 발생하므로 다른 코드들을 작성할 때 유연하게 코드를 작성할 수 있습니다.
              ㄴ 예제로 예를 들면 class Member의 생성자가 public이든 private이든 신경 쓸 필요없이(결합도가 낮아) 유연하게 대처할 수 있다.
   
```


# 정리
```
⭐️ IoC(Inversion of Control, 제어의 역전) - 스프링의 컨테이너가 필요에 따라 개발자 대신 생성하고 Bean들을 관리(제어)해주는 행위
   : 개발자가 빈이라고 스프링에게 알려주면 스프링이 개발자 대신해서 객체를 생성해주고 관리해준다!!
   
⭐️ IOC를 사용하는 이유(장점)

    객체 간 결합도를 낮춘다.
    : 직접 생성해준 객체가 아닌 스프링이 생성해준 객체를 사용하므로 결합도가 낮다. (이미 만들어진 객체를 필요할 때 사용하기에)
   
    유지 보수 용이,  유연한 코드 작성이 가능하다.
    : 결합도가 낮아 컴파일 에러 발생률이 적고 컴파일 에러에 자유도가 높아 유연하게 코드를 작성할 수 있다.

    
   개발자는 객체 관리에 덜 신경쓸 수 있게 되어 다른 부분에 더 집중할 수 있게 됨
   : 스프링의 컨테이너가 필요에 따라 개발자 대신 생성하고 Bean들을 관리해준다.
```









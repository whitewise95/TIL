# @Scheduled 이란?
- 주기적인 작업이 있을 때 @Scheduled 애노테이션을 사용하면 쉽게 적용할 수 있다.
> 특정시간 혹은 몇분 혹은 몇시간마다 동작해는 스케쥴러를 구현

## 사용 방법
- 스케쥴러를 적용하고 싶은 메소드에 @Scheduled 애노테이션을 명시해주면된다. fixedRateString과 initialDelay는 잠시 무시해도 좋다.
```
@Scheduled(fixedRateString = "5", initialDelay = 3000)
private void scheduleTest() { 
    logger.error("hello jeong-pro"); 
}
```
- application(main메소드클래스)에 @EnableScheduling 애노테이션으로 스케줄러를 사용한다고 알려준다.
```
@EnableScheduling 
@SpringBootApplication 
public class SchedulerApplication { 
    public static void main(String[] args) { 
        SpringApplication.run(SchedulerApplication.class, args); 
    } 
}
```

## 속성
- cron : 
```
cron표현식을 지원한다. "초 분 시 일 월 주 (년)"으로 표현한다. 이 글 하단에 예제 
```

- fixedDelay :
```
milliseconds 단위로, 이전 작업이 끝난 시점으로 부터 고정된 시간을 설정한다. ex) fixedDelay = 5000  

@Scheduled(fixedDelay=5000)
public void testScheduler(){
     System.out.println("스케줄링 테스트");
}
```

- fixedDelayString :
```
fixedDelay와 같은데 property의 value만 문자열로 넣는 것이다. ex) fixedDelay = "5000"  

@Scheduled(fixedDelay="5000")
public void testScheduler(){
        System.out.println("스케줄링 테스트");
}
```
- fixedRate : 
```
milliseconds 단위로, 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간을 설정한다. ex) fixedRate = 3000  

@Scheduled(fixedRate = 3000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
 }

```

- fixedRateString : 
```
fixedDelay와 같은데 property의 value만 문자열로 넣는 것이다. ex) fixedRate = "3000"

@Scheduled(fixedRate = "3000")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
 }
```
- initialDelay :
```
스케줄러에서 메서드가 등록되자마자 수행하는 것이 아닌 초기 지연시간을 설정하는 것이다.  

@Scheduled(fixedDelay = 3000,initialDelay = 1000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
}
```
- initialDelayString :
```
위와 마찬가지로 문자열로 값을 표현하겠다는 의미다. 
 
@Scheduled(fixedDelay = 3000,initialDelay = "1000")
  public void testScheduler(){
      System.out.println("스케줄링 테스트");
}
```
-  zone :
```
cron표현식을 사용했을 때 사용할 time zone으로 따로 설정하지 않으면 기본적으로 서버의 time zone이다. 
```




## cron 사용예제 
```
//1초 마다 실행
@Scheduled(fixedDelay=1000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//5초 마다 실행
@Scheduled(fixedDelay=5000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//10초 마다 실행
@Scheduled(fixedDelay=10000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//매일 5시에 실행
@Scheduled(cron="0 0 05 * * ?")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//매월 2일,20일 새벽2시에 실행
@Scheduled(cron="0 0 02 2,20 * ?")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//매월 마지막날 저녁 10시에 실행
@Scheduled(cron = "0 0 10 L * ?")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }

//1시간 마다 실행 ex) 01:00, 02:00, 03:00....
@Scheduled(cron = "0 0 0/1 * * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//매일 오후 18시마다 실행 ex) 18:00
@Scheduled(cron = "0 0 18 * * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }

//매일 오후 18시00분-18시55분 사이에 5분 간격으로 실행
@Scheduled(cron = "0 0/5 18 * * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }


//매일 오후 9시00분-9시55분, 18시00분-18시55분 사이에 5분 간격으로 실행
@Scheduled(cron = "0 0/5 9,18 * * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }

//매일 오후 9시00분-18시55분 사이에 5분 간격으로 실행
@Scheduled(cron = "0 0/5 9-18 * * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }

//매달 1일 00시에 실행
@Scheduled(cron = "0 0 0 1 * *")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");

    }

//매년 3월내 월-금요일 10시 30분에만 실행
@Scheduled(cron = "0 30 10 ? 3 MON-FRI")
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }
```

# 정보 얻은 출처
출처: https://jeong-pro.tistory.com/186 [기본기를 쌓는 정아마추어 코딩블로그]
출처: https://java119.tistory.com/34 [.java의 개발일기]

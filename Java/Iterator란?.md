# #Iterator란?

- `반복자` 라고 생각하면 쉽다. Collection 인터페이스를 구현하고 있는 클래스는 Iterator() 를 사용할 수 있습니다.  즉 `Iterator는` 자바의 컬렉션 프레임워크에서 `컬렉션에 저장되어 있는 요소들을 읽어오는 방법을 표준화`한 것이다.

<br>
<br>
<br>

![화면 캡처 2022-06-18 142650](https://user-images.githubusercontent.com/81284265/174424198-0c5fec9b-4f4f-4e78-97b9-a2fee5f856dc.png)![화면 캡처 2022-06-18 142816](https://user-images.githubusercontent.com/81284265/174424301-2787a892-ca40-4bb6-8c3d-5ff080789980.png)



# #기본 사용법
Iterator 메소드에는 hasNext(), next(), remove()가 있다.  
각각의 기능은 다음과 같다.  
``` 
hasNext() : 읽어올 요소가 남아있는지 확인하는 메소드이다. 요소가 있으면 true, 없으면 false  
next() : 다음 데이터를 반환한다.  
remove() : next()로 읽어온 요소를 삭제한다.  
```

``` java
class Scratch {
    public static void main(String[] args) {
        //ArrayList 생성
        List<Integer> test = new ArrayList<>();

        // 0~ 9 까지 데이터 넣어주기
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }

        //Iterator로 변환
        Iterator<Integer> is = test.iterator();


        // is.hasNext() 로 데이터가 있는지 확인하고 is.next()로 데이터를 꺼낼 수 있따.
        while (is.hasNext()) {
            int s = is.next();
            System.out.print(s + ", ");  // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        }

    }
}
```

# #remove() 에 대해서

위에서 remove()는 `next()로 읽어온 요소를 삭제한다.` 라고 설명했다.  
여기서는 주의할 점이 있다. #remove() 사용하여 요소를 제거하면 iterator의 타켓 Collection 요소에 영향을 준다는 것이다.  
즉, iterator는 요소를 복제한 것이 아니라 참조를 하고 있다.

### 확인 예제
```java
class Scratch {
    public static void main(String[] args) {
        //ArrayList 생성
        List<Integer> test = new ArrayList<>();

        // 0~ 9 까지 데이터 넣어주기
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }

        //Iterator로 변환
        Iterator<Integer> is = test.iterator();

        // is.hasNext() 로 데이터가 있는지 확인하고 is.next()로 데이터를 꺼낼 수 있따.
        while (is.hasNext()) {
            is.next();
            is.remove();    //요소를 지우고
            
            
            System.out.print(test.size()+ ", "); 
            //target이였던 test의 사이즈를 출력해보면 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 로 줄어든 것을 확인할 수있다.
        }

    }
}
```

### 메모지 주소 확인
메모지 주소값을 확인하기 위해 Object 타입으로 진행했다.   
출력 결과는 메모리 주소가 같기 때문에 iterator.remove()를 하면 결국 test 객체까지 영향을 준다라는게 증명되었습니다.  
```java
class Scratch {
    public static void main(String[] args) {
        List<Object> test = new ArrayList<>();

        Object o = new Object();
        test.add(o);

        Iterator<Object> is = test.iterator();

        System.out.println(test.get(0));  // java.lang.Object@50040f0c
        System.out.println(is.next());    // java.lang.Object@50040f0c
    }
}
```

# #Iterator vs for-each  

검색을 해보니 이러한 글들이 나왔다.  
```
차이점 1. For-each 반복문은 처음부터 끝까지 모두 탐색한다.
차이점 2. For-each문은 ConcurrentModificationException 예외가 발생할 수 있다.
```

그 외에도 `성능`, `가독성` 등의 이야기들이 있었지만...  

<br>

`가독성`은 개발자가 얼마나 그 `코드가 익숙하냐에 다름`이라 생각이들고  
성능도 현재는 하드웨어가 너무 좋아 `소프트웨어에서의 속도는 중요도는 낮아졌다고 한다. 100% 무시할 순 없다.` 하지만  
`성능차이가 차이도 많이 안나는 것 같다.` 나는 차이점 2 때문에 Iterator를 사용하게 된 계기였다.  
[ConcurrentModificationException](https://github.com/whitewise95/TIL/tree/main/Java/error) 대한 내용은 링크를 통해 확인할 수 있다.
















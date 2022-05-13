## 생성자란?
- 생성자는 인스턴스가 생성될 때 사용되는 '인스턴스 초기화 메소드'입니다
- 이 생성자를 이용해서 인스턴스가 생성될 때 수행할 동작을 코드로 짤 수 있는데요. 대표적으로 인스턴스 변수를 초기화 하는 용도로 사용합니다.  
## 생성자&nbsp;형식
```
클래스이름 (타입 변수명, 타입 변수명, ...){
    인스턴스 생성 될 때에 수행하여할 코드
    변수의 초기화 코드
}
```  
- 생성자에게도 생성자만의 조건이 있기 때문에 이 부분을 따라줘야합니다!
> 1.&nbsp; 생성자의 이름은 클래스명과 같아야 한다.  
> 2.&nbsp; 생성자는 리턴 값이 없다.

## 모든 클래스에는 반드시 하나 이상의 생성자가 있어야 합니다! 
- 어라? 우리는 만들지 않았는데 그럼 어떻게 오류가 나지 않았지? 라는 의문이 생기실 겁니다. 사실, 클래스에 생성자가 1개도 작성이 되어있지 않을 경우, 자바 컴파일러가 기본 생성자를 추가해주기 때문에 우리는 기본 생성자를 작성하지 않고도 편리하게 사용할 수 있습니다!

## 예제
- 지금 까지 썼던 방식
```
class Phone {
    String model;
    String color;
    int price;
}

public class Main {
    public static void main(String[] args) {
        Phone galaxy = new Phone();

        Phone iphone =new Phone();

        System.out.println("철수는 이번에 " + galaxy.model + galaxy.color + " + 색상을 " + galaxy.price + "만원에 샀다.");
        System.out.println("영희는 이번에 " + iphone.model + iphone.color + " + 색상을 " + iphone.price + "만원에 샀다.");
    }
}
```

- 생성자를 이용한 방식
```
class Phone {
    String model;
    String color;
    int price;

    Phone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        Phone galaxy = new Phone("Galaxy10", "Black", 100);

        Phone iphone =new Phone("iPhoneX", "Black", 200);

        System.out.println("철수는 이번에 " + galaxy.model + galaxy.color + " + 색상을 " + galaxy.price + "만원에 샀다.");
        System.out.println("영희는 이번에 " + iphone.model + iphone.color + " + 색상을 " + iphone.price + "만원에 샀다.");
    }
}
```


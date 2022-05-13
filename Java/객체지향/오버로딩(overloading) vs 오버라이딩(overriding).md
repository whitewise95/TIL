# 오버로딩이란? 
- 한 클래스 내에 동일한 이름의 메소드를 여러개 정의하는 것

## 오버로딩의 조건
- 메소드 이름이 동일해야 합니다.  
- 매개변수의 개수 혹은 타입이 달라야 합니다.

## 오버로딩 예제
```
int add(int x, int y, int z) {
    int result = x + y + z;
    return result;
}

long add(int a, int b, long c) {
    long result = a + b + c;
    return result;
}

int add(int a, int b) {
    int result = a + b;
    return result;
}
```  
  
## 잘못된 오버로딩
```
int add(int x, int y, int z) {
    int result = x + y + z;
    return result;
}

long add(int a, int b, int c) {
    long result = a + b + c;
    return result;
}
// 반환타입은 다르지만 매개변수의 자료형과 개수는 같기에 오버로딩이 아닙니다.
```  
  
  
  
  
# 오버라이딩이란?
- 부모 클래스로부터 상속받은 메소드의 내용을 변경하는 것
- 상속받은 메소드를 그대로 사용하기도 하지만, 필요에 의해 변경해야할 경우 오버라이딩을 합니다.
  
  
## 오버라이딩 조건
- 부모 클래스의 메소드와 이름이 같아야 합니다.
- 부모 클래스의 메소드와 매겨변수가 같아야 합니다.
- 부모 클래스의 메소드와 반환타입이 같아야 합니다.
  
  
## 오버라이딩 예제  
  
  
```
class Animal {
    String name;
    String color;

    public void cry() {
        System.out.println(name + " is crying.");
    }
}

class Dog extends Animal {

    Dog(String name) {
        this.name = name;
    }

    public void cry() {
        System.out.println(name + " is barking!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("코코");
        dog.cry();
    }
}
```  
## 오버로딩 vs 오버라이딩 (비교)
- 두 가지 개념을 혼동하기 쉽지만 차이는 명백히 존재합니다.
> 오버로딩 : 기존에 없는 새로운 메소드를 정의하는 것  
> 오버라이딩 : 상속받은 메소드의 내용을 변경하는 것



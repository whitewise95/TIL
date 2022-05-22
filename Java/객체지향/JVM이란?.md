#
# JVM,JRE,JDK 구별하기
![화면 캡처 2022-05-22 215419](https://user-images.githubusercontent.com/81284265/169696084-25ab8fed-be38-47fd-bd15-57addb152efa.png)
#
## *JVM* (Java Virtual Machine) 이란?
- Java Virtual Machine의 줄임말.  
- 직역하면 '자바를 실행하기 위한 가상 기계(컴퓨터)'라고 할 수 있다.  
- Java 는 OS에 종속적이지 않다는 특징을 가지고 있다.  
- OS에 종속받지 않고 실행되기 위해선 OS 위에서 Java 를 실행시킬 무언가가 필요하다.  
- 즉, ***OS에 종속받지 않고 CPU 가 Java를 인식, 실행할 수 있게 하는 가상 컴퓨터이다.***

![화면 캡처 2022-05-22 214843](https://user-images.githubusercontent.com/81284265/169695902-ffec671b-ae4a-410a-aa19-8a7af1e6ea8e.png)

```
Java 소스코드, 즉 원시코드(*.java)는 CPU가 인식을 하지 못하므로 기계어로 컴파일을 해줘야한다.

하지만 Java는 이 JVM 이라는 가상머신을 거쳐서 OS에 도달하기 때문에 OS가 인식할 수 있는 기계어로 바로 컴파일 되는게 아니라,

JVM이 인식할 수 있는 Java bytecode(*.class)로 변환된다. Java compiler 가 .java 파일을 .class 라는 Java bytecode로 변환한다.

javac 명령어를 통해 .java를 .class로 컴파일 할 수 있다. 변환된 bytecode는 기계어가 아니기 때문에 OS에서 바로 실행되지 않는다.

이 때, JVM이 OS가 bytecode를 이해할 수 있도록 해석해준다. 따라서 Byte Code는 JVM 위에서 OS 상관없이 실행될 수 있는 것이다.

OS에 종속적이지 않고, Java 파일 하나만 만들면 어느 디바이스든 JVM 위에서 실행할 수 있다.
```
#
# 바이트코드란 무엇인가
- 가상 컴퓨터(VM)에서 돌아가는 실행 프로그램을 위한 이진 표현법.
>자바 바이트 코드(Java bytecode)는 JVM이 이해할 수 있는 언어로 변환된 자바 소스코드를 의미한다. 자바 컴파일러에 의해 변환된 코드의 명령어 크기가 1바이트라서 자바 바이트 코드라고 불리고 있다. 바이트 코드는 다시 실시간 번역기 또는 JIT 컴파일러에 의해 바이너리 코드로 변환된다.
```
💡 바이너리 코드란?
바이너리 코드 또는 이진 코드라고 함
컴퓨터가 인식할 수 있는 0과 1로 구성된 이진코드
```

- 즉, CPU가 이해하는 언어는 바이너리 코드, 가상 머신이 이해하는 코드는 바이트 코드이다.


#
## *JVM* (Java Virtual Machine 자바 개발 키트)이란?
- Java 를 사용하기 위해 필요한 모든 기능을 갖춘 Java용 SDK (Software Development Kit)이다.  
- JDK 는 JRE를 포함하고 있다.  
- JRE + 개발에 필요한 툴로 구성됨  
- 즉, JDK는 프로그램을 생성, 실행, 컴파일할 수 있다.
#
## *JRE* (Java Runtime Enviroment 자바 런타임 환경)란?
- JVM + 자바 클래스 라이브러리(Java Class Library) 등으로 구성되어 있다.  
- 구성된 자바 어플리케이션을 실행 할 수 있도록 구성된 배포판이라 할 수 있다.(JVM의 실행 환경을 구축함)  
- 즉, 컴파일 된 Java 프로그램을 실행하는데 필요한 패키지이다.  

```
요약
JDK는 자바 프로그램을 실행, 컴파일, 개발용 도구.
JRE, JVM를 모두 포함하는 포괄적이 키트이다.
JRE는 자바 프로그램을 실행할 수 있게 하는 도구이다. JVM을 포함하고 있다.
```


[출처 : JVM이란? 개념 및 구조 (JDK, JRE, JIT, 가비지 콜렉터...)](https://doozi0316.tistory.com/entry/1%EC%A3%BC%EC%B0%A8-JVM%EC%9D%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EB%A9%B0-%EC%9E%90%EB%B0%94-%EC%BD%94%EB%93%9C%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%8B%A4%ED%96%89%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80)

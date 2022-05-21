## String  vs  StringBuffer/StringBuilder
- String과 StringBuffer/StringBuilder 클래스의 가장 큰 차이점은 String은 불변(immutable)의 속성을 갖는다는 점
- 아래 코드를 보면 str 변수는 world라는 문자열을 추가해 hellow world로 변한 것 처럼 보이지만 그림을 보면 hello 라는 데이터는 그대로 남아있고 hellow world 가 따로 메모리에 생성되고 str 변수가 hello world를 가리키게 된것이다.
```
String str = "hello";   // String str = new String("hello");
str = str + " world";  // [ hello world ]
```


![제목 없음](https://user-images.githubusercontent.com/81284265/169628113-de0e34aa-95d3-4c47-b8ca-ece677318a5f.png)

- 문자열 추가,수정,삭제 등의 연산이 비번하게 발생하는 알고리즘에서 String클래스를 계속 사용하면?
> 힙 메모리에 많은 임시 가비지가 생성되어 힙메모리가 부족으로 어플리케이션 성능에 치명적인 영향을 끼치게 됩니다.

## StringBuffer  vs  StringBuilder
- StringBuffer는 동기화 키워드를 지원하여 멀티쓰레드 환경에서 안전하다는 점(thread-safe) 입니다.  참고로 String도 불변성을 가지기때문에 마찬가지로  멀티쓰레드 환경에서의 안정성(thread-safe)을 가지고 있습니다.
- StringBuilder는 동기화를 지원하지 않기때문에 멀티쓰레드 환경에서 사용하는 것은 적합하지 않지만 동기화를 고려하지 않는 만큼 단일쓰레드에서의 성능은 StringBuffer 보다 뛰어납니다.

![제목 없음](https://user-images.githubusercontent.com/81284265/169628519-9e71b597-0fd1-4e2b-a4f8-021efb2a2de4.png)

##  StringBuffer, StringBuilder 주요 api
- [ String 클래스와 동일 메소드 ]  
 ```
charAt() - 특정 인덱스 위치의 문자 반환
indexOf() / lastIndexOf() - 문자열 검색해서 위치 반환
length() - 문자열 길이 반환
replace() - 검색된 문자열 교체
substring() - 특정 인덱스 범위 내 문자열을 복사해서 새로 생성된 인스턴스 반환
toString() - 문자열 출력
```

- [ append() ]
> 문자열 추가
```
StringBuilder a = new StringBuilder("Hello");
a.append(" World");
System.out.println(a); 		// "Hello World"	
```

- [ capacity() ]
> String 클래스와 다르게 char[] 배열 사이즈를 여유 있게 잡아둠  
> 현재 char[] 배열이 가진 사이즈 정보를 반환  
> length()는 실제 데이터가 들어있는 문자열 자체의 길이이고 capacity()는 현재 배열 사이즈  
> append() 등 문자열 조정할 때 배열 사이즈가 자동으로 변경됨  
```
		StringBuilder a = new StringBuilder("Hello");
		
		System.out.println(a.length());		// 5
		System.out.println(a.capacity()); 	// 21
		
		a.append(" World");
		System.out.println(a);             	// "Hello World"
		
		System.out.println(a.length()); 	// 11
		System.out.println(a.capacity()); 	// 21
		
		a.append(" Hi everybody!!!!");
		System.out.println(a);             	// Hello World Hi everybody!!!!

		System.out.println(a.length()); 	// 28
		System.out.println(a.capacity()); 	// 44		
```

- [ delete() ]
- 매개변수로 전달받은 인덱스 사이의 문자열 제거
```
		StringBuilder a = new StringBuilder("Hello");

		a.append(" World");
		System.out.println(a);  // "Hello World"
		
		a.delete(6, 9);         // (6~8 삭제)
		System.out.println(a);  // "Hello ld" 
```  

- [ deleteCharAt() ]
- 특정 인덱스의 한 문자만 삭제
```
		StringBuilder a = new StringBuilder("Hello");

		a.append(" World");
		System.out.println(a); 				// "Hello World"
		
		// a.delete(6,7) 과 같음
		a.deleteCharAt(6);
		System.out.println(a);				// "Hello orld"
```  
- [ insert() ]
```
		StringBuilder a = new StringBuilder("He World");
		
		a.insert(2, "llo");
		System.out.println(a);			// "Hello World"
		
		a.insert(5, 55);
		System.out.println(a); 			// "Hello55 World"
```


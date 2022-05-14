## 스택이란?(stack)
- 스택은 마지막에 저장한 데이터를 가장 먼저 꺼내는 자료구조로 입니다. 이것을  LIFO(Last In First Out) 라고 합니다.
- 웹브라우저의 앞페이지 이동 뒤페이지 이동 / 그릇 쌓기 대표예시입니다.
- 아래 그림을 보도록 하죠. 먼저 삽입된 값인 17이 가장 아래로, 이후 삽입되는 값은 그 위에 쌓이기 시작합니다. 이후, pop()을 통해 값을 반환할 때도 마지막에 삽입된 값인 45가 가장 먼저 반환되죠!   

![화면 캡처 2022-05-14 145807](https://user-images.githubusercontent.com/81284265/168412917-ded82850-df51-41e4-b47b-985c6bb1a309.png)
> 예제
```
public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        System.out.println(stack); // Stack을 출력합니다

        System.out.println(stack.peek()); // Stack의 가장 상단 값을 출력합니다.(삭제는 하지 않습니다.)
        stack.pop(); // Stack의 가장 상단 값을 제거합니다.
        System.out.println(stack);
        System.out.println(stack.size()); // Stack의 크기를 반환합니다.
        System.out.println(stack.contains(1)); // Stack에 1이라는 값이 있으면 true를, 그렇지 않으면 false를 반환합니다.
        System.out.println(stack.empty()); // STack이 비어있으면 true를, 그렇지 않으면 false를 반환합니다.
        System.out.println(stack);
    }
}
```  
## 큐란?(queue)
- 큐는 처음에 저장한 데이터를 가장 먼저 꺼내게 되는 FIFO(First In First Out) 구조로 되어있습니다.
- 은행 창구 줄서기 / 인쇄작업 대기목록 대표 예시입니다.
- 아래 그림을 통해 이해를 해보도록 하겠습니다! 큐는 양 쪽 끝의 통로가 뚫려있다고 생각하면 됩니다. 가장 먼저 들어온 Data가 반환이 될때도 가장 먼저 반환되는 것이죠!  
  
![](https://media.vlpt.us/images/dorazi/post/a4cace31-1ec0-4371-b3db-e2177f899ce5/queue-implementation.png)
> 예시
```
public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(3);
        queue.add(5);//Queue에 값 삽입합니다.
        System.out.println(queue);//Queue 출력합니다.
        System.out.println(queue.poll()); // Queue에서 객체를 꺼내서 반환합니다.
        queue.add(7);
        queue.add(11);
        queue.add(9);
        System.out.println(queue);
        System.out.println(queue.peek()); //Queue에서 삭제 없이 요소를 반환합니다.
        System.out.println(queue);
    }
}
```  
## ArrayDeque
-  실무에서는 단순히 Stack, Queue 클래스 대신에 ArrayDeque  많이 사용합니다! 기본 Stack, Queue의 기능을 모두 포함하면서도 성능이 더 좋기 때문이죠.
-  우리가 앞서 배운 큐는 한쪽에서만 값이 삽입되고 다른 한쪽에서만 값을 반환하는 자료구조였습니다. 하지만 deque의 경우 양 끝에서 삽입과 반환이 가능합니다. 
-  아래 사진은 deque 구조를 띄는 사진입니다. 정말 양 끝에서 삽입과 삭제가 이루어지고있죠? 우리가 예제로 확인해볼 ArrayDeque가 바로 이러한 형태입니다! 

![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlEYaM-7bPz3FcrZhXGxefr39mZsxyzE2euQ&usqp=CAU)
> 예제
```
public class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(); // ArrayDeque를 이용한 선언(제네릭스 이용)
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(4); // arrayDeque의 앞에 값을 삽입
        System.out.println(arrayDeque);

        arrayDeque.addLast(0); // arrayDeque의 끝에 값을 삽입
        System.out.println(arrayDeque);

        arrayDeque.offerFirst(10); // addFirst와 비슷하지만 큐의 크기 문제가 생길 때, offerFirst는 false를,
        // addFrist는 exception을 반환합니다.
        System.out.println(arrayDeque);

        arrayDeque.offerLast(-1); // arrayDeque의 끝에 값을 삽입
        System.out.println(arrayDeque);
        System.out.println(arrayDeque.size()); // 7


        System.out.println(arrayDeque.removeFirst()); // 첫번째 값을 제거하면서 그 값을 리턴
        System.out.println(arrayDeque.removeLast()); // 마지막 값을 제거하면서 그 값을 리턴
		    System.out.println(arrayDeque);
				System.out.println(arrayDeque.size()); // 5

        System.out.println(arrayDeque.pollFirst()); // 첫번째 값을 반환 및 제거하면서 그 값을 리턴
        System.out.println(arrayDeque);
				System.out.println(arrayDeque.size()); // 4

        System.out.println(arrayDeque.pollLast()); // 마지막 값을 반환 및 제거하면서 그 값을 리턴
        System.out.println(arrayDeque);
				System.out.println(arrayDeque.size()); // 3

        System.out.println(arrayDeque.peekFirst()); // 첫번째 값을 반환, 제거하지 않음
        System.out.println(arrayDeque.peekLast()); // 마지막 값을 반환, 제거하지 않음
				System.out.println(arrayDeque.size()); // 3
    }
}
```


# 가운데 글자 가져오기

## 문제설명
- 함수 solution은 정수 x와 자연수 n을 입력 받아, x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트를 리턴해야 합니다. 다음 제한 조건을 보고, 조건을 만족하는 함수, solution을 완성해주세요.

## 재한사항
- x는 -10000000 이상, 10000000 이하인 정수입니다.
- n은 1000 이하인 자연수입니다.  

## 입출력 예
```
x	  n	 answer
2 	5	 [2,4,6,8,10]
4  	3	 [4,8,12]
-4	2	 [-4, -8]
```

## Solution
- 드디어 전에 다른사람의 문제풀이를 응용해서 풀어볼 수 있는 문제가 생겼다. 
```
import java.util.stream.IntStream;
class Solution {
    public long[] solution(long x, int n) {
        long[] answer = new long[n];
        IntStream.range(0, n).forEach(index -> answer[index] = x * (index + 1));
        return answer;
    }
}
```

## 다른사람 문제풀이 중 맘에들었던 문제풀이
- 내가 좋아하는 코드 스타일이 간결함인데 1페이지 밖에 보지 못했지만 나보다 간결한 분들은 찾을 수 없어서 좋았다.



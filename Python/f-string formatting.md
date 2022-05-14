## f-string 포매팅이란?
- 우리가 문자열에서 특정 부분만 바꾸고 나머지 부분은 일정하다고 할때, 문자열 포매팅을 이용해서 이쁘게 출력 할 수 있습니다.
- f-string 포매팅은 파이썬 버전 3.6 부터 사용할 수 있는 따끈따끈한 기능 입니다.


## 예제  

```
month = 1
while month <= 12:
    print(f'2020년 {month}월')
    month = month + 1
```   


```   
s = 'coffee'
n = 5
result1 = f'저는 {s}를 좋아합니다. 하루 {n}잔 마셔요.'
print(result1)
```





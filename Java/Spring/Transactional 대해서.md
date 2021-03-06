# #목차
1) [Transactional 이란?](#-transactional-이란)
2) [Transactional 특징](#-transactional-특징)
3) [Transactional 설정](#-transactional-설정)
   - [설정1 : Propagation 메소드 레벨](#설정1--propagation-메소드-레벨)
   - [설정2 : isolation 격리 수준](#설정2--isolation-격리-수준)
   - [설정3 : timeout 트랜잭션타임](#설정3--timeout-트랜잭션타임)
   - [설정4 : readOnly 읽기전용](#설정4--readonly-읽기전용)
   - [설정5 : noRollbackFor](#설정5--norollbackfor)

4) [참고](#참고)


<br>
<br>
<br>

# # Transactional 이란?
트랜잭션은 `우리말로 ‘거래’라는 뜻`을 갖고 있으며, 더 이상 쪼갤 수 없는 최소단위의 작업 또는 모두 저장되거나, 아무 것도 저장되지 않거나를 보장한다.

Transactional을 왜 사용하는지 먼저 간단한 예시를 들어보겠다.

🔍 아래 표와 같이 FAMILY라는 테이블에 데이터가 저장되어있고 조회를 할 경우 정확한 데이터가 출력이 되어야합니다.

| id | name | age |
|----|------|-----|
| 1  | 나    | 28  |
| 2  | 아빠   | 55  |
| 3  | 엄마   | 50  |
| 4  | 남동생  | 26  |
| 5  | 여동생  | 25  |
| 6  | 할아버지 | 96  |

🔍 돈거래로 비유를 하면 A씨의 계좌에는 1000원이 있고 B씨 계좌에는 0원이 있다고 가정하자
>  A씨가 B씨에게 1000원을 계좌이체를 했고 B는 1000원을 출금하는 상황이다.
- 아래 이미지와 같이 `이체과정에서 실패`를 했지만 `B씨는 1000원을 출금하여 돈이 복사된` 문제이다. 물론 돈이 생긴 입장에서는 기분이 좋겠지만 나중에 `개발한 개발자는 돈을 물어내야하는 상황`이 온다.  

이런 일이 발생하지 하도록 즉, `조회, 수정, 작성, 삭제가 정확`하게 이루어지는 것을 `보장하기위해 Transactional를 사용`한다.

![A씨](https://user-images.githubusercontent.com/81284265/174919319-47d34104-2750-479c-9a81-cd6f00c42bd2.png)

<br>
<br>
<br>

# # Transactional 특징
트랜잭션의 특징은 크게 4가지로 구분된고 이것을 ACID  
`원자성 (Atomicity` ,   `일관성(Consistency)`,   `독립성(Isolation)`, `지속성(Durability)` 이라고 부른다.


- `원자성` 은 트랜잭션이 데이터베이스에 모두 반영되던가, 아니면 전혀 반영되지 않아야 한다는 것이다.  
 > Transactional은 절대 쪼개지지 않은 단위로 A씨가 B씨에게 이체하고 B씨 통장에 이체한 금액이 있는 것까지가 하나의 단위이므로 이체 하고 이체한 금액이 통장에 입금하는 기능중 하나라도 실패했다면 반영되지 않아야 한다는 것이다


 <br>
 
- `일관성` 은 트랜잭션의 작업 처리 결과가 항상 일관성이 있어야 한다는 것이다.   
> 트랜잭션이 진행되는 동안에 데이터베이스가 변경 되더라도 업데이트된 데이터베이스로 트랜잭션이 진행되는것이 아니라, 처음에 트랜잭션을 진행 하기 위해 참조한 데이터베이스로 진행된다. 이렇게 함으로써 각 사용자는 일관성 있는 데이터를 볼 수 있는 것이다.
 <br>
 
- `독립성` 은 둘 이상의 트랜잭션이 동시에 실행되고 있을 경우 어떤 하나의 트랜잭션이라도, 다른 트랜잭션의 연산에 끼어들 수 없다는 점을 가리킨다.  

 <br>
 
- `지속성` 은 트랜잭션이 성공적으로 완료됬을 경우, 결과는 영구적으로 반영되어야 한다는 점이다.  


<br>
<br>
<br>

# # Transactional 설정
모든 작업에 독립성과 일관성을 보장해 하나씩 순차적으로 진행하게 된다면 성능 저하가 온다. 
그래서 `Transactional은 설정이 정말 중요`하다.

<br>
<br>

## #설정1 : Propagation 메소드 레벨

![화면 캡처 2022-06-22 101346](https://user-images.githubusercontent.com/81284265/174922683-8d1e17c8-8707-4131-b577-2dd68f73fd8c.png)  

### 📍Propagation.REQUIRED  
```
Transactional의 default 값이며 새로운 Transaction이 없으면 메소드1이 Transaction1 로 되고 
새로운 Transaction2 가 생긴다면 Transaction2는 Transaction1에 참여하게 됩니다.
즉, 두 메소드가 하나의 Transaction로 실행되기 때문에 어느 하나에서 문제가 발생해도 실행했던 메소드가 롤백됩니다.
```  

### 📍 Propagation.NOT_SUPPORT  
```
REQUIRED 처럼 실행된 Transaction이 있으면 참여하고 없다면 단독으로 실행됩니다.
```
### 📍 Propagation.MANDATORY  
```
진행되고 있는 Transaction이 있으면 참여하고 없으면 예외가 발생합니다. 즉, 혼자서는 실행할 수 없다.
```
### 📍 Propagation.REQUIRES_NEW
```
항상 새로운 Transaction을 실행 시킨다. 트랜잭션1이 실행도중 메소드2가 실행되었다면 
트랜잭션1를 잠시 보류하고 트랜잭션2가 실행된다.
```
### 📍 Propagation.NOT_SUPPORT
```
트랜잭션1 실행도중 메소드2가 실행되면 실행중이였던 트랜잭션1의 메소드는 
트랜잭션을 사용하지 않고 진행되며 메소드2가 트랜잭션2를 실행한다.
즉, 기존에 사용중인 메소드1는 트랜잭션을 사용하지 않는 설정입니다.
```

### 📍 Propagation.NEVER
```
트랜잭션을 사용하지 않도록 강제합니다. 또는 이미 진행중인 트랜잭션이 없다면
자신메소드를 실행하지만 이미 진행중인 트랜잭션이 있다면 예외가 발생한다.
```

### 📍 Propagation.NESTED
```
트랜잭션1이 실행도중 메소드2가 실행되었다면 트랜잭션1 안에 트랜잭션2가 삽입됩니다.
트랜잭션1에서 롤백과 커밋이 되었다면 트랜잭션2는 영향을 받지만 트랜잭션2에서 발생한 커밋과
롤백은 트랜잭션1에서 영향을 받지 않습니다.
```

<br>
<br>



## #설정2 : isolation 격리 수준
![화면 캡처 2022-06-22 101405](https://user-images.githubusercontent.com/81284265/174984791-b4d513c7-469d-4fcd-ba2a-080faf62eb09.png)

### 📍 READ_UNCOMMITTED
```
제일 낮은 격리 수준을 트랜잭션1 에서 아직 커밋되지 않은 내용을 트랜잭션2에서  읽을 수 있는 것입니다.
```

### 📍 READ_COMMITTED
```
트랜잭션1 에서 커밋되지 않은 정보는 트랜잭션2에서 읽을 수 없습니다.
```

### 📍 REPETABLE_READ
```
트랜잭션1에서 읽고 있는 내용을 트랜잭션2에서 수정할 수 없습니다.
```
### 📍 SERIALIZABLE
```
최고 레벨로 모든 트랜잭션이 동시에 같은 테이블에 접근할 수 없습니다.
```

<br>
<br>


## #설정3 : timeout 트랜잭션타임
초단위로 설정을 하며 설정한 시간이 되면 예외가 발생해 롤백됩니다.  

![화면 캡처 2022-06-22 174224](https://user-images.githubusercontent.com/81284265/174984933-cde5625d-c73e-4254-83e2-3b7c7be1310c.png)

<br>
<br>


## #설정4 : readOnly 읽기전용
false , true로 설정하며 기본값은 false 입니다. true로 설정하면 설정한 메소드 안에서 C,R,D가 일어나는 것을 방지합니다.  
또한 flush모드가 manual로 설정되어 jpa의 더티체킹 기능을 무시할 수 있습니다. 즉, 성능이 향상됩니다.  

![화면 캡처 2022-06-22 174418](https://user-images.githubusercontent.com/81284265/174985294-bdd22b49-0a0d-41a0-9b05-76904e068023.png)

<br>
<br>


## #설정5 : noRollbackFor 
아래 이미지와 같이 설정되면 설정된 Exception에 대해서는 롤백이 되지않고 커밋을 처리합니다.  
![화면 캡처 2022-06-22 174418](https://user-images.githubusercontent.com/81284265/174986322-0d2aa02c-b71c-431b-81c1-dc560f59d519.png)

<br>
<br>

# #참고
- [기록은 기억의 연장선](https://joont92.github.io/db/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B2%A9%EB%A6%AC-%EC%88%98%EC%A4%80-isolation-level/)
- [[10분 테코톡] 🐤 샐리의 트랜잭션](https://www.youtube.com/watch?v=aX9c7z9l_u8)
- [코린이의 개발노트](https://n1tjrgns.tistory.com/266)
- [개발자로 홀로 서기](https://mommoo.tistory.com/62)

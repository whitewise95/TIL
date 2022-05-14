## List
- ArrayList는 배열을 이용하여 데이터를 저장하는 List 인터페이스입니다
- 순서가 있는 나열된 데이터를 표현합니다.
> 예제
```
public class Main {
    public static void main(String[] args) {
        List list = new ArrayList(10);
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(11);
        list.add(10); // ArrayList에 값 한개씩 입력
        System.out.println(list); // [1,5,4,11,10]

        Collections.sort(list); // list 정렬
        System.out.println(list); // [1,4,5,10,11]

        System.out.println(list.size()); // arrayList의 크기 출력

        arrayList.remove(4); // 인덱스를 활용하여 해당하는 값 제거
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i)); // get을 이용하여 값 1개씩 출력
        }
				for (int current : list) {
						System.out.println(current);
        }

    }
}
```  

## Set
- 순서를 유지하지 않는 데이터의 집합이며 데이터의 중복을 허용하지 않습니다.
- HashSet은 Set 인터페이스를 구현한 대표적인 컬렉션입니다.
> 
```
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> integerSet = new HashSet<>(); // Collection의 자료형에는 primitive 타입은 올 수 없습니다. primitive 타입에 해당하는 class 가 존재하니 그것을 사용하세요.
        integerSet.add(1);
        integerSet.add(3);
        integerSet.add(2);
        integerSet.add(9);// 하나씩 값을 삽입합니다.
        System.out.println(integerSet); // 출력을 해보면 순서가 지켜지지 않는 것을 알 수 있습니다.

        Set<String> stringSet = new HashSet<>();
        stringSet.add("LA");
        stringSet.add("New York");
        stringSet.add("LasVegas");
        stringSet.add("San Francisco");
        stringSet.add("Seoul");
        System.out.println(stringSet);

        stringSet.remove("Seoul"); //Seoul을 HashSet에서 제거해보겠습니다.
        System.out.println(stringSet);

        ArrayList<String> target = new ArrayList<String>();
        target.add("New York");
        target.add("LasVegas");//제거할 항목을 ArrayList에 삽입하겠습니다.
        stringSet.removeAll(target);//제거항목에 삽입된 도시들을 삭제하겠습니다.
        System.out.println(stringSet);

        System.out.println("LA가 포함되어있나요? " + stringSet.contains("LA"));
        System.out.println("LA가 포함되어있나요? " + stringSet.contains("LasVegas"));
        //LA가 HashSet에 포함되어있으면 true를, 그렇지 않으면 false를 반환합니다.

        System.out.println("현재 HashSet의 크기는 : " + stringSet.size() + "입니다.");
        //HashSet의 크기를 반환합니다.

        stringSet.clear();//HashSet의 모든 아이템들을 삭제합니다.
        System.out.println(stringSet);
    }
}
```  

## Map
- HashMap은 키(key)와 값(value)을 하나의 데이터로 저장하는 특징을 가집니다. 이를 통하여 해싱(hashing)을 가능하게 하여 데이터를 검색하는데 뛰어난 성능을 보입니다.
> 예제
```
public class Main {
    public static void main(String[] args) {
				Map<Integer, String> map = new HashMap<>();
        map.put(1, "apple");
        map.put(2, "berry");
        map.put(3, "cherry");

        System.out.println(map);

        System.out.println("1st in map: " + map.get(1));

        map.remove(2);
        System.out.println(map);
        System.out.println(map.containsKey(2));
        System.out.println(map.containsValue("cherry"));
        
        map.clear();
        System.out.println(map);
    }
}
```



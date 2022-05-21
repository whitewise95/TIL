# 자바에서 JSON 파싱하기 
> 첫번째 : JSONObject, JSONArray 이용하기  

## JSONObject, JSONArray 이용하기
- restTeamplate를 이용한 데이터를 받았을 경우 
```
// 데이터를 받은 ResponseEntity타입의 객체를 getBody() 이용하여 String 객체에 대입한다.
String result = responseEntity.getBody();

// result데이터를 JSONObject 객체로 변환한다.
JSONObject rjson = new JSONObject(result);

// JSONObject 객체는 Key : value 형태이기 때문에 .getJSONArray("key") 로 값을 확인 할 수 있다. 
JSONArray items  = rjson.getJSONArray("items");

List<ItemDto> ret = new ArrayList<>();

// JSONArray객체로 itemDto를 생성해 ret에 하나씩 add한다.
for (int i=0; i<items.length(); i++) {
    JSONObject itemJson = items.getJSONObject(i);
    System.out.println(itemJson);
    ItemDto itemDto = new ItemDto(itemJson);
    ret.add(itemDto);
}

```

## 중요한 점
- JSON으로 변환하고 파싱하고 바인딩하는과정에서는 데이터가 어떤지부터 파악해야한다.





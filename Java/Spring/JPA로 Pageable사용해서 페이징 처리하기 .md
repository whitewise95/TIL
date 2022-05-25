# 목적
- HTML단에 페이징처리를 하고싶은데 서버에서 JPA와 Pageable를 이용해 간단하게 데이터를 가공할 수 있다고 해서 정리를 해보았다.

# Controller
- Pageable를 파라메타로 선언해준다.
```
@GetMapping("/blog")
public Page<Blog> blogFindAll(Pageable pageable) {
    return blogRepository.findAll(pageable);
}
```

# Ajax
- size : 한 페이지 당 조회 할 갯수 (default : 20)
- page : 요청할 페이지 번호
- sort : orderBy할 변수를 입력  "," 로 구분하여 desc 인지 asc 인지 구분
```
 $.ajax({
            type: "GET",
            url: "/blog?size=6&page="+0+"&sort=createDate,desc",
            success: function (response) {
            }
        })
```


# Request

![화면 캡처 2022-05-25 104646](https://user-images.githubusercontent.com/81284265/170161438-c2643c2f-5b4a-425c-b9bc-c0c006848195.png)

# Response
- content - 불러온 DB 데이터 
- 그외 - 전부 Pageable 데
![화면 캡처 2022-05-25 105042](https://user-images.githubusercontent.com/81284265/170162110-20374833-8267-4279-bdf2-eff706f3a68e.png)


# 같이 활용할 html js
[페이징](https://github.com/whitewise95/TIL/tree/main/HTML/paging)

# 목적
- 항상 이미 페이징이 적용된 프로젝트에 투입되어서 직접 적용시켜본적이 없어 직접해보고싶은 생각도 있었다.

# 플러그인 다운로드
[jQuery Pagination plugin](http://josecebe.github.io/twbs-pagination/#demo)

#필요한 라이브러리 위 플러그인 다운로드 포함
```
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/twbs-pagination/jquery.twbsPagination.js"></script>
```

# html 
```
<div class="paging-div">
  <ul class="pagination" id="pagination">
    
  </ul>
</div>
```

# js 자주 쓰는 옵션 정리

- 페이징UI 완성
> 주의할점은 이 함수가 실행되면 onPageClick가 바로실행된다.
```
$('#pagination').twbsPagination({
		    totalPages: 35,	// 총 페이지 번호 수
		    visiblePages: 7,	// 하단에서 한번에 보여지는 페이지 번호 수
		    startPage : 1, // 시작시 표시되는 현재 페이지
		    initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
		    first : "첫 페이지",	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
		    prev : "이전 페이지",	// 이전 페이지 버튼에 쓰여있는 텍스트
		    next : "다음 페이지",	// 다음 페이지 버튼에 쓰여있는 텍스트
		    last : "마지막 페이지",	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
		    nextClass : "page-item next",	// 이전 페이지 CSS class
		    prevClass : "page-item prev",	// 다음 페이지 CSS class
		    lastClass : "page-item last",	// 마지막 페이지 CSS calss
		    firstClass : "page-item first",	// 첫 페이지 CSS class
		    pageClass : "page-item",	// 페이지 버튼의 CSS class
		    activeClass : "active",	// 클릭된 페이지 버튼의 CSS class
		    disabledClass : "disabled",	// 클릭 안된 페이지 버튼의 CSS class
		    anchorClass : "page-link",	//버튼 안의 앵커에 대한 CSS class
		    
		    onPageClick: function (event, page) {
		    	//클릭 이벤트
				console.log("클릭");
		    }
		});
```
# OR
```
  $('#pagination').twbsPagination({
      totalPages: totalPage, // 전체 페이지
      startPage: page, // 시작(현재) 페이지
      visiblePages: 10, // 최대로 보여줄 페이지
      prev: "‹", // Previous Button Label
      next: "›", // Next Button Label
      first: '«', // First Button Label
      last: '»', // Last Button Label
      onPageClick: function (event, page) { // Page Click event
		    	//클릭 이벤트
				console.log("클릭");
      }
  });
```

- 페이지 네이션 버튼 활성화 / 비활성화
```
// 페이지 버튼 클릭 가능
$("#pagination").twbsPagination('enable');

// 페이지 버튼 클릭 불가능
$("#pagination").twbsPagination('disable');
```

- 그외 
```
// 전체 페이지 개수
$("#pagination-div").twbsPagination("getTotalPages");

// 현재 선택된 페이지 번호
$("#pagination-div").twbsPagination("getCurrentPage");


// 페이지 네이션 총 페이지 숫자 변경
// 이 함수가 호출 되면 변경되고 난 후 선택될 페이지 번호에 대한 클릭이벤트가 발생이 되는 점에 유의
$("#pagination-div").twbsPagination("changeTotalPages", 총 페이지, 변경되고 난 후 선택될 페이지 번호);
```






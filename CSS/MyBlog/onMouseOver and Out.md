-html
```
<div class="col" id="cardBox" onmouseover="mouseover(this, ${item.id})" onmouseout="mouseout(this, ${item.id})">
  <div style="position:absolute; top:31%; left:31%; display: none;" id="${item.id}-see">
      <button class="btn btn-outline-secondary" onclick="">수정</button>
      <button class="btn btn-outline-danger" onclick="">삭제</button>
  </div>
</div>  
```

- js
```
    function mouseover(target, id) {
        let see = "#" + id + "-see";
        let img = "#" + id + "-img";
        $(target).addClass("border border-white border border-2");
        $(see).show();
        $(img).css("filter", "opacity(30%)");
    }

    function mouseout(target, id) {
        let see = "#" + id + "-see";
        let img = "#" + id + "-img";
        $(target).removeClass("border border-white border border-2");
        $(see).hide();
        $(img).css("filter", "opacity(100%)");
    }

```


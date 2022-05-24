## 클라이언트
- input type = "file" 로 파일을 받는다 { accept="image/png" }
```
<input type="file" style="display: none" id="image" accept="image/png"/>
```
- ext 는 파일의 확장자이다.
-  ext != "png" 로 비교 가능
- 선택한 파일은 files[0] 데이터를 가공시킨다.
```
 $("#image").change(function () {      
    var ext = $(this).val().split('.').pop().toLowerCase();  
    ext != "png" 
    var fileImg = this.files[0];
    fileUpload(fileImg);
})
```
-  cache: false, contentType: false, enctype: 'multipart/form-data', processData: false, 설정을 꼭 해주고 파일을 넘겨준다.
```
    function fileUpload(file) {
        var form_data = new FormData();
        form_data.append('file', file);
        form_data.append('subPath', "images");

        $.ajax({
            data: form_data,
            type: "POST",
            url: '/common/fileUpload',
            cache: false,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (url) {
                imgUrl = url;
            },
            error: function (e) {

            }
        });
    }
```


## 서버
- controller
- MultipartFile로 file을 바인딩한다.
```
    @ResponseBody
    @RequestMapping("/common/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam("subPath") String subPath){
        return commonService.fileUpload(file, subPath);
    }
```
- service
> 1. !file.exists()  폴더가 없으면  file.mkdirs(); 생성된다.  
> 2. new File("").getAbsolutePath() 프로젝트 제일 상단 폴더 까지 표시된다.  
> 3. rtnVal변수는 클라이언트 img태그에 src에 들어가게된다.
```
    public String fileUpload(MultipartFile attcFile, String subPath) {
        String rtnVal = "";
        try {
            String attcFileNm = "";
            String attcFileOriNm = "";
            String attcFileOriExt = "";
            String storedFileName = "";

            attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
            attcFileOriNm = attcFile.getOriginalFilename();
            attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
            storedFileName = attcFileNm + attcFileOriExt;

            String fullVal = new File("").getAbsolutePath() + "\\src" +"\\main"+ "\\resources" + "\\static\\";
            rtnVal =  fullVal + subPath + "\\" + storedFileName;
            File file = new File(rtnVal);

            if (!file.exists()) {
                file.mkdirs();
            }

            attcFile.transferTo(file);
            rtnVal =  subPath + "/" + storedFileName;
        } catch (Exception e) {
            rtnVal = "";
        }
        return rtnVal;
    }
```


## 만들게 이유
- 지인들과 게임을 하나 조금하게 게임을 개발하게 되었다 나는 서버쪽을 맡게되었고 그 중에 데이터베이스에 하드코딩(?) 으로 insert해야하는데 데이터가 있는데 시간이 너무 많이 소모될 예정이었다. 전 프로젝트중 엑셀 업로드 기능을 누가 구현해놓은 기억이 있었지만 자동화가 아닌 각 양식에 맞는 업로드 기능을 하드로 코딩되어있던 로직이였다 

## 문제점
- 1. row 랑 cell을 정해놓고  for문을 돌릴 때 for문이 끝나는 지점을 하드코딩으로 적었다.
- 2. 데이터 타입에 이미 알고 있기에 하드코딩으로 변환을 시켰다.

# controller
```
@PostMapping("/fileUpload")
public String uploadFile(MultipartHttpServletRequest request) {
    MultipartFile file = null;
    Iterator<String> iterator = request.getFileNames();
    if (iterator.hasNext()) {
        file = request.getFile(iterator.next());
    }
    commonService.fileUpload(file);
    return null;
}
```  

# service
```
/*
* 
* file.getInputStream() 으로 OPCPackage 객체로 변환 후 XSSFWorkbook 객로 변환
*  ROW (세로) cell (가로)
* sheet.getPhysicalNumberOfRows() 세로의 총 길이를 알 수 있음
* setFild() 따로 분리해둔 메소드 를 이용해 POJO 객체를 만든다.
*
* */
public void fileUpload(MultipartFile file) {
    //        List<Fruit> list = new ArrayList<Fruit>();
    try {
        OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

        // 첫번째 시트 불러오기
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<SkillCard> skillCardList = new ArrayList<>();

        // dbName 과 pk 값
        String dbName = sheet.getRow(0).getCell(0).toString();
        String pk = sheet.getRow(1).getCell(0).toString();

        // getPhysicalNumberOfRows() : row 최대 값

        for (int i = 4; i < sheet.getPhysicalNumberOfRows(); i++) {

            SkillCard skillCard = setFild(sheet, sheet.getRow(i));
            skillCardList.add(skillCard);
        }

        commonMapper.insertDbData3(skillCardList, dbName, pk);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```  
# setFild() 메소드 
```
/*
*
* getPhysicalNumberOfCells() row의 각 가로의 값을 알 수 있음
* ReflectionUtils.findField(POJO클래스, 셀 위치) 메소드를 이용 Field개체로 변환
* ReflectionUtils.makeAccessible(Field 객체) 메소드를 이용 private 객체에 접근
* ReflectionUtils.setField(Field 객체, POJO클래스 객체 , 셀 위치) 메소드를 이용하여 setter와 같은 기능 수행
*
* switch 문으로 각 데이터타입에 대응
* */
private SkillCard setFild(XSSFSheet row, XSSFRow value){

    SkillCard skillCard = new SkillCard();

    //  getPhysicalNumberOfCells() : cell 최대값
    for (int j = 0; j < row.getRow(2).getPhysicalNumberOfCells(); j++) {

        Field field = ReflectionUtils.findField(SkillCard.class, row.getRow(3).getCell(j).toString());
        ReflectionUtils.makeAccessible(field);


        switch(row.getRow(2).getCell(j).toString()) {

        case "String":
            ReflectionUtils.setField(field, skillCard, value.getCell(j).toString());
            break;
        case "int":
            ReflectionUtils.setField(field, skillCard, (int) Math.floor(Double.parseDouble(value.getCell(j).toString())));
            break;
        }
    }
    return skillCard;
}
```

# 엑셀 문서 
-DB는 파이어베이스를 사용하고 있다.
![화면 캡처 2022-05-17 192928](https://user-images.githubusercontent.com/81284265/168791316-c82c1622-530a-4071-8c5b-4f2315b2775b.png)

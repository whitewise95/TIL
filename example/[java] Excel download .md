
## 기본 
```
@RequestMapping(value="/goodsExcelExport.do")
	public void goodsExcelExport(HttpServletResponse response , @RequestParam Map<String, String> dateMap) throws Exception {
		  
		SimpleDateFormat fileSdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		String makeFileTime = fileSdf.format(new Date());
		String makeFileName = makeFileTime + "_goodsList.xls";
		
	    // 워크북 생성
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("상품리스트");
	    Row row = null;
	    Cell cell = null;

	    int rowNo = 0;


	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();
	    // 가는 경계선
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);


	    // 노란색 배경
	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 가운데 정렬
	    headStyle.setAlignment(HorizontalAlignment.CENTER);


	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);


	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("상품번호");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("상품이름");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("저자");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("출판사");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("상품가격");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("입고일자");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("출판일");


	    // 데이터 부분 생성
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String search_type       = dateMap.get("search_type");
		String search_word       = dateMap.get("search_word");
		String [] tempDate       = null; 
		String beginDate         = "";
		String endDate           = "";
		
		if (dateMap.get("beginDate") == null && dateMap.get("endDate") == null ) {
			tempDate = commonUtil.calcSearchPeriod(fixedSearchPeriod).split(",");
			beginDate = tempDate[0];
			endDate = tempDate[1];
		} 
		else {
			beginDate = dateMap.get("beginDate");
			endDate = dateMap.get("endDate");
		}
		
		Map<String,Object> condMap=new HashMap<String,Object>();
		
		condMap.put("beginDate" , beginDate);
		condMap.put("endDate"   , endDate);
		condMap.put("search_type", search_type);
		condMap.put("search_word", search_word);
		
		for (GoodsDTO goodsDTO :  adminGoodsService.listNewGoods(condMap)) {
			row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(goodsDTO.getGoodsId());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(goodsDTO.getGoodsTitle());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(goodsDTO.getGoodsWriter());
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(goodsDTO.getGoodsPublisher());
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(goodsDTO.getGoodsPrice());
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(dateSdf.format(goodsDTO.getGoodsCredate()));
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(dateSdf.format(goodsDTO.getGoodsPublishedDate()));
		} 


	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename="+makeFileName);

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();

		
	}
```



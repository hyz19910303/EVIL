package com.hyz.evil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;

public class POIUtil {
	private POIUtil() {
	};

	/**
	 * 解析excel 返回json格式的对象
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, List<Map<String, Object>>>> parseExcel(File path) throws Exception {
		if (!path.exists()) {
			throw new Exception("文件不存在");
		}
		FileInputStream fi = new FileInputStream(path);
		List<Map<String, List<Map<String, Object>>>> list = null;
			try {
				if (path.getName().endsWith(".xls")) {
					list = commonParseWorkbook(new HSSFWorkbook(fi));
					String jsonString = JSON.toJSONString(list);
				} else if (path.getName().endsWith(".xlsx")) {
					list = commonParseWorkbook(new XSSFWorkbook(fi));
					String jsonString = JSON.toJSONString(list);
				} else {
					throw new Exception("文件格式不正确");
				}
			} finally {
				fi.close();
			}
		return list;
	}

	private static List<Map<String, List<Map<String, Object>>>> commonParseWorkbook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		int rowfirst = sheet.getFirstRowNum();
		int rowlast = sheet.getLastRowNum();
		List<Map<String, List<Map<String, Object>>>> list = new LinkedList<>();
		int p=1;
		for (int i = rowfirst; i <= rowlast; i++) {
			Map<String, List<Map<String, Object>>> rowMap = new ConcurrentHashMap<>(rowlast - rowfirst + 10);
			Row row = sheet.getRow(i);
			short celllast = row.getLastCellNum();
			short cellfirst = row.getFirstCellNum();
			List<Map<String, Object>> cellList = new LinkedList<>();
			short q=1;
			for (int j = cellfirst; j < celllast; j++) {
				Map<String, Object> cellMap = new LinkedHashMap<>(1);
				Cell cell = row.getCell(j);
				Object cellValue = getCellValue(cell);
				cellMap.put("cellValue_" + q, cellValue);
				cellList.add(cellMap);
				q++;
			}
			rowMap.put("rowData_" + p, cellList);
			p++;
			list.add(rowMap);

		}
		return list;
	}


	@SuppressWarnings("deprecation")
	private static Object getCellValue(Cell cell) {
		Object value = null;
		switch (CellType.forInt(cell.getCellType())) {
		case NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		case _NONE:
			value = "";
			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case BLANK:
			value = "";
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case ERROR:
			value = cell.getErrorCellValue();
			break;
		}
		return value;
	}
}

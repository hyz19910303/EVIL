package com.hyz.service.Excel.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hyz.evil.util.POIUtil;
import com.hyz.service.Excel.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public String parseExcel(String path) throws Exception {
		
			List<Map<String, List<Map<String, Object>>>> parseExcel = POIUtil.parseExcel(new File(path));
			int i=1;
			for (Map<String, List<Map<String, Object>>> map : parseExcel) {
				List<Map<String, Object>> list = map.get("rowData_"+i);
				short j=1;
				for (Map<String, Object> mp : list) {
					Object object = mp.get("cellValue_"+j);
					System.out.println(object);
					j++;
				}
				i++;
			}
			String jsonString = JSON.toJSONString(parseExcel);
			System.out.println(jsonString);
		
		return null;
	}
	
}

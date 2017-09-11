package com.hyz.evil.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIUtils {
	public static  final String Default_Sheet_Name="sheet";
	
	public POIUtils() {
		super();
	}
	private static HSSFWorkbook  workbook=null;
	
	
	/**
	 * 
	 * @param jia 对象的数据
	 * @param file 文件存储路径
	 * @param orderField 对应的对象字段以及对应的excel头文字 eg [{"OP_COUNT":"门诊人次"},{"IN_COUNT":"住院人次"}] 字段需和参数jia 相对应 字段的顺序决定excel
	 * 表格显示的顺序 
	 */
	public static <P> void ToExcelOrderFiled(List<P> jia,File file,List<Map<String,String>> orderField) {
		
		workbook= new HSSFWorkbook();
		
		HSSFSheet createSheet = workbook.createSheet(Default_Sheet_Name);
		
		FileOutputStream fileOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(file);
			
		//默认文字贴顶读写
		HSSFRow row = createSheet.createRow(0);
		Set<String> fieldSet = new LinkedHashSet<>();
		for (short i = 0; i < orderField.size(); i++) {
			HSSFCell createCell = row.createCell(i);
			String FiledName = orderField.get(i).keySet().iterator().next();
			fieldSet.add(FiledName);
			createCell.setCellValue(orderField.get(i).get(FiledName));
		}
		//Set<String> getMethods = getPojoFieldsInGetSet(jia.get(0).getClass(),"Method");
		for (int i = 0; i < jia.size(); i++) {
			P pp=jia.get(i);
			HSSFRow createRow = createSheet.createRow(i+1);
			
			Method[] methodsArr = pp.getClass().getMethods();
			short index=0;
			for (String FieldName : fieldSet) {
				String value=null;
				for (int k = 0; k < methodsArr.length; k++) {
					String methodName=FieldName.substring(0, 1).toUpperCase()+FieldName.substring(1);
					if(("get"+methodName).equals(methodsArr[k].getName())) {
						//调用 get 方法
						value = (String) methodsArr[k].invoke(pp);
						
						continue;
					}
				}
				if(value!=null && value!="") {
					HSSFCell createCell = createRow.createCell(index);
					createCell.setCellValue(value);
				}
				index++;
			}
		}
		
		workbook.write(fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fileOutputStream!=null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param jia 对象数组
	 * @param file 文件路径
	 * @throws Exception
	 */
	public static <J> void Converter(List<J> jia,File file) throws Exception{
		HSSFSheet createSheet = workbook.createSheet(Default_Sheet_Name);
		for (int i = 0; i < jia.size(); i++) {
			
			J jj=jia.get(i);
			Set<String> getMethods = getPojoFieldsInGetSet(jj.getClass(),"Method");
			//
			HSSFRow createRow = createSheet.createRow(i);
			//Set<String> getSetfiled = getPojoFieldsInGetSet(jj.getClass(),"Field");
			Method[] methodsArr = jj.getClass().getMethods();
			//循环对比找到对应的方法 调用
			short index=0;
			for (int k = 0; k < methodsArr.length; k++) {
				String value=null;
				for (String methodName : getMethods) {
					if(methodName.equals(methodsArr[k].getName())) {
						//调用 get 方法
						value = (String) methodsArr[k].invoke(jj);
					}
				}
				if(value!=null && value!="") {
					HSSFCell createCell = createRow.createCell(index);
					createCell.setCellValue(value);
					index++;
				}
			}
		}
		
		workbook.write(new FileOutputStream(file));
	}
	/**
	 * 获取对象拥有get set 方法/属性名称
	 * @param p
	 * @return
	 */
	private static <P>  Set<String> getPojoFieldsInGetSet(Class<P> p,String methodOrField){
		Set<String> set = new HashSet<>();
//		Set<String> methodName = new HashSet<>();
		try {
			Field[] fields = p.getDeclaredFields();
			Method[] methods = p.getMethods();
			for (Field field : fields) {
				for (Method method : methods) {
					String methodField;
					if(field.getName().length()>1) {
						methodField = field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
					}else {
						methodField=field.getName().toUpperCase();
					}
					if(/*("set"+methodField).equals(method.getName()) ||*/ ("get"+methodField).equals(method.getName())) {
						if("Field".equals(methodOrField)) {
							set.add(field.getName());
						}else  {
							set.add(method.getName());
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}
}

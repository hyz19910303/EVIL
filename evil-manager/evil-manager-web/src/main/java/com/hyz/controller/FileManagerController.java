package com.hyz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyz.service.Excel.ExcelService;

import sun.misc.Unsafe;

@Controller()
@RequestMapping("/fileManager")
public class FileManagerController {
	
	private final Logger log =LoggerFactory.getLogger(FileManagerController.class);
	
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping("/fileDownload.do")
	//@ResponseBody
	public void fileDownload(HttpServletRequest request,HttpServletResponse response){
		String filePath=request.getServletContext().getRealPath("/WEB-INF/files");
		String fileName="批量导入模板.rar";
		InputStream in=null;
		try {
			in = new FileInputStream(filePath+File.separator+fileName);
			OutputStream out  = response.getOutputStream();
			response.setContentType("text/plain;charset=utf-8");
			//response.setContentType("application/x-download");
			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
			byte[] by= new byte[1024*1024];
			int i=0;
			while((i=in.read(by))!=-1){
				out.write(by, 0, i);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			//return "fail";
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//return "success";
	}
	
	@RequestMapping(value="/fileUpload",produces="application/json;charset=utf-8")
	@ResponseBody
	public String fileUpload(HttpServletRequest request){
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(100*1024);//文件上传大小
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		if(!ServletFileUpload.isMultipartContent(request)){
			return "fail";
		}
		fileUpload.setHeaderEncoding("UTF-8");
		fileUpload.setFileSizeMax(10*1024*100000);//单个文件上传的大小
		fileUpload.setSizeMax(100000000*1024);//所有文件上传的大小
		fileUpload.setProgressListener(new ProgressListener(){
			@Override
			public void update(long pBytesRead, long pContentLength, int pItems) {
				System.out.println("正在读取第"+pItems+"个文件,文件大小为:"+pContentLength+"。已经上传"+pBytesRead);
			}
		});
		try {
		    List<FileItem> parseRequest = fileUpload.parseRequest(request);
			//List<FileItem> list = parseParameterMap.get("image");
			String realPath = request.getServletContext().getRealPath("/WEB-INF/files");
			System.out.println(realPath);
			for (FileItem fileItem : parseRequest) {
				fileItem.getOutputStream();
				String fileName = fileItem.getName();
				fileItem.write(new File(realPath+File.separator+fileName));
				fileItem.delete();//删除临时文件 防止占用硬盘
				if(fileName.endsWith(".xls") ||fileName.endsWith(".xlsx")) {
					excelService.parseExcel(realPath+File.separator+URLEncoder.encode(fileName, "UTF-8"));
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		//ProgressListener progressListener = fileUpload.getProgressListener();
		//progressListener.update(pBytesRead, pContentLength, pItems);
		return "success";
	}
	
}

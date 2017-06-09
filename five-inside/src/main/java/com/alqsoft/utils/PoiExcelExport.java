package com.alqsoft.utils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;

/**
 * 
 * @Description: TODO
 * @author 时永青
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017年5月11日 上午11:54:52
 * Copyright © 2017 北京易商通公司 All rights reserved.
 * 
 */
public class PoiExcelExport {
	
	
	private final static String FONT="黑体";

	//header = {"id","user_key_id","name","bank","bank_open","bank_no","province","city","num","money",
	//		"sxf","real_tx_money","state","created_time","kqorder_id","chk_time","del_time","auto_state","update_time"};
	
	private final static String sheetName="sheet";
	
	/**
	 * @Description:header  头部字段,headername  map的key,sList  数据
	 * clumnWidth //列单元格宽度
	 * TitleName 标题名字
	
	 * @author:时永青
	
	 * @time:2017年5月11日 下午2:33:28
	 */
	public Result export(HttpServletRequest request, HttpServletResponse response, 
			String[] header,String[] headername,int[] clumnWidth,List<Map<String, Object>> sList,String TitleName){
		if(null==clumnWidth||clumnWidth.length==0){
			clumnWidth=new int[header.length];
			for (int i = 0; i < header.length; i++) {
				clumnWidth[i]=20;
			}
		}
		Result result = new Result();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		font.setFontName(FONT);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontHeightInPoints((short) 12);
		// 表头样式
		HSSFCellStyle cellStyle = wb.createCellStyle(); // 设置单元格样式
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		cellStyle.setFont(font);

		// 合并单元格样式
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		//sheet分页
		int num=sList.size()/65535+1;
		for (int i = 0; i <num; i++) {
			HSSFSheet sheet = wb.createSheet(sheetName+i);
			sheet.autoSizeColumn(1, true);
			// (2)创建excel的sheet页面
			HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
			// (3)创建第一行标题
			HSSFRow row = sheet.createRow(0);
			int indexHead = 0;
			for (String string : header) {
				sheet.setColumnWidth(indexHead, ((clumnWidth[indexHead]) * 256));
				HSSFCell cell_01 = row.createCell(indexHead);
				cell_01.setCellValue(new HSSFRichTextString(string)); // VALUE
				cell_01.setCellStyle(cellStyle);
				indexHead++;
			}
			if(sList.size()-i*65535>65535){
				for (int j = 0; j <65535; j++) {
					Map<String, Object> data = sList.get(i*65535+j);
					HSSFRow _row = sheet.createRow(j + 1);
					for(int m=0;m<header.length;m++){
						HSSFCell cell = _row.createCell((short) m);
						cell.setCellValue(new HSSFRichTextString(
								data.get(headername[m]) == null ? "" : data.get(headername[m]).toString()));
					}
				}
			}else{
				for (int j = 0; j < sList.size()-i*65535; j++) {
					Map<String, Object> data = sList.get(i*65535+j);
					HSSFRow _row = sheet.createRow(j + 1);
					for(int m=0;m<header.length;m++){
						HSSFCell cell = _row.createCell((short) m);
						cell.setCellValue(new HSSFRichTextString(
								data.get(headername[m]) == null ? "" : data.get(headername[m]).toString()));
					}
			}
			}
				
		}
		// (5)指定写出的流对象
		OutputStream outputStream = null;
		String filedisplay = null;
		try {
			
			/*  */
			outputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
//			String time = System.currentTimeMillis() + "";
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String time=format.format(date);
			/*filedisplay =TitleName + time + ".xls";*/
			filedisplay =TitleName + time + ".xls";
			response.setHeader("Content-disposition", "attachment; filename ="
					+ new String((filedisplay).getBytes("utf-8"), "iso8859-1"));
			response.setContentType("application/msexcel");
			// (6)将得到的workbook写入流中
			wb.write(outputStream);

		} catch (FileNotFoundException e) {
			result.setMsg("导出失败");
			// e.printStackTrace();
		} catch (IOException e) {
			result.setMsg("导出失败");
			// e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		result.setMsg("导出成功");
		return result;
	
	}
	public String create(HttpServletRequest request, HttpServletResponse response, 
			String[] header,String[] headername,int[] clumnWidth,
			List<Map<String, Object>> sList,String TitleName,String fileName){
		if(null==clumnWidth||clumnWidth.length==0){
			clumnWidth=new int[header.length];
			for (int i = 0; i < header.length; i++) {
				clumnWidth[i]=20;
			}
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		font.setFontName(FONT);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontHeightInPoints((short) 12);
		// 表头样式
		HSSFCellStyle cellStyle = wb.createCellStyle(); // 设置单元格样式
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		cellStyle.setFont(font);

		// 合并单元格样式
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		//sheet分页
		int num=sList.size()/65535+1;
		for (int i = 0; i <num; i++) {
			HSSFSheet sheet = wb.createSheet(sheetName+i);
			sheet.autoSizeColumn(1, true);
			// (2)创建excel的sheet页面
			HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
			// (3)创建第一行标题
			HSSFRow row = sheet.createRow(0);
			int indexHead = 0;
			for (String string : header) {
				sheet.setColumnWidth(indexHead, ((clumnWidth[indexHead]) * 256));
				HSSFCell cell_01 = row.createCell(indexHead);
				cell_01.setCellValue(new HSSFRichTextString(string)); // VALUE
				cell_01.setCellStyle(cellStyle);
				indexHead++;
			}
			if(sList.size()-i*65535>65535){
				for (int j = 0; j <65535; j++) {
					Map<String, Object> data = sList.get(i*65535+j);
					HSSFRow _row = sheet.createRow(j + 1);
					for(int m=0;m<header.length;m++){
						HSSFCell cell = _row.createCell((short) m);
						cell.setCellValue(new HSSFRichTextString(
								data.get(headername[m]) == null ? "" : data.get(headername[m]).toString()));
					}
				}
			}else{
				for (int j = 0; j < sList.size()-i*65535; j++) {
					Map<String, Object> data = sList.get(i*65535+j);
					HSSFRow _row = sheet.createRow(j + 1);
					for(int m=0;m<header.length;m++){
						HSSFCell cell = _row.createCell((short) m);
						cell.setCellValue(new HSSFRichTextString(
								data.get(headername[m]) == null ? "" : data.get(headername[m]).toString()));
					}
			}
			}
		}
		return createExcel(fileName, wb);
	}
	
	
	/**
	 * @param fileName  文件的物理路径
	 * @param wb   
	 * @return
	 */
	public String createExcel(String fileName,HSSFWorkbook wb){
		FileOutputStream fos=null;
            try {
				fos=new FileOutputStream(fileName);
				wb.write(fos);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         return fileName;
	}

}

package com.alqsoft.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alqsoft.entity.exceldata.DetailReport;
import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;

public class XlsMain {
	 
   /* public static void main(String[] args) throws IOException {
        XlsMain xlsMain = new XlsMain();
        DetailReport xls = null;
        List<DetailReport> list = xlsMain.readXls("E:\\coding\\five-inside\\src\\main\\webapp\\upload\\uploadExcel\\1111494916389300.xls");
        for (int i = 0; i < list.size(); i++) {
            xls = (DetailReport) list.get(i);
           System.out.println(xls.getGoodName());
        }
 
    }*/
 
    /**
     * 读取xls文件内容
     * 
     * @throws IOException
     *             输入/输出(i/o)异常
     */
    @SuppressWarnings("null")
	public  List<DetailReport> readXls(String pah) throws IOException {
        InputStream is = new FileInputStream(pah);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        DetailReport xlsDto = null;
        List<DetailReport> list = new ArrayList<DetailReport>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < 1; numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                break;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum < hssfSheet.getPhysicalNumberOfRows(); rowNum++) {
            	HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (null == hssfRow) {
                	break;
                }
                xlsDto = new DetailReport();
                HSSFCell a  = hssfRow.getCell(0);
                if (a == null) {
                	xlsDto.setId("0");
                }else{
                	 xlsDto.setId(getValue(a));
                }
                
                HSSFCell b = hssfRow.getCell(1);
                if (b == null) {
                	xlsDto.setProvideName("");
                }else{
                	xlsDto.setProvideName(getValue(b));
                }
                
                
                
                HSSFCell c = hssfRow.getCell(2);
                if (c == null) {
                	xlsDto.setProvideId("0");
                }else{
                	xlsDto.setProvideId(getValue(c));
                }
                
                
                
                HSSFCell d = hssfRow.getCell(3);
                if (d == null) {
                	xlsDto.setGoodName("");
                }else{
                	 xlsDto.setGoodName(getValue(d));
                }
                
                
                HSSFCell e = hssfRow.getCell(4);
                if (e == null) {
                	xlsDto.setGoodId("0");
                }else{
                	xlsDto.setGoodId(getValue(e));
                }
                
                
                
                HSSFCell f = hssfRow.getCell(5);
                if (f == null) {
                	  xlsDto.setSaleNum("0");
                }else{
                	xlsDto.setSaleNum(getValue(f));
                }
                
                
                HSSFCell g = hssfRow.getCell(6);
                if (g == null) {
                	xlsDto.setSignNum("");
                }else{
                	xlsDto.setSignNum(getValue(g));
                }
                
                
                HSSFCell h = hssfRow.getCell(7);
                if (h == null) {
                	 xlsDto.setCollectionBank("");
                }else{
                	 xlsDto.setCollectionBank(getValue(h));
                }
               
                
                
                HSSFCell i = hssfRow.getCell(8);
                if (i == null) {
                	xlsDto.setCollectionBankType("");
                }else{
                	xlsDto.setCollectionBankType(getValue(i));
                }
                
                
                HSSFCell j = hssfRow.getCell(9);
                if (j == null) {
                	xlsDto.setRealName("");
                }else{
                	 xlsDto.setRealName(getValue(j));
                }
               
                
                HSSFCell k = hssfRow.getCell(10);
                if (k == null) {
                	 xlsDto.setPayMoney("0.0");
                }else{
                	xlsDto.setPayMoney(getValue(k));
                }
                
                
                HSSFCell l = hssfRow.getCell(11);
                if (l == null) {
                	  xlsDto.setIngNum("0");
                }else{
                	xlsDto.setIngNum(getValue(l));
                }
                
                
                HSSFCell m = hssfRow.getCell(12);
                if (m == null) {
                	xlsDto.setAccountAttr("");
                }else{
                	 xlsDto.setAccountAttr(getValue(m));
                }
               
                
                HSSFCell n = hssfRow.getCell(13);
                if (n == null) {
                	  xlsDto.setAccountType("");
                }else{
                	xlsDto.setAccountType(getValue(n));
                }
                
                
                HSSFCell o = hssfRow.getCell(14);
                if (o == null) {
                	 xlsDto.setAccountArea("");
                }else{
                	xlsDto.setAccountArea(getValue(o));
                }
                
                
                HSSFCell p = hssfRow.getCell(15);
                if (p == null) {
                	xlsDto.setAccountCity("");
                }else{
                	xlsDto.setAccountCity(getValue(p));
                }
                
                
                HSSFCell q = hssfRow.getCell(16);
                if (q == null) {
                	 xlsDto.setZhiBankName("");
                }else{
                	 xlsDto.setZhiBankName(getValue(q));
                }
               
                
                HSSFCell r = hssfRow.getCell(17);
                if (r == null) {
                	 xlsDto.setYlNum("");
                }else{
                	xlsDto.setYlNum(getValue(r));
                }
                
                
                HSSFCell s = hssfRow.getCell(18);
                if (s == null) {
                	 xlsDto.setPaySay("");
                }else{
                	xlsDto.setPaySay(getValue(s));
                }
                
                HSSFCell t = hssfRow.getCell(19);
                if (t == null) {
                	xlsDto.setCollectsPhone("");
                }else{
                	 xlsDto.setCollectsPhone(getValue(t));
                }
               
                
                HSSFCell u = hssfRow.getCell(20);
                if (u == null) {
                	 xlsDto.setInstitution("");
                }else{
                	xlsDto.setInstitution(getValue(u));
                }
                list.add(xlsDto);
            }
        }
        return list;
    }
 
    /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
 
}
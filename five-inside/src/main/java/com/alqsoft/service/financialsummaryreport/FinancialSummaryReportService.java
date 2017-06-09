package com.alqsoft.service.financialsummaryreport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;

import com.alqsoft.entity.emailpush.EmailPush;
import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;
import com.alqsoft.entity.financialsummaryreport.FinancialSummaryReport;
public interface FinancialSummaryReportService {
	List<Map<String, Object>> findSummeryReportList(Map<String, Object> params);

	Integer getSummeryReportTotal(Map<String, Object> map);
	Result delById(Long id);
	
	Iterator<FinancialDetailReport> initialize(Long id,HttpServletRequest request, HttpServletResponse response);

	FinancialSummaryReport updataFinancialSummaryReport(FinancialSummaryReport f);

	FinancialSummaryReport get(Long id);
	Result createExcel(Long id,HttpServletRequest request, HttpServletResponse response);
	Object exportExcel(Long id,HttpServletRequest request, HttpServletResponse response);
}

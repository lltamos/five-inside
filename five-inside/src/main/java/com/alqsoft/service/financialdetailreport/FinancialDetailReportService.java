package com.alqsoft.service.financialdetailreport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;

public interface FinancialDetailReportService {
	List<Map<String,Object>> findDetailReportList(Map<String, Object> params);
	Integer getDetailReportTotal(Map<String, Object> map);
	Result sendEmail(Long id,HttpServletRequest request);
	Result reSendEmail(Long id,Long summaryId,HttpServletRequest request);
}

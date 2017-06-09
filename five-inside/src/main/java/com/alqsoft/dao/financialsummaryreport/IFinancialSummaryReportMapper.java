package com.alqsoft.dao.financialsummaryreport;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IFinancialSummaryReportMapper {
	List<Map<String,Object>> findSummeryReportList(Map<String, Object> params);
	Integer getSummeryReportTotal(Map<String, Object> map);
}

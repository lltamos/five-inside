package com.alqsoft.dao.financialdetailreport;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;
@MyBatisRepository
public interface IfinancialDetailReportMapper {
	List<Map<String,Object>> findDetailReportList(Map<String, Object> params);
	Integer getDetailReportTotal(Map<String, Object> map);
	List<Map<String,Object>> findDetailListForEmail(Map<String, Object> params);
	List<Map<String,Object>> findDataForEmail(Map<String, Object> params);
	List<Map<String,Object>> findDataForSummaryExcal(Long id);
	List<Map<String,Object>> findDatailBySummaryId(Long id);
}

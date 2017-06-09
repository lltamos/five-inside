package com.alqsoft.hibernatedao.financialDetailreport;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;
import com.alqsoft.entity.good.Good;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月16日 下午5:06:33
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface FinancialDetailReportDao extends BaseDao<FinancialDetailReport>{
	@Query(value="select * from financialdetailreportt where summary_id = ? and provider_id =? and state=?",nativeQuery=true)
	public List<FinancialDetailReport> findReportByProviderId(@Param("summary_id") Long summary_id,
														@Param("provider_id") Long provider_id,
														@Param("state") Integer state);
}

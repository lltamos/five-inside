package com.alqsoft.service.financialstatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月17日 下午1:44:39
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface FinancialStatementService {
	//查询财务报表
	ArgsBean findFinancialStatements(ArgsBean page);

	Object exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page);
}

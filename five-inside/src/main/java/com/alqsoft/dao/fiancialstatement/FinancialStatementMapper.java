package com.alqsoft.dao.fiancialstatement;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月17日 上午11:43:41 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface FinancialStatementMapper {
	List<Map<String, Object>> findFinancialStatements(ArgsBean page);

	int findFinancialStatementsCount(ArgsBean page);
	
	Integer findInventoryt(Map<String, Object> map);
	Integer findOrdergoodstRemainNum(Map<String, Object> map);
}

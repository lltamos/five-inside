package com.alqsoft.dao.repertory;

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
 * @create-time 2017年5月10日 上午9:53:40 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface RepertoryDao {
	/**
	 * 库存查询
	 * @param bean 
	 * 
	 * @return list
	 */
	List<Map<String, Object>> findInventoryQuiry(ArgsBean bean);

	int findInventoryQuiryCount(ArgsBean page);

	/**
	 * 查询库存记录
	 * 
	 * @return list
	 */
	List<Map<String, Object>> findInventoryRecords(ArgsBean bean);

	int findInventoryRecordsCount(ArgsBean bean);

	/**
	 * 入库记录
	 * 
	 * @return list
	 */
	List<Map<String, Object>> findStoreRecording(ArgsBean bean);

	int findStoreRecordingCount(ArgsBean bean);
}

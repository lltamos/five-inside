package com.alqsoft.dao.storageapply;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: //财务-入库
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月12日 上午10:15:45 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface StorageApplyMapper {

	List<Map<String, Object>> findStorageApplys(ArgsBean bean);

	int findStorageApplysCount(ArgsBean bean);
}

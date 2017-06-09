package com.alqsoft.dao;

import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserInfoDao {
	// 根據訂單中心id查找訂單中心法人 
	Map<String, Object> getOrderCenterByNumberMap(long id);

}

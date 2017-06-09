package com.alqsoft.dao.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
 * @author zj
 *
 */
@MyBatisRepository
public interface IProviderMapper {

	List<Map<String,Object>> findProviderList(Map<String, Object> params);

	int getProviderCount(Map<String, Object> params);

	List<Map<String, Object>> findAssociatedPage(HashMap<String, Object> param);

	int getAssociatedCount(HashMap<String, Object> param);

	List<Map<String, Object>> findAssociatedCancelPage(HashMap<String, Object> param);

	int getAssociatedCancelCount(HashMap<String, Object> param);
}

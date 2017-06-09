package com.alqsoft.dao.good;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.good.Good;

/**
 * 
 * @author zj
 *
 */
@MyBatisRepository
public interface IGoodMapper {
	
	List<Map<String,Object>> findGoodList(Map<String, Object> params);

	int getGoodCount(Map<String, Object> params);
	
	/**
	 * 
	* @Title: getGoodByBarCode 
	* @Description: 根据条形码查找已经入库的商品 
	* @return Good    返回类型 
	* @throws
	 */
	public Good getGoodByBarCodeBe(String bar_code);
}

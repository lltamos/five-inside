package com.alqsoft.hibernatedao.goodsreturnrecord;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.goodsreturnrecord.GoodsReturnRecord;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月11日 下午5:58:23 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface GoodsReturnRecordDao extends BaseDao<GoodsReturnRecord> {
	
	/**
	 * @param id  订单号
	 * @return
	 */
	@Query("FROM GoodsReturnRecord where OrderGoodsId=:id")
	public Good getReCordByOrderId(@Param("id") Long id);
	/**
	 * 
	* @Title: getGoodsReturnRecordByOrderNo 
	* @Description: 根据订单号查找订单记录
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	@Query(value="select count(1) from goodsreturnrecordt where order_no = ?",nativeQuery=true)
	public int getGoodsReturnRecordByOrderNo(@Param("order_no") String order_no);
	
	
	
	
	
	
	
	
}

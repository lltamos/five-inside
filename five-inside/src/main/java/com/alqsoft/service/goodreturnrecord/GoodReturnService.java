package com.alqsoft.service.goodreturnrecord;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.goodsreturnrecord.GoodsReturnRecord;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月11日 下午6:21:29
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface GoodReturnService extends BaseService<GoodsReturnRecord>{

	

	public void findReturnRecordList(ArgsBean bean);

	public void findGoodReturnList(ArgsBean bean);
	/**
	 * 
	* @Title: getGoodsReturnRecordByOrderNo 
	* @Description: 根据订单号查询退货记录 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public int getGoodsReturnRecordByOrderNo(String order_no);


}

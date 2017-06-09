package com.alqsoft.service.ordergoods;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.ordergoods.OrderGoods;
/**
 * 
* @ClassName: OrderGoodsService 
* @Description: 订单商品明细service
* @author 腾卉 
* @date 2017年5月18日 下午7:25:14 
*
 */
public interface OrderGoodsService extends BaseService<OrderGoods>{

	/**
	* @Title: updateRemainNumByOrderNum 
	* @Description: 根据order_num和good_id修改remain_num
	* @return int    返回类型 	
	* @throws
	 */
	public int updateRemainNumByOrderNumAndGoodId(Integer remain_num,String order_num,Long good_id);
	/**
	 * 
	* @Title: lock 
	* @Description: 退货数据加锁
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<OrderGoods> lock(String order_num,Long good_id);
}

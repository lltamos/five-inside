package com.alqsoft.service.shoporder;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;

/**
 * 
* @ClassName: ShopOderService 
* @Description: 店铺订单service
* @author 腾卉 
* @date 2017年5月15日 下午1:55:41 
*
 */
public interface ShopOrderService {

	/**
	 * 
	* @Title: findAllBDOrder 
	* @Description: 查看本地全部订单列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllBDOrder(String mobile,Integer page, Integer rows);
	/**
	 * 
	* @Title: getBDOrderCount 
	* @Description: 本地本地订单记录总条数  
	* @return int    返回类型 
	* @throws
	 */
	public int getBDOrderCount(String mobile);
	/**
	 * 
	* @Title: findAllXYTOrder 
	* @Description: 查看新业态全部订单列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllXYTOrder(String mobile,Integer page, Integer rows);
	/**
	 * 
	* @Title: getXYTOrderCount 
	* @Description: 本地新业态订单记录总条数 
	* @return int    返回类型 
	* @throws
	 */
	public int getXYTOrderCount(String mobile);
	/**
	 * 
	* @Title: checkDetails 
	* @Description: 订单查看详情
	* @return Result    返回类型 
	* @throws
	 */
	public Result checkDetails(String order_num);
}

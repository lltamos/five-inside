package com.alqsoft.dao.shoporder;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
* @ClassName: ShopOrderDao 
* @Description: 店铺订单Dao 
* @author 腾卉 
* @date 2017年5月15日 上午11:31:34 
*
 */
@MyBatisRepository
public interface ShopOrderMapper {
	/**
	 * 
	* @Title: findAllBDOrder 
	* @Description: 查看本地全部订单列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllBDOrder(Map<String, Object> map);
	/**
	 * 
	* @Title: getBDOrderCount 
	* @Description: 本地本地订单记录总条数 
	* @return int    返回类型 
	* @throws
	 */
	public int getBDOrderCount(Map<String, Object> map);
	/**
	 * 
	* @Title: findAllXYTOrder 
	* @Description: 查看新业态全部订单列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllXYTOrder(Map<String, Object> map);
	/**
	 * 
	* @Title: getXYTOrderCount 
	* @Description: 本地新业态订单记录总条数 
	* @return int    返回类型 
	* @throws
	 */
	public int getXYTOrderCount(Map<String, Object> map);
	/**
	 * 
	* @Title: checkDetails 
	* @Description: 订单查看详情
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String,Object>> checkDetails(String order_num);
}

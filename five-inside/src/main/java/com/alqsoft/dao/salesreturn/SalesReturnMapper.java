package com.alqsoft.dao.salesreturn;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;
/**
 * 
* @ClassName: SalesReturnMapper 
* @Description: 退货管理 
* @author 腾卉 
* @date 2017年5月16日 下午4:04:45 
*
 */
@MyBatisRepository
public interface SalesReturnMapper {

	/**
	 * 
	* @Title: findAllGoodsReturn 
	* @Description: 查看商品退货列表 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllGoodsReturn(Map<String, Object> map);
	/**
	 * 
	* @Title: getGoodsReturnCount 
	* @Description: 商品退货总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getGoodsReturnCount(Map<String, Object> map);
	/**
	 * 
	* @Title: findAllReturnRecord 
	* @Description: 退货记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllReturnRecord(Map<String, Object> map);
	/**
	 * 
	* @Title: getReturnRecordCount 
	* @Description: 退货记录总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getReturnRecordCount(Map<String, Object> map);
}

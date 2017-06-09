package com.alqsoft.dao.stocktakeapply;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
* @ClassName: StocktAkeapplyDao 
* @Description: 盘存申请记录dao(MyBatis) 
* @author 腾卉 
* @date 2017年5月9日 下午3:06:55 
*
 */
@MyBatisRepository
public interface StockTakeApplyMyBatisDao {

	/**
	 * 
	* @Title: getAll 
	* @Description: 查询全部盘存申请列表
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAll(Map<String, Object> map);
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 查询全部盘存申请总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String, Object> map);
	/**
	 * 
	* @Title: findAllStockRecord 
	* @Description: 查询全部盘存记录列表
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllStockRecord(Map<String, Object> map);
	/**
	 * 
	* @Title: getStockRecordCount 
	* @Description: 查询全部盘存记录总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getStockRecordCount(Map<String, Object> map);
	/**
	 * 
	 * @Title: findAllApplyRecord 
	 * @Description: 查询全部申请记录列表
	 * @return List<Map<String, Object>>    返回类型 
	 * @throws
	 */
	public List<Map<String, Object>> findAllApplyRecord(Map<String, Object> map);
	/**
	 * 
	 * @Title: getApplyRecordCount 
	 * @Description: 查询全部申请记录总数 
	 * @return int    返回类型 
	 * @throws
	 */
	public int getApplyRecordCount(Map<String, Object> map);
	
}

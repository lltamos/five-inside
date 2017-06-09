package com.alqsoft.dao.savesellstatement;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
* @ClassName: SaveSellStatementDao 
* @Description: 存销报表Dao
* @author 腾卉 
* @date 2017年5月11日 上午11:52:23 
*
 */
@MyBatisRepository
public interface SaveSellStatementDao {

	/**
	 * 
	* @Title: findAll 
	* @Description: 查看全部记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAll(Map<String, Object> map);
	/**
	 * 
	* @Title: getCount 
	* @Description: 存销报表记录总条数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String, Object> map);
	/**
	 * 
	* @Title: deriveCxStatement 
	* @Description: 存销报表导出
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> deriveCxStatement(Map<String, Object> map);
	/**
	 * 
	* @Title: deriveKcStatement 
	* @Description: 库存报表导出
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> deriveKcStatement();
	
}

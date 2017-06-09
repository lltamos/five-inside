package com.alqsoft.service.savesellstatement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.good.Good;

/**
 * 
* @ClassName: SaveSellStatementService 
* @Description: 存销报表service 
* @author 腾卉 
* @date 2017年5月11日 上午11:53:03 
*
 */
public interface SaveSellStatementService extends BaseService<Good>{

	/**
	 * 
	* @Title: findAll 
	* @Description: 查看全部记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAll(String begin_time,String end_time,String good_name,Integer page, Integer rows);
	/**
	 * 
	* @Title: getCount 
	* @Description: 存销报表记录总条数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(String begin_time, String end_time, String good_name);
	/**
	 * 
	* @Title: deriveCxStatement 
	* @Description: 存销报表导出 
	* @return Result    返回类型 
	* @throws
	 */
	public Result deriveCxStatement(HttpServletRequest request, HttpServletResponse response,Map<String, Object> param);
	/**
	 * 
	* @Title: deriveKcStatement 
	* @Description: 库存报表导出
	* @return Result    返回类型 
	* @throws
	 */
	public Result deriveKcStatement(HttpServletRequest request, HttpServletResponse response);
}

package com.alqsoft.service.stocktakeapply;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.stocktakeapply.StockTakeApply;

/**
 * 
* @ClassName: StockTakeApplyService 
* @Description: 盘存申请记录Service 
* @author 腾卉 
* @date 2017年5月8日 下午2:16:46 
*
 */
public interface StockTakeApplyService extends BaseService<StockTakeApply>{

	/**
	 * 
	* @Title: saveStockTakeApply 
	* @Description: 盘存申请保存 
	* @return Result    返回类型 
	* @throws
	 */
	public Result saveStockTakeApply(StockTakeApply stockTakeApply);
	/**
	 * 
	* @Title: getGoodByBarCode 
	* @Description: 根据条形码获取商品信息 
	* @return Result    返回类型 
	* @throws
	 */
	public Result getGoodByBarCode(String barCode);
	/**
	 * 
	 * @Title: getGoodByBarCodeBe 
	 * @Description: 根据条形码查看商品是否入库 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getGoodByBarCodeBe(String barCode);
	/**
	 * 
	* @Title: getAll 
	* @Description: 查询全部盘存申请
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAll(String begin_time,String end_time,Integer page, Integer rows);
	/**
	 * 
	* @Title: getCount 
	* @Description: 查询全部盘存申请总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(String begin_time,String end_time);
	/**
	 * 
	* @Title: updateUserGradeById 
	* @Description: 更改盘存审批状态 
	* @return Result    返回类型 
	* @throws
	 */
	public Result updateStates(Integer States,Long id);
	/**
	 * 
	* @Title: getAll 
	* @Description: 查询全部盘存记录
	* @return List<Map<String, Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllStockRecord(String Goodname,String BarCode,Integer page, Integer rows);
	/**
	 * 
	* @Title: getCount 
	* @Description: 查询全部盘存记录总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getStockRecordCount(String Goodname,String BarCode);
	/**
	 * 
	 * @Title: getAll 
	 * @Description: 查询全部申请记录
	 * @return List<Map<String, Object>>    返回类型 
	 * @throws
	 */
	public List<Map<String, Object>> findAllApplyRecord(String begin_time,String end_time,Integer page, Integer rows);
	/**
	 * 
	 * @Title: getCount 
	 * @Description: 查询全部申请记录总数 
	 * @return int    返回类型 
	 * @throws
	 */
	public int getApplyRecordCount(String begin_time,String end_time);

}

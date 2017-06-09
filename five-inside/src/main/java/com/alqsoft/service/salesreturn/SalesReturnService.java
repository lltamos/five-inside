package com.alqsoft.service.salesreturn;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
/**
 * 
* @ClassName: SalesReturnService 
* @Description: 退货管理service 
* @author 腾卉 
* @date 2017年5月16日 下午4:19:44 
*
 */
public interface SalesReturnService {

	/**
	 * 
	* @Title: findAllGoodsReturn 
	* @Description: 查看商品退货列表 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllGoodsReturn( String order_num,Integer page, Integer rows );
	/**
	 * 
	* @Title: getGoodsReturnCount 
	* @Description: 商品退货总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getGoodsReturnCount( String order_num );
	/**
	 * 
	* @Title: findAllReturnRecord 
	* @Description: 退货记录列表
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findAllReturnRecord( String good_name,String order_no,Integer page, Integer rows );
	/**
	 * 
	* @Title: getReturnRecordCount 
	* @Description: 退货记录总数 
	* @return int    返回类型 
	* @throws
	 */
	public int getReturnRecordCount(String good_name,String order_no);
	/**
	 * 
	* @Title: goodsReturn 
	* @Description: 调用退货接口
	* @return Result    返回类型 
	* @throws
	 */
	public Result goodsReturn(Long userid,Integer return_num,String remark,Long order_goods_id);
	/**
	 * 
	* @Title: saverecord 
	* @Description: 保存退货记录并更改库存 
	* @return Result    返回类型 
	* @throws
	 */
	public Result saverecord(Long order_goods_id, String remark, Integer return_num);
}

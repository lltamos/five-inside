package com.alqsoft.service.good;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.good.Good;

public interface GoodService extends BaseService<Good>{

	/**
	 * 根据条形码获取商品信息
	 * @param barCode
	 * @return
	 */
	public Good getGoodByBarCode(String barCode);

	/**
	 * 条码绑定分页列表
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> findGoodPage(HashMap<String, Object> param);

	/**
	 * 条码绑定分页总记录
	 * @param param
	 * @return
	 */
	public int getGoodCount(HashMap<String, Object> param);

	/**
	 * 根据商品id获取库存
	 * @param goodId
	 * @return
	 */
	public Result getInventoryByGoodId(Long goodId);

	/**
	 * 清除商品条码
	 * @param goodId
	 * @return
	 */
	public Result removeBarCode(Long goodId);

	/**
	 * 绑定条码
	 * @param goodId
	 * @param barCode
	 * @return
	 */
	public Result bindingBarCode(Long goodId, String barCode);

	/**
	 * 添加商品
	 * @param good
	 * @return
	 */
	public Result goodAdd(Good good);

	/**
	 * 删除、下架
	 * @param goodId
	 * @param goodStates
	 * @return
	 */
	public Result modifGood(Long goodId, Integer goodStates);

	/**
	 * 修改商品
	 * @param good
	 * @return
	 */
	public Result goodEdit(Good good,HttpServletRequest request);
	/**
	 * 
	* @Title: getGoodByBarCode 
	* @Description: 根据条形码查找已经入库的商品 
	* @return Good    返回类型 
	* @throws
	 */
	public Good getGoodByBarCodeBe(String bar_code);
}

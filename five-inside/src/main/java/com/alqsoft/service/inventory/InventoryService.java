package com.alqsoft.service.inventory;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.storageapply.StorageApply;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午11:00:49 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface InventoryService extends BaseService<Inventory> {

	/**
	 * 添加入库申请
	 */
	public StorageApply insertStorageApply(StorageApply entity);

	/**
	 * 库存查询
	 */
	public List<Map<String, Object>> findInventoryQuiry(ArgsBean bean);

	/**
	 * 查询库存记录
	 * 
	 */
	public List<Map<String, Object>> findInventoryRecords(ArgsBean bean);

	/**
	 * 入库记录
	 * 
	 */
	public List<Map<String, Object>> findStoreRecording(ArgsBean bean);

	int findInventoryQuiryCount(ArgsBean bean);

	int findInventoryRecordsCount(ArgsBean bean);

	int findStoreRecordingCount(ArgsBean bean);

	/**
	 * 根据商品id获取库存
	 * @param goodId
	 * @return
	 */
	public Inventory getInventoryByGoodId(Long goodId);
	/**
	 * 
	* @Title: updateInventoryByGoodsId 
	* @Description: 更新库存 
	* @return int    返回类型 
	* @throws
	 */
	public int updateMoreNumByGoodsId(Integer more_num,Long goodid);
	/**
	 * 
	* @Title: updateInventoryByGoodsId 
	* @Description: 根据goods_id更新库存表num more_num
	* @return int    返回类型 
	* @throws
	 */
	public int updateInventoryByGoodsId(Integer num,Integer more_num,Long goods_id);
	/**
	 * 退货数据加锁
	 * @param goodId
	 * @return
	 */
	public Inventory lock(Long goodId);

}

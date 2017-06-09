package com.alqsoft.service.impl.inventory;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.repertory.RepertoryDao;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.storageapply.StorageApply;
import com.alqsoft.hibernatedao.inventory.InventoryDao;
import com.alqsoft.hibernatedao.storageapply.StorageApplyDao;
import com.alqsoft.service.inventory.InventoryService;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: 库存模块service
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午11:02:00 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Resource
	private InventoryDao inventoryDao;

	@Resource
	private RepertoryDao repertoryDao;

	@Resource
	private StorageApplyDao storaApplyDao;

	@Override
	public Inventory saveAndModify(Inventory arg0) {
		return inventoryDao.save(arg0);
	}

	@Override
	public boolean delete(Long arg0) {
		try {
			inventoryDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Inventory get(Long arg0) {
		return inventoryDao.findOne(arg0);
	}

	@Override
	public StorageApply insertStorageApply(StorageApply entity) {
		return storaApplyDao.save(entity);

	}

	// 库存查询
	@Override
	public List<Map<String, Object>> findInventoryQuiry(ArgsBean bean) {
		bean.InitializeFilter();
		return repertoryDao.findInventoryQuiry(bean);
	}

	@Override
	public int findInventoryQuiryCount(ArgsBean bean) {
		bean.InitializeFilter();
		return repertoryDao.findInventoryQuiryCount(bean);
	}

	// 库存记录
	@Override
	public List<Map<String, Object>> findInventoryRecords(ArgsBean bean) {
		return repertoryDao.findInventoryRecords(bean);
	}

	@Override
	public int findInventoryRecordsCount(ArgsBean bean) {
		bean.InitializeFilter();
		return repertoryDao.findInventoryRecordsCount(bean);
	}

	// 入库记录
	@Override
	public List<Map<String, Object>> findStoreRecording(ArgsBean bean) {
		return repertoryDao.findStoreRecording(bean);
	}

	@Override
	public int findStoreRecordingCount(ArgsBean bean) {
		bean.InitializeFilter();
		return repertoryDao.findStoreRecordingCount(bean);
	}

	@Override
	public Inventory getInventoryByGoodId(Long goodId) {
		Inventory inventory = inventoryDao.getInventoryByGoodsId(goodId);
		return inventory;
	}

	@Override
	@Transactional
	public int updateMoreNumByGoodsId(Integer more_num, Long goodid) {
		int updateInventoryByGoodsId = inventoryDao.updateMoreNumByGoodsId(more_num, goodid);
		if( updateInventoryByGoodsId > 0 ){
			return updateInventoryByGoodsId;
		}
		return 0;
	}

	@Override
	@Transactional
	public int updateInventoryByGoodsId(Integer num, Integer more_num, Long goods_id) {
		int updateInventoryByGoodsId = inventoryDao.updateInventoryByGoodsId(num, more_num, goods_id);
		if( updateInventoryByGoodsId > 0 ){
			return updateInventoryByGoodsId;
		}
		return 0;
	}

	@Override
	public Inventory lock(Long goodId) {
		return inventoryDao.lock(goodId);
	}

}

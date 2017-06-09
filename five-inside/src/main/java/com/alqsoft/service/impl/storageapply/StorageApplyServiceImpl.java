package com.alqsoft.service.impl.storageapply;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.storageapply.StorageApplyMapper;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.operationinventoryrecord.OperationInventoryRecord;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.entity.storageapply.StorageApply;
import com.alqsoft.hibernatedao.good.GoodDao;
import com.alqsoft.hibernatedao.inventory.InventoryDao;
import com.alqsoft.hibernatedao.operationinventoryrecord.OperationInventoryRecordDao;
import com.alqsoft.hibernatedao.storageapply.StorageApplyDao;
import com.alqsoft.service.storageapply.StorageApplyService;
import com.alqsoft.utils.ArgsBean;
import com.alqsoft.utils.PoiExcelExport;

/**
 * 
 * @Description: 入库记录
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月12日 上午11:39:47 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional
public class StorageApplyServiceImpl implements StorageApplyService {

	@Resource
	private StorageApplyMapper storageApplyMappger;
	@Resource
	private StorageApplyDao storageApplyDao;

	@Resource
	private InventoryDao inventoryDao;

	@Resource
	private GoodDao goodDao;
	@Resource
	private OperationInventoryRecordDao operationInventoryRecordDao;

	@Override
	public List<Map<String, Object>> findStorageApplys(ArgsBean bean) {
		bean.InitializeFilter();
		bean.setTotalRecords(storageApplyMappger.findStorageApplysCount(bean));
		return storageApplyMappger.findStorageApplys(bean);
	}

	@Override
	public Result repeal(Long sid) {

		try {

			StorageApply storageApply = storageApplyDao.findOne(sid);
			if (storageApply.getStates() != 0) {
				return SpringMVCUtils.returnError("请勿重新提交!");
			}
			storageApply.setStates(2);
			return SpringMVCUtils.returnSuccess("操作成功!");
		} catch (Exception e) {
			return SpringMVCUtils.returnError("操作失败!");
		}

	}

	@Override
	public Result allow(Long sid, HttpSession session) {

		StorageApply storageApply = storageApplyDao.findOne(sid);

		Rolet attribute = (Rolet) session.getAttribute("rolet");
		if (attribute == null) {
			return SpringMVCUtils.returnError("异常：请重新登录!");
		}
		if (storageApply != null) {
			Long goodId = storageApply.getGoodId();
			Good good = goodDao.findOne(goodId);

			Inventory inv = inventoryDao.getInventoryByGoodsId(goodId);
			if (inv != null) {
				if (storageApply.getStates() != 0) {
					return SpringMVCUtils.returnError("请勿重新提交!");
				}
				Integer m1 = storageApply.getNum();
				Integer m2 = inv.getNum();
				inv.setNum(m1 + m2);

			} else {
				inv = new Inventory();
				inv.setBarCode(good.getBarCode());
				inv.setCreatedTime(new Date());
				inv.setNum(storageApply.getNum());
				inv.setGoodsId(goodId);
				inv.setMoreNum(0);
				inventoryDao.save(inv);
			}

			OperationInventoryRecord o = new OperationInventoryRecord();
			o.setInventoryId(inv.getId());
			o.setBarCode(inv.getBarCode());
			o.setCreatedTime(new Date());
			o.setNum(storageApply.getNum());
			o.setUserId(attribute.getId());
			o.setCurrentInventoryNum(inv.getNum());
			o.setOperationType(0);
			operationInventoryRecordDao.save(o);
			storageApply.setStates(1);
			return SpringMVCUtils.returnSuccess("操作成功!");
		}

		return SpringMVCUtils.returnError("找不到申请记录!");
	}

	@Override
	public Object exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page) {

		page.setOffset(0);
		page.InitializeFilter();
		page.setRows(storageApplyMappger.findStorageApplysCount(page));

		List<Map<String, Object>> storageApplys = storageApplyMappger.findStorageApplys(page);

		String[] datas = { "序号", "商品ID", "供应商", "商品名称", "商品单价", "商品数量", "申请时间", "当前状态" };
		int[] clumnWidth = { 20, 20, 35, 35, 35, 18, 20, 20 };
		String[] header = { "ids", "good_id", "NAME", "goodname", "good_money", "num", "created_time", "states" };

		PoiExcelExport poi = new PoiExcelExport();
		Long count = (long) 1;
		for (Map<String, Object> n : storageApplys) {
			Object object = n.get("states");
			if (object.toString().equals("1")) {
				n.put("states", "已审批");
			}
			if (object.toString().equals("2")) {
				n.put("states", "已撤销");
			}
			if (object.toString().equals("0")) {
				n.put("states", "未审批");
			}
			n.put("ids", count);
			count++;

		}

		return poi.export(request, response, datas, header, clumnWidth, storageApplys, "入库审批_");
	}
}

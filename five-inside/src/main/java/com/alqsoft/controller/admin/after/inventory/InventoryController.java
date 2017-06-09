package com.alqsoft.controller.admin.after.inventory;

import javax.annotation.Resource;

import org.alqframework.interceptor.AvoidDuplicateSubmission;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.relationship.RelationShip;
import com.alqsoft.entity.storageapply.StorageApply;
import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.inventory.InventoryService;
import com.alqsoft.service.relationship.RelationShipService;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: 仓库商品管理
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月8日 下午5:27:44 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Controller
@RequestMapping("admin/after/inventory")
public class InventoryController {
	@Resource
	private InventoryService inventoryService;
	@Resource
	private GoodService goodService;

	@Resource
	private RelationShipService relationshipService;

	// 前往商品入库
	@RequestMapping("toInsertProPager")
	public String toInsertProPager() {
		return "after/librarytube/repertory_home";
	}

	// 前往仓库首页
	@RequestMapping("toInventoryHome")
	public String toInventoryHome() {
		return "after/librarytube/common/librarytube-main";
	}

	// 申请入库
	@RequestMapping("add")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result insertProductToStorageApply(StorageApply storageApply,
			@RequestParam(required = true) String barCode,String token) {

		if (storageApply != null) {
			Good good = goodService.getGoodByBarCode(barCode);
			if (good == null) {
				return SpringMVCUtils.returnError(" 数据库中找不到对应商品!");
			}
			RelationShip relationShip = relationshipService.getRelationShipByGoodID(good.getId());
			Integer num = storageApply.getNum();
			if(num<=0){
				return SpringMVCUtils.returnError(" 数量不能为0!");
			}
			if (relationShip == null || relationShip.getProviderID() == null) {
				return SpringMVCUtils.returnError(" 请先绑定供应商!");
			}

			storageApply.setGoodId(good.getId());
			storageApply.setGoodMoney(good.getGoodMoney());
			storageApply.setGoodname(good.getGoodName());
			storageApply.setStates(0);
			StorageApply s = inventoryService.insertStorageApply(storageApply);
			if (s != null) {
				return SpringMVCUtils.returnSuccess("操作成功!");
			}
		}
		return SpringMVCUtils.returnError("操作失败!");
	}

	// 库存查询

	@RequestMapping("inventoryquery")
	public String queryRepertoryPager(Model model, ArgsBean page) {
		page.setTotalRecords(inventoryService.findInventoryQuiryCount(page));
		page.setData(inventoryService.findInventoryQuiry(page));
		model.addAttribute("list", page);
		return "after/librarytube/repertory_query";

	}

	// 库存记录
	@RequestMapping("inventoryrecord")
	public String queryRepertoryRecord(Model model, ArgsBean page) {
		int count = inventoryService.findInventoryRecordsCount(page);
		page.setTotalRecords(count);
		page.setData(inventoryService.findInventoryRecords(page));
		model.addAttribute("list", page);
		return "after/librarytube/repertory_record";

	}

	// 入库记录
	@RequestMapping("storagerecord")
	public String queryStorageRecord(Model model, ArgsBean page) {

		page.setTotalRecords(inventoryService.findStoreRecordingCount(page));
		page.setData(inventoryService.findStoreRecording(page));
		model.addAttribute("list", page);
		return "after/librarytube/repertory_storagerecord";
	}

	@RequestMapping("getGoods")
	@ResponseBody
	public Good getGoods(String barCode) {
		Good goodByBarCode = goodService.getGoodByBarCode(barCode);
		return goodByBarCode;
	}
}

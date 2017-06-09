package com.alqsoft.service.impl.stocktakeapply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alqsoft.dao.stocktakeapply.StockTakeApplyMyBatisDao;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.stocktakeapply.StockTakeApply;
import com.alqsoft.entity.stocktakerecord.StockTakeRecord;
import com.alqsoft.hibernatedao.stocktakeapply.StockTakeApplyDao;
import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.inventory.InventoryService;
import com.alqsoft.service.stocktakeapply.StockTakeApplyService;
import com.alqsoft.service.stocktakerecord.StockTakeRecordService;

/**
 * 
* @ClassName: StockTakeApplyServiceImpl 
* @Description: 盘存申请记录实现类
* @author 腾卉 
* @date 2017年5月8日 下午2:17:06 
*
 */
@Service
@Transactional(readOnly=true)
public class StockTakeApplyServiceImpl implements StockTakeApplyService{
	private static Logger logger=LoggerFactory.getLogger(StockTakeApplyServiceImpl.class);
	@Autowired
	private StockTakeApplyDao stockTakeApplyDao;
	@Autowired
	private StockTakeApplyMyBatisDao stockTakeApplyMyBatisDao;
	@Autowired
	private GoodService goodService;
	@Autowired
	public InventoryService inventoryService;
	@Autowired
	public StockTakeRecordService stockTakeRecordService;
	
	@Override
	public boolean delete(Long arg0) {
		stockTakeApplyDao.delete(arg0);
		return true;
	}

	@Override
	public StockTakeApply get(Long arg0) {
		return stockTakeApplyDao.findOne(arg0);
	}

	@Override
	public StockTakeApply saveAndModify(StockTakeApply arg0) {
		return stockTakeApplyDao.save(arg0);
	}

	@Override
	@Transactional
	public Result saveStockTakeApply(StockTakeApply stockTakeApply) {
		Result result = new Result();
		try{
			Long goodId = stockTakeApply.getGoodId();
			String goodName = stockTakeApply.getGoodname();
			Integer num = stockTakeApply.getNum();
			String remark = stockTakeApply.getRemark();
			Double goodMoney = stockTakeApply.getGoodMoney();
			String handleType = stockTakeApply.getHandleType();
			if ( goodId == null ) {
				return ResultUtils.returnError("商品ID不能为空!");
			}
			Inventory inventoryByGoodId = inventoryService.getInventoryByGoodId(goodId);
			if( inventoryByGoodId == null ){
				return ResultUtils.returnError("申请失败,请检查商品是否入库!");
			}
			if ( StringUtils.isBlank(goodName) ) {
				return ResultUtils.returnError("商品名称不能为空!");
			}
			if ( num == null ) {
				return ResultUtils.returnError("商品数量不能为空!");
			}
			if ( StringUtils.isBlank(remark) ) {
				return ResultUtils.returnError("备注不能为空!");
			}
			if ( goodMoney < 0 || goodMoney == null  ) {
				return ResultUtils.returnError("商品价格不能为空且大于零!");
			}
			if ( StringUtils.isBlank(handleType) ) {
				return ResultUtils.returnError("处理类型不能为空!");
			}
			Inventory inventory = inventoryService.getInventoryByGoodId(goodId);//根据商品id查看库存表
			Integer inventoryNum = inventory.getNum();
			if( stockTakeApply.getNum() < 0 ){
				if( inventoryNum == null ){
					inventoryNum=0;
				}
				if ( inventoryNum + stockTakeApply.getNum() < 0 ) {
					return ResultUtils.returnError("库存不足,盘差失败!");
				}
			}
			stockTakeApply.setStates(0);
			stockTakeApplyDao.save(stockTakeApply);
			result.setCode(1);
			result.setMsg("申请成功");
		}catch (Exception e) {
			result.setCode(0);
			result.setMsg("申请失败");
			logger.error("===============>申请失败");
			logger.error(e.toString());
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Result getGoodByBarCode(String barCode) {
		Result result = new Result();
		Good good = goodService.getGoodByBarCode(barCode);
		if( good != null ){
			result.setCode(1);
			result.setMsg("成功查询");
			result.setContent(good);
		}else{
			result.setCode(0);
			result.setMsg("查询失败");
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> findAll(String begin_time,String end_time,Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return stockTakeApplyMyBatisDao.findAll(map);
	}

	@Override
	public int getCount(String begin_time,String end_time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		int count = stockTakeApplyMyBatisDao.getCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	@Transactional
	public Result updateStates(Integer States, Long id) {
		if( States == null ){
			return ResultUtils.returnError("状态不能为空!");
		}
		if( id == null ){
			return ResultUtils.returnError("id不能为空!");
		}
		if("2".equals(States+"")){
			stockTakeApplyDao.updateStates(States, id);//撤销修改状态
			return ResultUtils.returnSuccess("操作成功!");
		}
		StockTakeApply stockTakeApply = stockTakeApplyDao.findOne(id);
		Long good_id = stockTakeApply.getGoodId();
		Inventory inventory = new Inventory();
		inventory = inventoryService.getInventoryByGoodId(good_id);//根据商品id查看库存表
		if( inventory != null && stockTakeApply != null){
			try {
				//操作数量小于0,更新库存表中商品数量,否则更新盘余库存数量
				Integer num = inventory.getNum();
				if( stockTakeApply.getNum() < 0 ){
					if( num == null ){
						num=0;
					}
					if ( num + stockTakeApply.getNum()<0 ) {
						return ResultUtils.returnError("库存不足,盘差失败!");
					}
					num = num+stockTakeApply.getNum();
					inventoryService.updateInventoryByGoodsId(num, inventory.getMoreNum(), good_id);
				}else {
					Integer moreNum = inventory.getMoreNum();
					if( moreNum == null ){
						moreNum=0;
					}
					Integer more_num = moreNum+stockTakeApply.getNum();
					inventoryService.updateInventoryByGoodsId(inventory.getNum(), more_num, good_id);
				}
				//保存盘存记录
				StockTakeRecord stockTakeRecord = new StockTakeRecord();//盘存记录实体
				inventory = inventoryService.getInventoryByGoodId(good_id);//根据商品id查看库存表
				stockTakeRecord.setInventoryId(inventory.getId());
				stockTakeRecord.setBarCode(inventory.getBarCode());
				stockTakeRecord.setNum(stockTakeApply.getNum());
				stockTakeRecord.setRemainNum(num);
				stockTakeRecord.setRemark(stockTakeApply.getRemark());
				stockTakeRecordService.saveAndModify(stockTakeRecord);
				stockTakeApplyDao.updateStates(States, id);//更改盘存审批状态
				return ResultUtils.returnSuccess("操作成功!");
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ResultUtils.returnError("操作失败,数据回滚!");
			}
		}else {
			return ResultUtils.returnError("操作失败!请检查商品是否入库!");
		}
	}

	@Override
	public List<Map<String, Object>> findAllStockRecord(String Goodname, String BarCode, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("Goodname", Goodname);
		map.put("BarCode", BarCode);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return stockTakeApplyMyBatisDao.findAllStockRecord(map);
	}

	@Override
	public int getStockRecordCount(String Goodname, String BarCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Goodname", Goodname);
		map.put("BarCode", BarCode);
		int count = stockTakeApplyMyBatisDao.getStockRecordCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> findAllApplyRecord(String begin_time, String end_time, Integer page,
			Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return stockTakeApplyMyBatisDao.findAll(map);
	}

	@Override
	public int getApplyRecordCount(String begin_time, String end_time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		int count = stockTakeApplyMyBatisDao.getCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public Result getGoodByBarCodeBe(String barCode) {
		Result result = new Result();
		Good good = goodService.getGoodByBarCodeBe(barCode);
		if( good != null ){
			result.setCode(1);
			result.setMsg("成功查询");
			result.setContent(good);
		}else{
			result.setCode(0);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	
}

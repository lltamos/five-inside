package com.alqsoft.service.impl.salesreturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alqsoft.dao.salesreturn.SalesReturnMapper;
import com.alqsoft.entity.goodsreturnrecord.GoodsReturnRecord;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.ordergoods.OrderGoods;
import com.alqsoft.init.InitParams;
import com.alqsoft.service.goodreturnrecord.GoodReturnService;
import com.alqsoft.service.inventory.InventoryService;
import com.alqsoft.service.ordergoods.OrderGoodsService;
import com.alqsoft.service.salesreturn.SalesReturnService;
import com.alqsoft.utils.HttpRequestUtils;
/**
 * 
* @ClassName: SalesReturnServiceImpl 
* @Description: 退货管理impl
* @author 腾卉 
* @date 2017年5月16日 下午4:23:44 
*
 */
@Service
@Transactional(readOnly=true)
public class SalesReturnServiceImpl implements SalesReturnService {

	@Autowired
	private SalesReturnMapper salesReturnMapper;
	@Autowired
	private GoodReturnService goodReturnRecordService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private InitParams initParams;
	
	private static Logger logger = LoggerFactory.getLogger(SalesReturnServiceImpl.class);

	
	@Override
	public List<Map<String, Object>> findAllGoodsReturn(String order_num, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("order_num", order_num);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return salesReturnMapper.findAllGoodsReturn(map);
	}

	@Override
	public int getGoodsReturnCount(String order_num) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_num", order_num);
		int count = salesReturnMapper.getGoodsReturnCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> findAllReturnRecord(String good_name, String order_no, Integer page,
			Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("good_name", good_name);
		map.put("order_no", order_no);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return salesReturnMapper.findAllReturnRecord(map);
	}

	@Override
	public int getReturnRecordCount(String good_name, String order_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("good_name", good_name);
		map.put("order_no", order_no);
		int count = salesReturnMapper.getReturnRecordCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	@Transactional
	public Result goodsReturn(Long userid,Integer return_num,String remark,Long order_goods_id) {
		Result result = new Result();
		if( userid == null ){
			return ResultUtils.returnError("用户id不能为空");
		}
		if( return_num == null ){
			return ResultUtils.returnError("退货数量不能为空");
		}
		if( StringUtils.isBlank(remark)){
			return ResultUtils.returnError("备注不能为空");
		}
		if( order_goods_id == null ){
			return ResultUtils.returnError("订单商品ID不能为空");
		}
		OrderGoods orderGoods = orderGoodsService.get(order_goods_id);
		Integer shopType = 0;
		String orderNum = "";
		Integer remainNum = 0;
		Double goodMoney = 0.0;
		Long goodId = 0L;
		if(orderGoods!=null){
			shopType = orderGoods.getShopType();
			orderNum = orderGoods.getOrderNum();
			remainNum = orderGoods.getRemainNum();
			goodMoney = orderGoods.getGoodMoney();
			goodId = orderGoods.getGoodId();
			//数据加锁
			inventoryService.lock(goodId);
			orderGoodsService.lock(orderNum, goodId);
		}else{
			return ResultUtils.returnError("没有此订单！");
		}
		if( return_num > remainNum ){
			return ResultUtils.returnError("退货数量不能大于剩余数量");
		}
		int goodsReturnRecordByOrderNo = goodReturnRecordService.getGoodsReturnRecordByOrderNo(orderNum+"_"+goodId);
		if( goodsReturnRecordByOrderNo > 0 ){
			return ResultUtils.returnError("退货记录已经存在,不支持重复退货！");
		}
		double money = goodMoney*return_num;
		if( shopType == 0){
			//新业态订单退货
			String msg_url = initParams.getProperties().getProperty("xyt_server");
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("serviceName","OrderRevoke"));
			list.add(new BasicNameValuePair("userid",userid+""));
			list.add(new BasicNameValuePair("money",money+""));
			list.add(new BasicNameValuePair("orderno",orderNum+"_"+goodId));
			logger.info("新业态请求地址是："+msg_url);
			logger.info("请求参数是："+list);
			Map<String, Object> map = HttpRequestUtils.httpPost(msg_url, list);
			if( map == null ){
				return ResultUtils.returnError("退货失败");
			}
			if ( "1".equals(map.get("state") + "") ) {
				logger.info("新业态退货接口返回参数是："+map);
				return ResultUtils.returnError(map.get("error").toString());
			}
			if ( "0".equals(map.get("state") + "") ) {
				Result saverecord = this.saverecord(order_goods_id, remark, return_num);
				if(saverecord.getCode() == 1){
					return ResultUtils.returnSuccess("退货成功!");
				}else{
					return ResultUtils.returnSuccess("退货成功,保存记录并更新库存失败！");
				}
			}
		}
		if( shopType == 1){
			//本地报单订单退货
			String msg_url = initParams.getProperties().getProperty("bdbd_server");
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("serviceName","OrderRevoke"));
			list.add(new BasicNameValuePair("userid",userid+""));
			list.add(new BasicNameValuePair("money",money+""));
			list.add(new BasicNameValuePair("orderno",orderNum+"_"+goodId));
			logger.info("本地报单请求地址是："+msg_url);
			logger.info("请求参数是："+list);
			Map<String, Object> map = HttpRequestUtils.httpPost(msg_url, list);
			if( map == null ){
				return ResultUtils.returnError("退货失败");
			}
			if ( "1".equals(map.get("state") + "") ) {
				logger.info("本地报单退货接口返回参数是："+map);
				return ResultUtils.returnError(map.get("error").toString());
			}
			if ( "0".equals(map.get("state") + "") ) {
				Result saverecord = this.saverecord(order_goods_id, remark, return_num);
				if(saverecord.getCode() == 1){
					return ResultUtils.returnSuccess("退货成功!");
				}else{
					return ResultUtils.returnSuccess("退货成功,保存记录并更新库存失败！");
				}
			}
		} 
		return result;
	}

	@Transactional
	@Override
	public Result saverecord(Long order_goods_id, String remark, Integer return_num) {
//		修改用户余额------接口中实现
//		添加退货记录------已经实现
//		修改商品购买记录剩余数量----根据订单号，修改orderGoodsT里的RemainNum-return_num
//		修改库存数量-----根据GoodID找到InventoryT里对应的ID，然后根据这个ID修改Inventory里的MoreNum（退货全加到MoreNum中）
		Result result = new Result();
		OrderGoods orderGoods = orderGoodsService.get(order_goods_id);
		if( orderGoods != null){
			Long id = orderGoods.getId();
			String goodName = orderGoods.getGoodName();
			Double goodMoney = orderGoods.getGoodMoney();
//			Integer goodNum = orderGoods.getGoodNum();
			Long goodId = orderGoods.getGoodId();
			String orderNum = orderGoods.getOrderNum();
			Integer shopType = orderGoods.getShopType();
			Integer remainNum = orderGoods.getRemainNum();
			try {
				GoodsReturnRecord goodsReturnRecord = new GoodsReturnRecord();
				goodsReturnRecord.setOrderGoodsId(id);
				goodsReturnRecord.setGoodName(goodName);
				goodsReturnRecord.setGoodMoney(goodMoney);
				goodsReturnRecord.setGoodNum(remainNum);
				goodsReturnRecord.setGoodId(goodId);
				goodsReturnRecord.setOrderNo(orderNum+"_"+goodId);
				goodsReturnRecord.setRemark(remark);
				goodsReturnRecord.setReturnNum(return_num);
				goodsReturnRecord.setShopType(shopType);
				Inventory inventoryByGoodId = inventoryService.getInventoryByGoodId(goodId);//查出商品库存
				Integer oldmoreNum = 0;
				if( inventoryByGoodId.getMoreNum() != null ){
					oldmoreNum = inventoryByGoodId.getMoreNum();
				}
				Integer more_num = oldmoreNum+return_num;
				Integer remain_num = remainNum - return_num;
				logger.info("原始盘余库存数量"+oldmoreNum);
				logger.info("退货数量"+return_num);
				logger.info("原始商品购买记录剩余数量"+remainNum);
				logger.info("修改后商品购买记录剩余数量"+remain_num);
				GoodsReturnRecord saveAndModify = goodReturnRecordService.saveAndModify(goodsReturnRecord);//保存退货记录
				int byGoodsId = inventoryService.updateMoreNumByGoodsId(more_num, goodId);//更改盘余数量
				int updateRemainNumByOrderNum = orderGoodsService.updateRemainNumByOrderNumAndGoodId(remain_num, orderNum,goodId);//修改商品购买记录剩余数量
				if(saveAndModify != null && byGoodsId > 0 && updateRemainNumByOrderNum > 0){
					return ResultUtils.returnSuccess("记录保存成功并更改库存数量");
				}
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ResultUtils.returnError("操作失败");
			}
		}else{
			return ResultUtils.returnError("订单不存在!");
		}
		return result;
	}

	

}

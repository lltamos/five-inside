package com.alqsoft.service.impl.payservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.operationinventoryrecord.OperationInventoryRecord;
import com.alqsoft.entity.ordergoods.OrderGoods;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.entity.shoporder.ShopOrder;
import com.alqsoft.entity.shoppingcart.ShoppingCart;
import com.alqsoft.hibernatedao.inventory.InventoryD;
import com.alqsoft.hibernatedao.inventory.InventoryDao;
import com.alqsoft.hibernatedao.operationinventoryrecord.OperationInventoryRecordDao;
import com.alqsoft.hibernatedao.ordergoods.OrderGoodsDao;
import com.alqsoft.hibernatedao.shoporder.ShopOrderDao;
import com.alqsoft.hibernatedao.shoppingcart.ShoppingCartDao;
import com.alqsoft.init.InitParams;
import com.alqsoft.service.payservice.PayService;
import com.alqsoft.utils.HttpRequestUtils;
import com.alqsoft.utils.MD5Util;


@Service
@Transactional(readOnly=false)
public class PayServiceImpl implements PayService{
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private ShopOrderDao shopOrderDao;
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private OperationInventoryRecordDao operationInventoryRecordDao;
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private InitParams initParams;
	@Autowired
	InventoryD inventoryD;
	@Override
	public Map<String, Object> goToPay(String userid,String mobile, Double money, String orderno ,String type) {
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		if(StringUtils.equals("1", type)){
			String valid1 = mobile+orderno+userid;
			String valid=MD5Util.MD5Encode(valid1, "UTF-8");
			String bdbd_server = initParams.getProperties().getProperty("bdbd_server");
			list.add(new BasicNameValuePair("serviceName","PayOrder"));
			list.add(new BasicNameValuePair("mobile",mobile));
			list.add(new BasicNameValuePair("valid",valid));
			list.add(new BasicNameValuePair("money",money+""));
			list.add(new BasicNameValuePair("orderno",orderno));
			list.add(new BasicNameValuePair("userid",userid));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(bdbd_server, list);
//			Map<String, Object> jdMap = new HashMap<>();
//			jdMap.put("state", 0);
			return jdMap ;
		}
		if(StringUtils.equals("0", type)){
			String valid1 = mobile+orderno+userid;
			String valid=MD5Util.MD5Encode(valid1, "UTF-8");
			String xyt_server = initParams.getProperties().getProperty("xyt_server");
			list.add(new BasicNameValuePair("serviceName","PayOrder"));
			list.add(new BasicNameValuePair("mobile",mobile));
			list.add(new BasicNameValuePair("valid",valid));
			list.add(new BasicNameValuePair("money",money+""));
			list.add(new BasicNameValuePair("orderno",orderno));
			list.add(new BasicNameValuePair("userid",userid));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(xyt_server, list);
//			Map<String, Object> jdMap = new HashMap<>();
//			jdMap.put("state", 0);
			return jdMap ;
		}
		return null;
	}

	@Override
	@Transactional
	public Result AfterPay(String userid, String mobile, Double money, String orderno, String type,
			List<Map<String, Object>> cartList,Rolet rolet) {
		for(Map<String, Object> map : cartList){
			Good goods = (Good)map.get("goods");
			Integer goodNum = Integer.parseInt(map.get("good_num")+"");
			Inventory inventory = inventoryD.inventoryGoodsId(goods.getId());
			
			Integer currentInventoryNum =null;
			if(null ==inventory.getMoreNum() || inventory.getMoreNum()<=0){
				Integer shenyu = inventory.getNum()-goodNum;
				currentInventoryNum=shenyu;
				inventory.setNum(currentInventoryNum);
				OrderGoods orderGoods = new OrderGoods();
				orderGoods.setCreatedTime(new Date());
				orderGoods.setGoodId(goods.getId());
				orderGoods.setGoodMoney(goods.getGoodMoney());
				orderGoods.setGoodName(goods.getGoodName());
				orderGoods.setGoodNum(goodNum);
				orderGoods.setOrderNum(orderno);
				orderGoods.setShopType(Integer.parseInt(type));
				orderGoods.setRemainNum(goodNum);
				orderGoods.setMinusType(0);
				OrderGoods save = orderGoodsDao.save(orderGoods);
				if(null == save){
					ResultUtils.returnError("添加商品订单失败!");
					break;
				}
				
				OperationInventoryRecord inventoryRecord = new OperationInventoryRecord();
				inventoryRecord.setUserId(rolet.getId());
				inventoryRecord.setBarCode(goods.getBarCode());
				inventoryRecord.setCreatedTime(new Date());
				inventoryRecord.setCurrentInventoryNum(currentInventoryNum);
				inventoryRecord.setInventoryId(inventory.getId());
				inventoryRecord.setNum(goodNum);
				inventoryRecord.setOperationType(1);
				inventoryRecord.setOrderNo(orderno);
				inventoryRecord.setShopType(Integer.parseInt(type));
				OperationInventoryRecord save3 = operationInventoryRecordDao.save(inventoryRecord);
				if(null == save3){
					ResultUtils.returnError("添加库存记录失败!");
					break;
				}
			}
			if(null !=inventory.getMoreNum()&& inventory.getMoreNum()>0 && inventory.getMoreNum()>=goodNum){
				Integer shenyu = inventory.getMoreNum()-goodNum;
				currentInventoryNum=inventory.getNum();
				inventory.setMoreNum(shenyu);
				OrderGoods orderGoods = new OrderGoods();
				orderGoods.setCreatedTime(new Date());
				orderGoods.setGoodId(goods.getId());
				orderGoods.setGoodMoney(goods.getGoodMoney());
				orderGoods.setGoodName(goods.getGoodName());
				orderGoods.setGoodNum(goodNum);
				orderGoods.setOrderNum(orderno);
				orderGoods.setShopType(Integer.parseInt(type));
				orderGoods.setRemainNum(goodNum);
				orderGoods.setMinusType(1);
				OrderGoods save = orderGoodsDao.save(orderGoods);
				if(null == save){
					ResultUtils.returnError("添加商品订单失败!");
					break;
				}
				OperationInventoryRecord inventoryRecord = new OperationInventoryRecord();
				inventoryRecord.setUserId(rolet.getId());
				inventoryRecord.setBarCode(goods.getBarCode());
				inventoryRecord.setCreatedTime(new Date());
				inventoryRecord.setCurrentInventoryNum(currentInventoryNum);
				inventoryRecord.setInventoryId(inventory.getId());
				inventoryRecord.setNum(goodNum);
				inventoryRecord.setOperationType(2);
				inventoryRecord.setOrderNo(orderno);
				inventoryRecord.setShopType(Integer.parseInt(type));
				OperationInventoryRecord save3 = operationInventoryRecordDao.save(inventoryRecord);
				if(null == save3){
					ResultUtils.returnError("添加库存记录失败!");
					break;
				}
			}
			if(null !=inventory.getMoreNum()&& inventory.getMoreNum()>0 && inventory.getMoreNum()<goodNum){
				//panyu 该库存中的盘余数
				Integer panyu =  inventory.getMoreNum();
				//moreshenyu 要扣除库存的数量
				Integer moreshenyu = goodNum-panyu;
				//num 该库存中的库存数
				Integer num =inventory.getNum();
				//i 购买后库存因该有点数量
				Integer i = num-moreshenyu;
				currentInventoryNum=i;
				inventory.setNum(i);
				inventory.setMoreNum(0);
				//生成扣库存的订单
				OrderGoods orderGoods = new OrderGoods();
				orderGoods.setCreatedTime(new Date());
				orderGoods.setGoodId(goods.getId());
				orderGoods.setGoodMoney(goods.getGoodMoney());
				orderGoods.setGoodName(goods.getGoodName());
				orderGoods.setGoodNum(moreshenyu);
				orderGoods.setOrderNum(orderno);
				orderGoods.setShopType(Integer.parseInt(type));
				orderGoods.setRemainNum(goodNum);
				orderGoods.setMinusType(0);
				//生成扣盘余订单
				OrderGoods ogs = new OrderGoods();
				ogs.setCreatedTime(new Date());
				ogs.setGoodId(goods.getId());
				ogs.setGoodMoney(goods.getGoodMoney());
				ogs.setGoodName(goods.getGoodName());
				ogs.setGoodNum(panyu);
				ogs.setOrderNum(orderno);
				ogs.setShopType(Integer.parseInt(type));
				ogs.setRemainNum(goodNum);
				ogs.setMinusType(1);
				OrderGoods save1 = orderGoodsDao.save(orderGoods);
				OrderGoods save2 = orderGoodsDao.save(ogs);
				if(null == save1 || null == save2){
					ResultUtils.returnError("添加商品订单失败!");
					break;
				}
				//生成扣库存的记录
				OperationInventoryRecord inventoryRecord = new OperationInventoryRecord();
				inventoryRecord.setUserId(rolet.getId());
				inventoryRecord.setBarCode(goods.getBarCode());
				inventoryRecord.setCreatedTime(new Date());
				inventoryRecord.setCurrentInventoryNum(currentInventoryNum);
				inventoryRecord.setInventoryId(inventory.getId());
				inventoryRecord.setNum(moreshenyu);
				inventoryRecord.setOperationType(1);
				inventoryRecord.setOrderNo(orderno);
				inventoryRecord.setShopType(Integer.parseInt(type));
				//生成扣盘余的记录
				OperationInventoryRecord oir = new OperationInventoryRecord();
				oir.setUserId(rolet.getId());
				oir.setBarCode(goods.getBarCode());
				oir.setCreatedTime(new Date());
				oir.setCurrentInventoryNum(currentInventoryNum);
				oir.setInventoryId(inventory.getId());
				oir.setNum(panyu);
				oir.setOperationType(2);
				oir.setOrderNo(orderno);
				oir.setShopType(Integer.parseInt(type));
				OperationInventoryRecord save3 = operationInventoryRecordDao.save(inventoryRecord);
				OperationInventoryRecord s = operationInventoryRecordDao.save(oir);
				if(null == save3 || null==s ){
					ResultUtils.returnError("添加库存记录失败!");
					break;
				}
				
			}
			Inventory save2 = inventoryDao.save(inventory);
			if(null == save2){
				ResultUtils.returnError("改变库存失败!");
				break;
			}
			ShoppingCart shoppingCart = shoppingCartDao.findOne(Long.parseLong(map.get("id")+""));
			shoppingCart.setStates(2);
			ShoppingCart save4 = shoppingCartDao.save(shoppingCart);
			if(null == save4){
				ResultUtils.returnError("删除购物车订单失败!");
				break;
			}
		}
		ShopOrder  shopOrder = new  ShopOrder();
		shopOrder.setCreatedTime(new Date());
		shopOrder.setGoodTotal(cartList.size());
		shopOrder.setOrderNum(orderno);
		shopOrder.setShopType(Integer.parseInt(type));
		shopOrder.setStates(3);
		shopOrder.setTotalMoney(money);
		shopOrder.setLogisiticsMoney(0.0);
		shopOrder.setLogisID(0L);
		shopOrder.setUserID(Long.parseLong(userid));
		ShopOrder save5 = shopOrderDao.save(shopOrder);
		if(null == save5){
			return ResultUtils.returnError("删除购物车订单失败!");
		}
		
		return ResultUtils.returnSuccess("支付成功!");
	}

}

package com.alqsoft.service.impl.shoporder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.pay.weixin.util.MD5Util;
import org.alqframework.result.Result;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alqsoft.dao.shoporder.ShopOrderMapper;
import com.alqsoft.init.InitParams;
import com.alqsoft.service.impl.salesreturn.SalesReturnServiceImpl;
import com.alqsoft.service.shoporder.ShopOrderService;
import com.alqsoft.utils.HttpRequestUtils;
/**
 * 
* @ClassName: ShopOrderServiceImpl 
* @Description: 店铺订单impl
* @author 腾卉 
* @date 2017年5月15日 下午2:01:30 
*
 */
@Service
@Transactional(readOnly=true)
public class ShopOrderServiceImpl implements ShopOrderService{

	@Autowired
	private ShopOrderMapper shopOrderDao;
	@Autowired
	private InitParams initParams;
	
	private static Logger logger = LoggerFactory.getLogger(SalesReturnServiceImpl.class);
	
	@Override
	public List<Map<String, Object>> findAllBDOrder(String mobile, Integer page, Integer rows) {
		//本地报单订单退货
		String name = "";
		String userid = "";
		String msg_url = initParams.getProperties().getProperty("bdbd_server");
		String newMobile = MD5Util.MD5Encode("ystzt"+mobile, "utf-8");
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("servicename","GetUserModelByMobile"));
		list.add(new BasicNameValuePair("mobile",mobile));
		list.add(new BasicNameValuePair("valid",newMobile));
		logger.info("本地报单请求地址是："+msg_url);
		logger.info("请求参数是："+list);
		Map<String, Object> map = HttpRequestUtils.httpPost(msg_url, list);
		if ( map == null || "1".equals(map.get("state") + "") ) {
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			return list2;
		}
		if ( map == null || "0".equals(map.get("state") + "") ) {
			JSONObject jsonObj = JSON.parseObject(map+"");
			Object data = jsonObj.get("data");
			JSONObject data1 = JSONObject.parseObject(data.toString());
			userid = data1.getString("id");
			name = data1.getString("name");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map1.put("userid", userid);
		map1.put("startIndex", index);
		map1.put("endIndex", rows);
		List<Map<String,Object>> findAllBDOrder = shopOrderDao.findAllBDOrder(map1);
		for(Map<String,Object> bd:findAllBDOrder){
			bd.put("name", name);
			bd.put("userid", mobile);
		}
		return findAllBDOrder;
	}

	@Override
	public int getBDOrderCount(String mobile) {
		String userid = "";
		String msg_url = initParams.getProperties().getProperty("bdbd_server");
		String newMobile = MD5Util.MD5Encode("ystzt"+mobile, "utf-8");
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("servicename","GetUserModelByMobile"));
		list.add(new BasicNameValuePair("mobile",mobile));
		list.add(new BasicNameValuePair("valid",newMobile));
		logger.info("本地报单请求地址是："+msg_url);
		logger.info("请求参数是："+list);
		Map<String, Object> map = HttpRequestUtils.httpPost(msg_url, list);
		if ( map == null || "1".equals(map.get("state") + "") ) {
			return 0;
		}
		if ( map == null || "0".equals(map.get("state") + "") ) {
			JSONObject jsonObj = JSON.parseObject(map+"");
			Object data = jsonObj.get("data");
			JSONObject data1 = JSONObject.parseObject(data.toString());
			userid = data1.getString("id");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("userid", userid);
		int count = shopOrderDao.getBDOrderCount(map1);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> findAllXYTOrder(String mobile, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("mobile", mobile);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return shopOrderDao.findAllXYTOrder(map);
	}

	@Override
	public int getXYTOrderCount(String mobile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		int count = shopOrderDao.getXYTOrderCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public Result checkDetails(String order_num) {
		Result result = new Result();
		List<Map<String, Object>> orderGoodsList = shopOrderDao.checkDetails(order_num);
		if( orderGoodsList != null ){
			result.setCode(1);
			result.setMsg("查询成功！");
			result.setContent(orderGoodsList);
		}else{
			result.setCode(0);
			result.setMsg("查询失败！");
		}
		return result;
	}

}

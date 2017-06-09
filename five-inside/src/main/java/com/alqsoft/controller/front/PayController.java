package com.alqsoft.controller.front;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.interceptor.AvoidDuplicateSubmission;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.DoubleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.service.cashier.CashierService;
import com.alqsoft.service.payservice.PayService;
import com.alqsoft.service.shoppingcart.ShoppingCartService;
/**
 * 
 * @Description: TODO
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月12日 上午11:11:31
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
@Controller
@RequestMapping("front/pay")
public class PayController {
	@Autowired
	CashierService cashierService;
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	PayService payService;
	@RequestMapping("to-invoice")
	@ResponseBody
	public Result toInvoice(@RequestParam(value="totalPrice")Double totalPrice,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		Object type = request.getSession().getAttribute("type");
		if(null == cashier || cashier.size()<=0||null ==type){
			return ResultUtils.returnError("登陆超时,请重新登陆!");
		}
		if(null ==totalPrice){
			return ResultUtils.returnError("订单价格不能为空!");
		}
		//查消费者的余额
		Object object = cashier.get("userid");
		Map<String, Object> balance = cashierService.getBalanceByUserId(object+"", type+"");
		if(null == balance || balance.size()<=0){
			return ResultUtils.returnError("获取用户商城余额获取有误!");
		}
		if(!StringUtils.equals("0",  balance.get("state")+"") ){
			return ResultUtils.returnError(balance.get("error")+"");
		}
		//获取该用户的购物车
		String userid = object+"";
		List<Map<String, Object>> cartList = shoppingCartService.getShoppingCartListByUserId(Long.parseLong(userid),0);
		Double price = (double) 0;
		if(null == cartList || cartList.size()<=0 ){
			return ResultUtils.returnError("请您先扫码添加商品!");
		}
		for(Map<String, Object> item : cartList){
			Double num = Double.parseDouble(item.get("good_num")+"") ;
			Good goods = (Good)item.get("goods");
			Double goodsMoney =  DoubleUtils.tworound(goods.getGoodMoney());
			Double val =  DoubleUtils.tworound(DoubleUtils.multiply(num, goodsMoney));
			price = DoubleUtils.tworound(DoubleUtils.add(price, val));
		}
		if(DoubleUtils.subtract(Double.parseDouble(balance.get("data")+""),price)<=0){
			return ResultUtils.returnError("亲,您的余额已不足!");
		}
		// 生成订单编号
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		Integer radomInt = new Random().nextInt(899999)+100000;
		String orderNo = str + radomInt;
		session.setAttribute("orderNo", orderNo);
		Map<String, Object> res = new HashMap<>();
		res.put("totalPrice", totalPrice);
		res.put("orderNo",orderNo);
		Result result = new Result();
		result.setCode(1);
		result.setContent(res);
		return result;
	}
	
	@RequestMapping("to-print-invoice")
	public String toPrintInvoice(@RequestParam(value="orderno")String orderno,
			@RequestParam(value="totalprice")String totalprice,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		Object type = request.getSession().getAttribute("type");
		if(null ==rolet || null == cashier || null ==type || cashier.size()<=0){
			return "redirect:/route/to-relogin";
		}
		//查消费者的余额
		Object object = cashier.get("userid");
		Map<String, Object> balance = cashierService.getBalanceByUserId(object+"", type+"");
		if(null == balance || balance.size()<=0){
			return "redirect:/route/to-relogin";
		}
		if(!StringUtils.equals("0",  balance.get("state")+"")){
			return "redirect:/route/to-relogin";
		}
		Double tes = DoubleUtils.tworound(Double.parseDouble(balance.get("data")+""));
		balance.replace("data", tes);
		//获取该用户的购物车
		String userid = object+"";
		List<Map<String, Object>> cartList = shoppingCartService.getShoppingCartListByUserId(Long.parseLong(userid),0);
		Double price = (double) 0;
		if(null == cartList || cartList.size()<=0 ){
			return "redirect:/front/cashier/to-productlist";
		}
		for(Map<String, Object> item : cartList){
			Double num = Double.parseDouble(item.get("good_num")+"") ;
			Good goods = (Good)item.get("goods");
			Double goodsMoney =  DoubleUtils.tworound(goods.getGoodMoney());
			Double val =  DoubleUtils.tworound(DoubleUtils.multiply(num, goodsMoney));
			price = DoubleUtils.tworound(DoubleUtils.add(price, val));
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(date);
		model.addAttribute("cartList", cartList);
		model.addAttribute("balance", balance);
		model.addAttribute("price", price);
		model.addAttribute("time", time);
		return "view/pay/invoice";
	}
	@RequestMapping("gotopay")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result goToPay(@RequestParam(value="orderno")String orderno,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@RequestParam(value = "token") String token){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		Object type = request.getSession().getAttribute("type");
		String order = request.getSession().getAttribute("orderNo")+"";
		session.removeAttribute("orderNo");
		if(null == cashier || cashier.size()<=0 ||null ==type){
			return ResultUtils.returnError("登陆超时,请重新登陆!");
		}
		if(null ==orderno){
			return ResultUtils.returnError("订单号不能为空!");
		}
		if(!StringUtils.equals(orderno, order)){
			return ResultUtils.returnError("操作有误!");
		}
		//查消费者的余额
		Object object = cashier.get("userid");
		Map<String, Object> balance = cashierService.getBalanceByUserId(object+"", type+"");
		if(null==balance || balance.size()<=0){
			return ResultUtils.returnError("获取用户商城余额获取有误!");
		}
		if(!StringUtils.equals("0",  balance.get("state")+"") ){
			return ResultUtils.returnError(balance.get("error")+"");
		}
		//获取该用户的购物车
		String userid = object+"";
		List<Map<String, Object>> cartList = shoppingCartService.getShoppingCartListByUserId(Long.parseLong(userid),0);
		Double price = (double) 0;
		if(null == cartList || cartList.size()<=0 ){
			return ResultUtils.returnError("请您先扫码添加商品!");
		}
		//获取购车中的商品价格
		for(Map<String, Object> item : cartList){
			Double num = Double.parseDouble(item.get("good_num")+"") ;
			Good goods = (Good)item.get("goods");
			Double goodsMoney =  DoubleUtils.tworound(goods.getGoodMoney());
			Double val =  DoubleUtils.tworound(DoubleUtils.multiply(num, goodsMoney));
			price = DoubleUtils.tworound(DoubleUtils.add(price, val));
		}
		//判断余额是否不足
		if(DoubleUtils.subtract(Double.parseDouble(balance.get("data")+""),price)<=0){
			return ResultUtils.returnError("亲,您的余额已不足!");
		}
		//调用支付接口
		Map<String, Object> payRes = payService.goToPay(userid,cashier.get("mobile")+"", price, orderno,type+"");
		if(null == payRes || payRes.size()<=0){
			return ResultUtils.returnError("亲,支付失败,请重新操作!");
		}
		//支付成功后对几个OrderGoodsT ShopOrderT OperationInventoryRecordT InventoryT 的添加和删除
		if(StringUtils.equals("0", payRes.get("state")+"")){
			return payService.AfterPay(userid, cashier.get("mobile")+"", price, orderno, type+"", cartList,rolet);
		}
		return ResultUtils.returnError(payRes.get("error")+"");
		
	}
	
	
}

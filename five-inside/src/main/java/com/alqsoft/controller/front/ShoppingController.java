package com.alqsoft.controller.front;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.shoppingcart.ShoppingCartService;
/**
 * 消费者购物
 * @Description: TODO
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月10日 下午4:13:25
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
@Controller
@RequestMapping("front/customer")
public class ShoppingController {
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	GoodService goodService;
	
	/**
	 * 删除购物车中的中的商品
	 */
	@RequestMapping("delshoppingcart")
	@ResponseBody
	public Result delshoppingcart(HttpServletRequest request, @RequestParam(value="id")Long id){
		//0是失败 1是成功
		Result result = new Result();
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		if(null == cashier || cashier.size()<=0){
			return ResultUtils.returnError("登陆超时,请重新登陆!");
		}
		if(null == id){
			return ResultUtils.returnError("操作有误!");
		}
		result = shoppingCartService.delShoppingCart(id);
		return result;
	}
	/**
	 * 向购物车中添加商品
	 */
	@RequestMapping("addshoppingcart")
	@ResponseBody
	public Result addshoppingcart(HttpServletRequest request, @RequestParam(value="barcode")String barCode){
		//0是失败 1是成功
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		if(null == cashier || cashier.size()<=0){
			return ResultUtils.returnError("登陆超时,请重新登陆!");
		}
		if(null == barCode || ""==barCode.trim()){
			return ResultUtils.returnError("条形码不能为空!");
		}
		long userid = Long.parseLong(cashier.get("userid")+"");
		
		return shoppingCartService.addshoppingcart(barCode, userid);
		
	}
	/**
	 * 在购物车中改变购买商品的数量
	 */
	@RequestMapping("updshoppingcart")
	@ResponseBody
	public Result updShoppingCart(HttpServletRequest request,
			@RequestParam(value="id")Long id,
			@RequestParam(value="num")Integer num){
		//0是失败 1是成功
				Result result = new Result();
		Map<String, Object> cashier = (Map<String, Object>) request.getSession().getAttribute("cashier");
		if(null == cashier || cashier.size()<=0){
			return ResultUtils.returnError("登陆超时,请重新登陆!");
		}
		result = shoppingCartService.updShoppingCart(id, num);
		return result;
	}
}

package com.alqsoft.controller.front;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.utils.DoubleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.SystemAccount;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.entity.shoppingcart.ShoppingCart;
import com.alqsoft.service.cashier.CashierService;
import com.alqsoft.service.shoppingcart.ShoppingCartService;

/**
 * 
 * @Description: 用户登陆
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月8日 下午3:38:52
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
@Controller
@RequestMapping("front/cashier")
public class CashierController {
	
	@Autowired
	CashierService cashierService;
	@Autowired
	ShoppingCartService shoppingCartService;
	
	/*@RequestMapping("to-cashierlogin")
	public String toCashierLogin(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		String success = "cashier/cashierlogin";
		String error = "view/login";
		return checked(request, session,success,error);
	}*/
/**
 * 跳转新业态登陆页面
 * @param request
 * @param response
 * @param session
 * @return
 */
	@RequestMapping("to-xytlogin")
	public String toXytlogin(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		String success = "view/cashier/xytlogin";
		String error = "view/login";
		return checked(request, session,success,error);
	}
	/**
	 * 跳转本地商城登陆页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("to-bdsclogin")
	public String toBdsclogin(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		String success = "view/cashier/bdsclogin";
		String error = "view/login";
		return checked(request, session,success,error);
	}
	
	@RequestMapping("cashierlogin")
	@ResponseBody
	public Result cashierLogin(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="mobile")String mobile,
			@RequestParam(value="newvalid") String newvalid,
			@RequestParam(value="type")String type,
			HttpSession session){
		session.removeAttribute("cashier");
		session.removeAttribute("type");
		//1是失败 0 是成功
		Result result = new Result();
		if(null==mobile || ""==mobile.trim()){
			result .setCode(1);
			result .setMsg("手机号不能为空!");
			return result; 
		}
		String reg="^1(3[0-9]|4[57]|5[0-35-9]|7[013678]|8[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(mobile);
		if(!matcher.matches()){
			result .setCode(1);
			result .setMsg("请输入正确的手机号码!");
			return result;
		}
		if(null==newvalid || ""==newvalid.trim()){
			result .setCode(1);
			result .setMsg("密码不能为空!");
			return result;
		}
		if(!StringUtils.equals("0", type+"") && !StringUtils.equals("1", type+"")){
			result .setCode(1);
			result .setMsg("操作有误!");
			return result;
		}
		result = cashierService.cashierLogin(mobile, newvalid, type,request);
		
		return result;
	}
	
	/**
	 * 跳转商品列表页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("to-productlist")
	public String toProductList(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		//判断session,是否登陆或者过期
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
		model.addAttribute("cartList", cartList);
		model.addAttribute("cashier", cashier);
		model.addAttribute("balance", balance);
		return "view/cashier/cashiershopping";
	}
	
	
	
	private String checked(HttpServletRequest request, HttpSession session,String success,String error) {
		Rolet rolet = (Rolet)request.getSession().getAttribute("rolet");
		session.removeAttribute("cashier");
		session.removeAttribute("type");
		if(null==rolet){
			return error;
		}
		if(StringUtils.equals("czy001", rolet.getName())){
			return success;
			/*return "cashier/cashierlogin";*/
		}
		if(StringUtils.equals("czy002", rolet.getName())){
			return success;
			}
		//如果后台没有登陆跳回首页
		return error;
	}
}

package com.alqsoft.controller.admin.after.shoporder;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.shoporder.ShopOrderService;

/**
 * 
* @ClassName: OrderController 
* @Description: 售后后台订单查询控制层
* @author 腾卉 
* @date 2017年5月15日 上午11:25:52 
*
 */
@Controller
@RequestMapping("admin/after/aftersales/shoporder")
public class ShopOrderController {

	@Autowired
	private ShopOrderService shopOrderService;
	
	/**
	 * 
	* @Title: findAllBDOrder 
	* @Description: 本地订单查询 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findAllBDOrder")
	public String findAllBDOrder(Model model, 
			@RequestParam(value = "remark",required=false,defaultValue="") String remark,
			@RequestParam(value = "mobile", required=false) String mobile,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Integer beforeTotalPage = 0;
		if(remark.equals("1")){
			model.addAttribute("currPage", 0);
			model.addAttribute("totalPage", 0);
			model.addAttribute("totalRecords", 0);
			model.addAttribute("bdOrderList", null);
		}else{
			List<Map<String,Object>> bdOrderList = shopOrderService.findAllBDOrder(mobile, page, rows);
			beforeTotalPage = shopOrderService.getBDOrderCount(mobile);
			double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
			Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
			model.addAttribute("currPage", page);
			model.addAttribute("totalPage", aftertTotalPage);
			model.addAttribute("totalRecords", beforeTotalPage);
			model.addAttribute("bdOrderList",bdOrderList);
		}
		return "after/aftersales/shoporder/bdorder";
	}
	/**
	 * 
	* @Title: findAllXYTOrder 
	* @Description: 新业态订单查询 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findAllXYTOrder")
	public String findAllXYTOrder(Model model, 
			@RequestParam(value = "remark",required=false,defaultValue="") String remark,
			@RequestParam(value = "mobile", required=false) String mobile,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Integer beforeTotalPage = 0;
		if(remark.equals("1")){
			model.addAttribute("currPage", 0);
			model.addAttribute("totalPage", 0);
			model.addAttribute("totalRecords", 0);
			model.addAttribute("xytOrderList", null);
		}else{
			List<Map<String,Object>> xytOrderList = shopOrderService.findAllXYTOrder(mobile, page, rows);
			beforeTotalPage = shopOrderService.getXYTOrderCount(mobile);
			double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
			Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
			model.addAttribute("currPage", page);
			model.addAttribute("totalPage", aftertTotalPage);
			model.addAttribute("totalRecords", beforeTotalPage);
			model.addAttribute("xytOrderList",xytOrderList);
		}
		return "after/aftersales/shoporder/xytorder";
	}
	/**
	 * 
	* @Title: checkDetails 
	* @Description: 订单查看详情
	* @return Result    返回类型 
	* @throws
	 */
	@RequestMapping("checkDetails")
	@ResponseBody
	public Result checkDetails(
			Model model, 
			@RequestParam(value = "order_num", required=false) String order_num,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		return shopOrderService.checkDetails(order_num);
	}
	
	
}

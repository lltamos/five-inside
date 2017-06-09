package com.alqsoft.controller.admin.after.salesreturn;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.interceptor.AvoidDuplicateSubmission;
import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.salesreturn.SalesReturnService;
/**
 * 
* @ClassName: SalesReturnController 
* @Description: 售后后台退货管理控制层
* @author 腾卉 
* @date 2017年5月16日 下午3:08:14 
*
 */
@Controller
@RequestMapping("admin/after/aftersales/salesreturn")
public class SalesReturnController {
	
	@Autowired
	private SalesReturnService salesReturnService;
	
	// 前往售后
	@RequestMapping("toaftersaleshome")
	public String toInventoryHome() {
		return "after/aftersales/common/aftersales-main";
	}
	/**
	 * 
	* @Title: findGoodsReturn 
	* @Description: 商品退货列表
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findGoodsReturn")
	public String findGoodsReturn(Model model,
			@RequestParam(value = "remark",required=false,defaultValue="") String remark,
			@RequestParam(value = "order_num", required = false) String order_num,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, HttpServletRequest request,
			HttpServletResponse response){
		Integer beforeTotalPage = 0;
		if(remark.equals("1")){
			model.addAttribute("currPage", 0);
			model.addAttribute("totalPage", 0);
			model.addAttribute("totalRecords", 0);
			model.addAttribute("goodsReturnList", null);
		}else{
			List<Map<String, Object>> goodsReturnList = salesReturnService.findAllGoodsReturn(order_num, page, rows);
			beforeTotalPage = salesReturnService.getGoodsReturnCount(order_num);
			double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
			Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
			model.addAttribute("currPage", page);
			model.addAttribute("totalPage", aftertTotalPage);
			model.addAttribute("totalRecords", beforeTotalPage);
			model.addAttribute("goodsReturnList", goodsReturnList);
		}
		return "after/aftersales/salesreturn/goodsreturn";
	}
	/**
	 * 
	* @Title: findAllReturnRecord 
	* @Description: 退货记录列表 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findAllReturnRecord")
	public String findAllReturnRecord(Model model,
			@RequestParam(value = "good_name", required = false) String good_name,
			@RequestParam(value = "order_no", required = false) String order_no,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, HttpServletRequest request,
			HttpServletResponse response){
		Integer beforeTotalPage = 0;
		List<Map<String, Object>> returnRecordList = salesReturnService.findAllReturnRecord(good_name, order_no, page, rows);
		beforeTotalPage = salesReturnService.getReturnRecordCount(good_name, order_no);
		double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
		Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", aftertTotalPage);
		model.addAttribute("totalRecords", beforeTotalPage);
		model.addAttribute("returnRecordList", returnRecordList);
		return "after/aftersales/salesreturn/returnrecord";
	}
	/**
	 * 
	* @Title: goodsReturn 
	* @Description: 调用退货接口 
	* @return Result    返回类型 
	* @throws
	 */
	@RequestMapping("goodsReturn")
	@AvoidDuplicateSubmission(needRemoveToken=true)
	@ResponseBody
	public Result goodsReturn(Model model,
			@RequestParam(value = "userid") Long userid,
			@RequestParam(value = "return_num") Integer return_num,
			@RequestParam(value = "remark") String remark,
			@RequestParam(value = "order_goods_id") Long order_goods_id,
			@RequestParam(value = "token") String token,
			HttpServletRequest request,HttpServletResponse response){
		return salesReturnService.goodsReturn(userid,return_num,remark,order_goods_id);
	}
}

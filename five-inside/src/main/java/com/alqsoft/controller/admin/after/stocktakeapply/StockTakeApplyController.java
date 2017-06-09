package com.alqsoft.controller.admin.after.stocktakeapply;

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

import com.alqsoft.entity.stocktakeapply.StockTakeApply;
import com.alqsoft.service.stocktakeapply.StockTakeApplyService;

/**
 * 
* @ClassName: StockTakeApplyController 
* @Description: 盘存管理控制层 
* @author 腾卉 
* @date 2017年5月8日 下午2:18:12 
*
 */
@Controller
@RequestMapping("admin/after/libraryTube/stocktakeapply")
public class StockTakeApplyController {

	@Autowired
	public StockTakeApplyService stockTakeApplyService;
	/**
	 * 
	* @Title: toStockTakeApply 
	* @Description: 跳转盘存管理页面
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("to-stockmanage")
	public String toStockManage(){
		return "after/librarytube/stocktakeapply/stockmanage";
	}
	/**
	 * 
	* @Title: insertStockTakeApply 
	* @Description: 商品盘存申请提交
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("save-stocktakeapply")
	@AvoidDuplicateSubmission(needRemoveToken=true)
	@ResponseBody
	public Result saveStockTakeApply(Model model,
			StockTakeApply stockTakeApply,
			@RequestParam(value = "token") String token,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		return this.stockTakeApplyService.saveStockTakeApply(stockTakeApply);
	}
	/**
	 * 
	* @Title: findGoodByBarCode 
	* @Description: 根据条形码查找商品信息 
	* @return Result    返回类型 
	* @throws
	 */
	@RequestMapping("findGoodByBarCode")
	@ResponseBody
	public Result findGoodByBarCode(Model model,
			@RequestParam(value = "BarCode", required=false) String BarCode,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		return stockTakeApplyService.getGoodByBarCode(BarCode);
	}
	/**
	 * 
	 * @Title: getGoodByBarCodeBe 
	 * @Description: 根据条形码查看商品是否入库 
	 * @return Result    返回类型 
	 * @throws
	 */
	@RequestMapping("getGoodByBarCodeBe")
	@ResponseBody
	public Result getGoodByBarCodeBe(Model model,
			@RequestParam(value = "BarCode", required=false) String BarCode,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		return stockTakeApplyService.getGoodByBarCodeBe(BarCode);
	}
	/**
	 * 
	* @Title: findAllStock 
	* @Description: 查看全部盘存记录 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findAllStock")
	public String findAllStock(Model model, 
			@RequestParam(value = "Goodname", required=false) String Goodname,
			@RequestParam(value = "BarCode", required=false) String BarCode,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Integer beforeTotalPage = 0;
		List<Map<String,Object>> stockRecordList = stockTakeApplyService.findAllStockRecord(Goodname, BarCode, page, rows);
		beforeTotalPage = stockTakeApplyService.getStockRecordCount(Goodname, BarCode);
		double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
		Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", aftertTotalPage);
		model.addAttribute("totalRecords", beforeTotalPage);
		model.addAttribute("stockRecordList",stockRecordList);
		return "after/librarytube/stocktakeapply/stockrecord";
	}
	/**
	 * 
	 * @Title: findAllStock 
	 * @Description: 查看全部盘申请记录 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("findAllApplyRecord")
	public String findAllApplyRecord(Model model, 
			@RequestParam(value = "begin_time", required=false) String begin_time,
			@RequestParam(value = "end_time", required=false) String end_time,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response) {
		if(begin_time != null){
			begin_time = begin_time+" 00:00:00";
		}
		if(end_time != null){
			end_time = end_time+" 23:59:59";
		}
		Integer beforeTotalPage = 0;
		List<Map<String,Object>> applyRecordList = stockTakeApplyService.findAllApplyRecord(begin_time, end_time, page, rows);
		beforeTotalPage = stockTakeApplyService.getApplyRecordCount(begin_time, end_time);
		double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
		Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", aftertTotalPage);
		model.addAttribute("totalRecords", beforeTotalPage);
		model.addAttribute("applyRecordList",applyRecordList);
		return "after/librarytube/stocktakeapply/applyrecord";
	}
	
}

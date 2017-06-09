package com.alqsoft.controller.admin.after.stockapproval;

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

import com.alqsoft.service.stocktakeapply.StockTakeApplyService;
/**
 * 
* @ClassName: StockApprovalController 
* @Description: 盘存审批控制层
* @author 腾卉 
* @date 2017年5月9日 下午2:41:32 
*
 */
@Controller
@RequestMapping("admin/after/financial/stockapproval")
public class StockApprovalController {

	@Autowired
	public StockTakeApplyService stockTakeApplyService;
	/**
	 * 
	* @Title: findAll 
	* @Description: 根据条件查询盘存记录
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findAll")
	public String findAll(Model model, 
			@RequestParam(value = "begin_time", required=false) String begin_time,
			@RequestParam(value = "end_time", required=false) String end_time,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Integer beforeTotalPage = 0;
//		if(begin_time != null){
//			begin_time = begin_time+" 00:00:00";
//		}
//		if(end_time != null){
//			end_time = end_time+" 23:59:59";
//		}
		List<Map<String,Object>> stockTakeApplyList = stockTakeApplyService.findAll(begin_time, end_time, page, rows);
		beforeTotalPage = stockTakeApplyService.getCount(begin_time, end_time);
		double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
		Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", aftertTotalPage);
		model.addAttribute("totalRecords", beforeTotalPage);
		model.addAttribute("stockTakeApplyList",stockTakeApplyList);
		return "after/financial/stockapproval/stockApproval";
	}
	/**
	 * 
	* @Title: updateStates 
	* @Description: 更改盘存审批状态  
	* @return Result    返回类型 
	* @throws
	 */
	@RequestMapping("updateStates")
	@AvoidDuplicateSubmission(needRemoveToken=true)
	@ResponseBody
	public Result updateStates(
			Model model, 
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "States") Integer States,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		return stockTakeApplyService.updateStates(States, id);
	}
	
	
	
}

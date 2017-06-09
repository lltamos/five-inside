package com.alqsoft.controller.admin.after.summarymanage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.interceptor.AvoidDuplicateSubmission;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.provider.Provider;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.service.financialdetailreport.FinancialDetailReportService;
import com.alqsoft.service.financialsummaryreport.FinancialSummaryReportService;
import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.provider.ProviderService;


/**
 * 
 * @Description: 财务后台:汇总管理
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月15日 下午6:47:47
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
@Controller
@RequestMapping("admin/after/financial/summarymanage")
public class SummaryManagement {
	@Autowired
	private FinancialSummaryReportService reportService ;
	@Autowired
	private FinancialDetailReportService detailReportService ;
	@Autowired
	private ProviderService ps;
	@Autowired
	private GoodService gs;
	
	@RequestMapping("findAll")
	public String findAll(Model model,
			@RequestParam(value = "begin_time", required=false) String begin_time,
			@RequestParam(value = "end_time", required=false) String end_time,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows,
			HttpServletRequest request,
			HttpServletResponse response) {
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return "/after/financial/outtime";
		}
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		params.put("page", (page-1)*rows);
		params.put("rows",rows);
		params.put("state", 2);
		if(null == begin_time|| null ==end_time || ""==begin_time || ""==end_time){
			params.put("cycle", null);
			map.put("cycle", null);
		}else{
			String cycle =begin_time+"-"+ end_time;
			params.put("cycle", cycle);
			map.put("cycle", cycle);
		}
		map.put("state", 2);
		List<Map<String, Object>> reportList = reportService.findSummeryReportList(params);
		Integer total = reportService.getSummeryReportTotal(map);
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("reportList", reportList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecords", total);
		model.addAttribute("begin_time", begin_time);
		model.addAttribute("end_time", end_time);
		return "after/financial/summarymanage/summaryall";
	}
	@RequestMapping("del")
	@ResponseBody
	public Result del(@RequestParam(value = "id", required=true) Long id,
			HttpServletRequest request,
			HttpServletResponse response){
		if(null == id){
			return ResultUtils.returnError("删除有误!");
		}
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return ResultUtils.returnError("删除有误!");
		}
		return reportService.delById(id);
	}
	
	@RequestMapping("initialize")
	@ResponseBody
	public Result initialize(@RequestParam(value = "id", required=true) Long id,
			HttpServletRequest request,
			HttpServletResponse response){
		if(null == id){
			return ResultUtils.returnError("初始化有误!");
		}
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return ResultUtils.returnError("您登陆超时,请重新登陆!");
		}
		Iterator<FinancialDetailReport> initialize = reportService.initialize(id, request, response);
		if(null == initialize || !initialize.hasNext() ){
			return ResultUtils.returnError("初始化有误!");
		}
	
		
		return 	reportService.createExcel(id,request, response);
	}
	@RequestMapping("checked")
	public String checked(Model model,@RequestParam(value = "id", required=true) Long id,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows,
			HttpServletRequest request,
			HttpServletResponse response){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return "/after/financial/outtime";
		}
		if(null == id){
			return "/after/financial/outtime";
		}
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		params.put("page", (page-1)*rows);
		params.put("rows",rows);
		params.put("state", 0);
		params.put("summaryId", id);
		map.put("state", 0);
		map.put("summaryId", id);
		List<Map<String, Object>> reportList = detailReportService.findDetailReportList(params);
		for(Map<String, Object> rl: reportList){
			if(null ==rl.get("provider_id") || null ==rl.get("goodid")){
				continue;
			}
			Long providerId = Long.parseLong(rl.get("provider_id")+"");
			Provider provider = ps.get(providerId);
			rl.put("provider", provider);
			
			Long goodId = Long.parseLong(rl.get("goodid")+"");
			Good good = gs.get(goodId);
			rl.put("goods", good);
			
		}
		Integer total = detailReportService.getDetailReportTotal(map);
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("sid", id);
		model.addAttribute("reportList", reportList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecords", total);
		return "after/financial/summarymanage/emailcheck";
		
	}
	@RequestMapping("export")
	@ResponseBody
	public Object export(@RequestParam(value = "id", required=true) Long id,
			HttpServletRequest request,
			HttpServletResponse response){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return ResultUtils.returnError("导出有误!");
		}
		return reportService.exportExcel(id, request, response);
		
	}
	@RequestMapping("sendemail")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result sendEmail(@RequestParam(value = "id", required=true) Long id,
			@RequestParam(value = "token") String token,
			HttpServletRequest request,
			HttpServletResponse response){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return ResultUtils.returnError("发送有误!");
		}
		if(null == id){
			return ResultUtils.returnError("发送有误!");
		}
		return detailReportService.sendEmail(id,request);
	}
	
	@RequestMapping("resendemail")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result reSendeMail(@RequestParam(value = "id", required=true) Long id,
							@RequestParam(value = "sid", required=true) Long sid,
							@RequestParam(value = "token") String token,
							HttpServletRequest request,
							HttpServletResponse response){
		Rolet rolet = (Rolet) request.getSession().getAttribute("rolet");
		if(null == rolet){
			return ResultUtils.returnError("发送有误!");
		}
		if(null == id){
			return ResultUtils.returnError("发送有误!");
		}
		return detailReportService.reSendEmail(id,sid,request);
	}
}

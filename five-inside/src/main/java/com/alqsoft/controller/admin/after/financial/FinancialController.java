package com.alqsoft.controller.admin.after.financial;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.financialsummaryreport.FinancialSummaryReport;
import com.alqsoft.service.financialstatement.FinancialStatementService;
import com.alqsoft.service.financialsummaryreport.FinancialSummaryReportService;
import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: 财务报表Controller
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月16日 下午3:54:10 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Controller
@RequestMapping("admin/after/financial")
public class FinancialController {
	@Resource
	private FinancialStatementService fss;

	@Resource
	private FinancialSummaryReportService financialSummaryReportServiceImpl;

	@RequestMapping("toupload")
	public String toUpload() {
		return "after/financial/upload";
	}

	// 查询财务报表
	@RequestMapping("findstatements")
	public String findStatements(Model model, ArgsBean page) {
		fss.findFinancialStatements(page);
		model.addAttribute("list", page);
		return "after/financial/financialstatement";
	}

	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page) {
		fss.exportExcel(request, response, page);
	}

	// xls文件上传
	@RequestMapping(value = "uploadsheet", method = RequestMethod.POST)
	@ResponseBody
	public Result upLoadSheet(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {
		try {
			String path = request.getSession().getServletContext().getRealPath("upload/xls/");
			String fileName = file.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			FinancialSummaryReport f = new FinancialSummaryReport();
			f.setCreatedTime(new Date());
			f.setDetailRoute("/upload/xls/" + fileName);
			FinancialSummaryReport fsr = financialSummaryReportServiceImpl.updataFinancialSummaryReport(f);

			return SpringMVCUtils.returnSuccess(fsr.getId() + "," + fileName);
		} catch (Exception e) {
			return SpringMVCUtils.returnError("操作失败!");
		}
	}

	@RequestMapping("addsheet")
	@ResponseBody
	public Result addSheet(@RequestParam(required = true) String startTime,
			@RequestParam(required = true) String endTime, @RequestParam(required = true) Long id) {
		FinancialSummaryReport report = financialSummaryReportServiceImpl.get(id);
		String cycle = startTime.trim() + "-" + endTime.trim();
		report.setCycle(cycle);
		report.setState(0);
		if (financialSummaryReportServiceImpl.updataFinancialSummaryReport(report) != null) {
			return SpringMVCUtils.returnSuccess("保存成功!");
		} else {
			return SpringMVCUtils.returnError("操作失败!");
		}

	}

}

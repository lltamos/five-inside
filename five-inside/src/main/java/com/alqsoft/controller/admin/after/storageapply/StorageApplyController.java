package com.alqsoft.controller.admin.after.storageapply;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.storageapply.StorageApplyService;
import com.alqsoft.utils.ArgsBean;
import com.alqsoft.utils.RoleSessionUtils;

/**
 * 
 * @Description: 入库审批
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月12日 上午11:29:12 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Controller
@RequestMapping("admin/after/storageapply")
public class StorageApplyController {

	@Resource
	private StorageApplyService storageApplyService;

	// 查询入库审批
	@RequestMapping("findStorageApplys")
	public String findStorageApplys(Model model, ArgsBean page) {
		page.setData(storageApplyService.findStorageApplys(page));
		model.addAttribute("list", page);
		return "after/financial/storageapply";
	}

	@RequestMapping("allow")
	@ResponseBody
	public Result allow(Long sid, HttpSession session) {
		return storageApplyService.allow(sid, session);
	}

	@RequestMapping("repeal")
	@ResponseBody
	public Result repeal(Long sid, HttpSession session) {

		if (RoleSessionUtils.isLogin(session)) {
			return storageApplyService.repeal(sid);
		}
		return SpringMVCUtils.returnError("异常：请重新登录!");

	}

	@RequestMapping("exportExcel")

	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page) {
		
	 	storageApplyService.exportExcel(request, response, page);
	}
}

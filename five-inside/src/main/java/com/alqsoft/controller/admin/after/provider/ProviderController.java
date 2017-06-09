package com.alqsoft.controller.admin.after.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.interceptor.AvoidDuplicateSubmission;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.provider.Provider;
import com.alqsoft.entity.provider.Provider.providerSubmit;
import com.alqsoft.service.provider.ProviderService;

/**
 * 厂商控制层
 * @author zj
 *
 */
@RequestMapping("admin/after/provider")
@Controller
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	/**
	 * 跳转添加厂商页面
	 * @return
	 */
	@RequestMapping("toProviderAddPage")
	public String toProviderAddPage(){
		return "after/chinamerchants/provider-add";
	}
	
	/**
	 * 到修改信息页面
	 * @return
	 */
	@RequestMapping("toProviderUpdatePage")
	public String toProviderUpdatePage(@RequestParam(value="providerId")Long providerId,Model model){
		Provider provider = providerService.get(providerId);
		model.addAttribute("provider",provider);
		return "after/chinamerchants/provider-update";
	}
	
	@RequestMapping("toBindingPage")
	public String toBindingPage(@RequestParam(value="providerId")Long providerId,Model model){
		Provider provider = providerService.get(providerId);
		model.addAttribute("provider",provider);
		return "after/chinamerchants/bindingpublic";
	} 
	
	/**
	 * 添加厂商
	 * @param provider
	 * @return
	 */
	@RequestMapping("providerAdd")
	@AvoidDuplicateSubmission(needRemoveToken=true)
	@ResponseBody
	public Result providerAdd(
			@RequestParam(value = "token") String token,
			@Validated(value=providerSubmit.class)Provider provider,
			BindingResult bindingResult){
		if(!bindingResult.hasErrors()){
			Result result = providerService.providerAdd(provider);
			return result;
		}else{
			return ResultUtils.returnError(bindingResult);
		}
	}
	
	/**
	 * 厂商列表
	 * @param page
	 * @param rows
	 * @param providerName
	 * @param startTime
	 * @param endTime
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("toProviderPageList")
	public String toProviderPageList(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows,
			@RequestParam(value="providerName",required=false)String providerName,
			@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpSession session, Model model){
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("startIndex", (page-1)*rows);
		param.put("endIndex",rows);
		param.put("providerName",providerName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		List<Map<String,Object>> providerList = providerService.findProviderPage(param);
		int pageCount = providerService.getProviderCount(param);
		double crslTotalPage = Math.ceil(Double.valueOf(pageCount/rows.doubleValue()));
		model.addAttribute("providerList", providerList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", new Double(crslTotalPage).intValue());
		model.addAttribute("totalRecords", pageCount);
		return "after/chinamerchants/provider-list";
	}
	
	
	/**
	 * 添加关联列表
	 * @param page
	 * @param rows
	 * @param goodName
	 * @param barCode
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("toAssociatedPage")
	public String toAssociatedPage(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows,
			@RequestParam(value="goodName",required=false)String goodName,
			@RequestParam(value="barCode",required=false)String barCode,
			HttpSession session,HttpServletRequest request, Model model){
		
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("startIndex", (page-1)*rows);
		param.put("endIndex",rows);
		param.put("goodName",goodName);
		param.put("barCode",barCode);
		List<Map<String,Object>> associatedlist = providerService.findAssociatedPage(param);
		int pageCount = providerService.getAssociatedCount(param);
		double crslTotalPage = Math.ceil(Double.valueOf(pageCount/rows.doubleValue()));
		model.addAttribute("associatedList", associatedlist);
		String providerId = request.getParameter("providerId");
		if(null==providerId||""==providerId){
			String parameter = request.getParameter("proName");
			model.addAttribute("proName", parameter);
		}else{
			Provider provider = providerService.get(Long.parseLong(providerId+""));
			model.addAttribute("proName", provider.getName());
		}
		
		model.addAttribute("providerId", providerId);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", new Double(crslTotalPage).intValue());
		model.addAttribute("totalRecords", pageCount);
		return "after/chinamerchants/associatedgood";
	}
	
	/**
	 * 取消关联列表
	 * @param page
	 * @param rows
	 * @param goodName
	 * @param barCode
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("toShowAssociatedPage")
	public String toAssociatedCancelPage(
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="10")Integer rows,
			@RequestParam(value="goodName",required=false)String goodName,
			@RequestParam(value="barCode",required=false)String barCode,
			HttpSession session,HttpServletRequest request, Model model){
		
		HashMap<String, Object> param = new HashMap<String,Object>();
		String parameter = request.getParameter("proName");
		String providerId = request.getParameter("providerId");
		param.put("startIndex", (page-1)*rows);
		param.put("endIndex",rows);
		param.put("goodName",goodName);
		param.put("barCode",barCode);
		param.put("providerId",providerId);
		List<Map<String,Object>> associatedlist = providerService.findAssociatedCancelPage(param);
		int pageCount = providerService.getAssociatedCancelCount(param);
		double crslTotalPage = Math.ceil(Double.valueOf(pageCount/rows.doubleValue()));
		model.addAttribute("associatedList", associatedlist);
		model.addAttribute("proName", parameter);
		model.addAttribute("providerId", providerId);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", new Double(crslTotalPage).intValue());
		model.addAttribute("totalRecords", pageCount);
		return "after/chinamerchants/associatedcancel";
	}
	
	
	/**
	 * 添加关联
	 * @param goodsid
	 * @param providerId
	 * @return
	 */
	@RequestMapping("associatedAdd")
	@ResponseBody
	public Result associatedAdd(@RequestParam(value="goodsid")Long[] goodsid,
			@RequestParam(value="providerId")Long providerId){
		if(goodsid.length<=0){
			ResultUtils.returnError("请您选择商品!");
		}
		Result result = providerService.associatedAdd(goodsid, providerId);
		return result;
	}
	
	/**
	 * 取消关联
	 * @param goodsid
	 * @param providerId
	 * @return
	 */
	@RequestMapping("associatedCancel")
	@ResponseBody
	public Result associatedCancel(@RequestParam(value="goodsid")Long[] goodsid,
			@RequestParam(value="providerId")Long providerId){
		Result result = providerService.associatedCancel(goodsid,providerId);
		return result;
	}
	
	/**
	 * 修改厂商
	 * @param provider
	 * @return
	 */
	@RequestMapping("providerUpdate")
	@ResponseBody
	public Result providerUpdate(Provider provider){
		Result result = providerService.providerUpdate(provider);
		return result;
	}
	
	/**
	 * 绑定账号
	 * @param provider
	 * @return
	 */
	@RequestMapping("bindingAccount")
	@ResponseBody
	public Result bindingAccount(Provider provider){
		Result result = providerService.bindingAccount(provider);
		return result;
	}
}

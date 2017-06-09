package com.alqsoft.controller.admin.after.good;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.good.Good.goodSubmit;
import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.soldier.SoldierService;
import com.alqsoft.tag.core.util.StringUtil;
/**
 * 商品控制层
 * @author zj
 *
 */
@RequestMapping("admin/after/good")
@Controller
public class GoodController {

	@Autowired
	private GoodService goodService;
	@Autowired
	private SoldierService soldierService;
	
	/**
	 * 跳转招商首页
	 * @return
	 */
	@RequestMapping("toChinamerchantsMain.do")
	public String toChinamerchantsMain(){
		return "after/chinamerchants/common/chinamerchants-main";
	}
	
	/**
	 * 跳转添加商品页面
	 * @return
	 */
	@RequestMapping("toGoodAdd")
	public String toGoodAdd(){
		return "after/chinamerchants/good-add";
	}
	
	/**
	 * 跳转修改商品页面
	 * @return
	 */
	@RequestMapping("toGoodEdit")
	public String toGoodEdit(@RequestParam(value="goodId")Long goodId,Model model){
		Good good = goodService.get(goodId);
		model.addAttribute("goods",good);
		return "after/chinamerchants/good-edit";
	}
	
	/**
	 * 条码列表
	 * @param page
	 * @param rows
	 * @param account_type
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("toGoodPageList")
	public String toGoodPageList(
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
		String parameter = request.getParameter("pagetype");
		String urls = "";
		if("1".equals(parameter)){
			param.put("ptype",parameter);
			urls = "after/chinamerchants/good-list";
		}else{
			urls = "after/chinamerchants/barcodemanage";
		}
		List<Map<String,Object>> goodList = goodService.findGoodPage(param);
		int pageCount = goodService.getGoodCount(param);
		double crslTotalPage = Math.ceil(Double.valueOf(pageCount/rows.doubleValue()));
		model.addAttribute("goodList", goodList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", new Double(crslTotalPage).intValue());
		model.addAttribute("totalRecords", pageCount);
		return urls;
	}
	
	/**
	 * 清除条码
	 * @param goodId
	 * @return
	 */
	@RequestMapping("removeBarCode")
	@ResponseBody
	public Result removeBarCode(
			@RequestParam(value="goodId")Long goodId){
		Result result = goodService.removeBarCode(goodId);
		return result;
	}
	
	/**
	 * 绑定条码
	 * @param goodId
	 * @param barCode
	 * @return
	 */
	@RequestMapping("bindingBarCode")
	@ResponseBody
	public Result bindingBarCode(
			@RequestParam(value="goodId")Long goodId,
			@RequestParam(value="barCode")String barCode){
		Result result = goodService.bindingBarCode(goodId,barCode);
		return result;
	}
	
	/**
	 * 添加商品
	 * @param good
	 * @return
	 */
	@RequestMapping("goodAdd")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result goodAdd(
			@RequestParam(value = "token") String token,
			@Validated(value=goodSubmit.class)Good good,
			BindingResult bindingResult,
			HttpServletRequest request){
 		String details = good.getDetails().trim();
		if(StringUtil.equals(details,null)){
			return ResultUtils.returnError("商品详情不能为空!");
		}
		if(StringUtil.equals(details,"")){
			return ResultUtils.returnError("商品详情不能为空!");
		}
		String parameter = request.getParameter("attachmentAddress").trim();
		if(StringUtil.equals(parameter,null)){
			return ResultUtils.returnError("商品图片不能为空!");
		}
		if(StringUtil.equals(parameter,"")){
			return ResultUtils.returnError("商品图片不能为空!");
		}
		String regExp = "^\\d+(\\.\\d{1,2})?$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher goodmoney = pattern.matcher(good.getGoodMoney()+"");
		Matcher buyprice = pattern.matcher(good.getBuyPrice()+"");
		if (goodmoney.find() == false) {
			return ResultUtils.returnError("商品价格只可以为数字,允许包含小数点后两位");
		}
		if (buyprice.find() == false) {
			return ResultUtils.returnError("进货价只可以为数字,允许包含小数点后两位");
		}
		if(!bindingResult.hasErrors()){
			good.setGoodImgUrl(parameter);
			good.setStates(0);
			good.setTotalSaleNum(0);
			Result result = goodService.goodAdd(good);
			return result;
		}else{
			return ResultUtils.returnError(bindingResult);
		}
	}
	
	@RequestMapping("goodEdit")
	@AvoidDuplicateSubmission(needRemoveToken= true)
	@ResponseBody
	public Result goodEdit(@Validated(value=goodSubmit.class)Good good,
			@RequestParam(value = "token") String token,
			BindingResult bindingResult,HttpServletRequest request){
		String details = good.getDetails().trim();
		if(StringUtil.equals(details,null)){
			return ResultUtils.returnError("商品详情不能为空!");
		}
		if(StringUtil.equals(details,"")){
			return ResultUtils.returnError("商品详情不能为空!");
		}
		String parameter = request.getParameter("attachmentAddress").trim();
		if(StringUtil.equals(parameter,null)){
			return ResultUtils.returnError("商品图片不能为空!");
		}
		if(StringUtil.equals(parameter,"")){
			return ResultUtils.returnError("商品图片不能为空!");
		}
		String regExp = "^\\d+(\\.\\d{1,2})?$";
		Pattern pattern = Pattern.compile(regExp);
		Matcher goodmoney = pattern.matcher(good.getGoodMoney()+"");
		Matcher buyprice = pattern.matcher(good.getBuyPrice()+"");
		if (goodmoney.find() == false) {
			return ResultUtils.returnError("商品价格只可以为数字,允许包含小数点后两位");
		}
		if (buyprice.find() == false) {
			return ResultUtils.returnError("进货价只可以为数字,允许包含小数点后两位");
		}
		if(!bindingResult.hasErrors()){
			if(good.getGoodMoney()<=0){
				return ResultUtils.returnError("商品价格有误!");
			}
			Result result = goodService.goodEdit(good,request);
			return result;
		}else{
			return ResultUtils.returnError(bindingResult);
		}
	}
	
	/**
	 * 跳转ckeditor
	 * @return
	 */
	@RequestMapping("good-ckeditor")
	public String productCkeditor(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			@RequestParam(value="details",required=false)String details) {
		model.addAttribute("details",details);
		return "after/chinamerchants/good-ckeditor";
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("uploadGoodImage")
	@ResponseBody
	public Result uploadImage(HttpServletRequest request, MultipartFile file, Model model) {
		String[] extendFile = new String[] { ".jpg", ".png", ".jpeg", ".bmp", ".gif" };
		String module = "good";
		Result result = soldierService.uploadImg(file,
				new Object[] { goodService, "saveAttachment" }, module, extendFile, request);
		Attachment attachment = (Attachment) result.getContent();
		return result;
	}
	
	/**
	 * 商品删除、下架
	 * @param goodId
	 * @param executeType
	 * @return
	 */
	@RequestMapping("modifGood")
	@ResponseBody
	public Result modifGood(@RequestParam(value="goodId")Long goodId,
			@RequestParam(value="goodStates")Integer goodStates) {
		Result result = goodService.modifGood(goodId,goodStates);
		return result;
	}
	
}

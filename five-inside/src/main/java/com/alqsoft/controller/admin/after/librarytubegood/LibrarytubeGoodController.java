package com.alqsoft.controller.admin.after.librarytubegood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.good.GoodService;
/**
 * 
* @ClassName: LibrarytubeGoodController 
* @Description: 库管后台条形码管理控制层
* @author 腾卉 
* @date 2017年6月6日 上午11:02:20 
*
 */
@RequestMapping("admin/after/librarytube/good")
@Controller
public class LibrarytubeGoodController {
	@Autowired
	private GoodService goodService;
	
	/**
	 * 
	* @Title: toGoodPageList 
	* @Description: 跳转条形码列表 
	* @return String    返回类型 
	* @throws
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
		List<Map<String,Object>> goodList = goodService.findGoodPage(param);
		int pageCount = goodService.getGoodCount(param);
		double crslTotalPage = Math.ceil(Double.valueOf(pageCount/rows.doubleValue()));
		model.addAttribute("goodList", goodList);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", new Double(crslTotalPage).intValue());
		model.addAttribute("totalRecords", pageCount);
		return "after/librarytube/barcodemanage/barcodemanage";
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
}

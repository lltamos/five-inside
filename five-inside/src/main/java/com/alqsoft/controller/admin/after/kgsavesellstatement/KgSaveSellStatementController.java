package com.alqsoft.controller.admin.after.kgsavesellstatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alqsoft.service.savesellstatement.SaveSellStatementService;
/**
 * 
* @ClassName: KgSaveSellStatementController 
* @Description: 库管后台存销报表控制层 
* @author 腾卉 
* @date 2017年5月15日 上午11:22:45 
*
 */
@Controller
@RequestMapping("admin/after/libraryTube/savesellstatement")
public class KgSaveSellStatementController {
	@Autowired
	public SaveSellStatementService saveSellStatementService;
	
	/**
	 * 
	* @Title: findAll 
	* @Description: 存销报表列表
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping("findStatementAll")
	public String findAll(Model model, @RequestParam(value = "begin_time", required = false) String begin_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "good_name", required = false) String good_name,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, HttpServletRequest request,
			HttpServletResponse response) {
		if(begin_time != "" && begin_time != null){
			begin_time = begin_time+" 00:00:00";
		}
		if(end_time != "" && end_time != null){
			end_time = end_time+" 23:59:59";
		}
		Integer beforeTotalPage = 0;
		List<Map<String, Object>> kgSaveSellStatementList = saveSellStatementService.findAll(begin_time, end_time,
				good_name, page, rows);
		beforeTotalPage = saveSellStatementService.getCount(begin_time, end_time, good_name);
		double centerTotalPage = Math.ceil(beforeTotalPage / rows.doubleValue());// 向上取整
		Integer aftertTotalPage = (new Double(centerTotalPage)).intValue();
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", aftertTotalPage);
		model.addAttribute("totalRecords", beforeTotalPage);
		model.addAttribute("kgSaveSellStatementList", kgSaveSellStatementList);
		return "after/librarytube/savesellstatement/kgsavesellstatement";
	}
	/**
	 * 
	* @Title: deriveCxStatement 
	* @Description: 存销报表导出
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("deriveCxStatement")
	public void deriveCxStatement(Model model, @RequestParam(value = "begin_time", required = false) String begin_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "good_name", required = false) String good_name,
			HttpServletRequest request,
			HttpServletResponse response) {
		if(begin_time != "" && begin_time != null){
			begin_time = begin_time+" 00:00:00";
		}
		if(end_time != "" && end_time != null){
			end_time = end_time+" 23:59:59";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("begin_time", begin_time);
		param.put("end_time", end_time);
		param.put("good_name", good_name);
		saveSellStatementService.deriveCxStatement(request, response, param);
	}
	/**
	 * 
	* @Title: deriveKcStatement 
	* @Description: 库存报表导出
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("deriveKcStatement")
	public void deriveKcStatement(Model model,HttpServletRequest request,HttpServletResponse response ) {
		saveSellStatementService.deriveKcStatement(request, response);
	}
}

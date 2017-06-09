package com.alqsoft.controller.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.pay.weixin.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.service.rolet.RoletService;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月8日 下午4:32:23 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */

@Controller
@RequestMapping("route")
public class RouteController {
	private final String ZHAOSHANG = "zhaoshang";

	private final String CAIWU = "caiwu";

	private final String KUGUANG = "kuguan";

	private final String SHOUHOU = "shouhou";

	private final String CZY = "czy";

	@Resource
	private RoletService roletServive;
	@Resource
	private StandardPasswordEncoder standardPasswordEncoder;

	@RequestMapping("toindex")
	public String toIndex(HttpServletRequest request) {
		request.setAttribute("error", request.getParameter("error"));
		return "view/login";
	}

	/**
	 * 招商后台：账号：zhaoshang 财务后台：账号：caiwu 库管后台：账号：kuguan 结账员：czy001\czy002
	 * 
	 */
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public String login(Model m, @RequestParam(required = true) String name, @RequestParam(required = true) String pass,
			HttpSession session) {

		session.removeAttribute("cashier");
		// type 0表示新业态的消费员 1表示本地商城的消费员
		session.removeAttribute("type");
		session.removeAttribute("rolet");
		String n = name.trim();
		Rolet rolet = roletServive.getRoletByMobileAdPwd(n);
		session.setAttribute("rolet", rolet);
		Boolean falg = false;

		// 查看密码是否为standardPasswordEncoder加密，不是在匹配md5加密，匹配成功修改为standardPasswordEncoder加密后的密码
		if (rolet != null && !standardPasswordEncoder.matches(pass, rolet.getPwd() != null ? rolet.getPwd() : "")) {
			String passwordMd5 = MD5Util.MD5Encode(pass + "", "utf-8");
			if (passwordMd5.equals(rolet.getPwd() != null ? rolet.getPwd() : "")) {
				falg = true;
				try {

					String encodeCode = standardPasswordEncoder.encode(pass);
					rolet.setPwd(encodeCode);
					roletServive.updateStandardPassword(rolet);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			falg = true;
		}

		if (rolet != null && falg) {
			String mobile = rolet.getMobile();
			if (StringUtils.containsIgnoreCase(mobile, ZHAOSHANG)) {

				// 跳转招商
				return "redirect:/admin/after/good/toChinamerchantsMain.do";

			}

			if (StringUtils.containsIgnoreCase(mobile, CAIWU)) {

				// 跳转财务
				return "redirect:/admin/after/financial/savesellstatement/tofinancialhome";

			}

			if (StringUtils.containsIgnoreCase(mobile, SHOUHOU)) {

				// 跳转售后
				return "redirect:/admin/after/aftersales/salesreturn/toaftersaleshome";

			}

			if (StringUtils.containsIgnoreCase(mobile, KUGUANG)) {

				// 跳转kuguan
				return "redirect:/admin/after/inventory/toInventoryHome";

			}

			if (StringUtils.containsIgnoreCase(mobile, CZY)) {
				// 结账员
				return "redirect:/front/cashier/to-xytlogin";

			}

		}

		m.addAttribute("error", "帐号或者密码错误!");
		session.removeAttribute("rolet");
		return "redirect:toindex";
	}

	// 退出,重新登陆
	@RequestMapping("to-relogin")
	public String relogin(Model model, HttpSession session) {
		session.removeAttribute("rolet");
		session.removeAttribute("cashier");
		session.removeAttribute("type");
		return "view/login";
	}
}
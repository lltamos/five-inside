package com.alqsoft.utils;

import javax.servlet.http.HttpSession;

import com.alqsoft.entity.rolet.Rolet;

/**
 * 
 * @Description: 登录校验类
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月15日 下午6:36:38 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public class RoleSessionUtils {

	// 判断是否登录
	public static Boolean isLogin(HttpSession s) {

		Rolet attribute = (Rolet) s.getAttribute("rolet");

		if (attribute != null) {
			return true;
		} else {
			return false;
		}
	}
}

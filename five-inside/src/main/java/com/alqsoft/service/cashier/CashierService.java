package com.alqsoft.service.cashier;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;


/**
 * 收营员登陆
 * @Description: TODO
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午10:26:03
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */

public interface CashierService {
	public Result cashierLogin(String mobile,String newvalid,String type,HttpServletRequest request);
	public Map<String, Object>  getBalanceByUserId(String userId,String type);
}

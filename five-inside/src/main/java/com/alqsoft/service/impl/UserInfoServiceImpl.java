package com.alqsoft.service.impl;

import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.UserInfoDao;
import com.alqsoft.hibernatedao.user.UsersDao;
import com.alqsoft.service.user.UserInfoService;

/**
 * 
 * @Description: TODO
 * @author 辛帅勇
 * @e-mail 916903931@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年2月12日 上午10:10:18
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao udao;
	@Override
	public Result getOrderCenterByNumberMap(long id) {
		Map<String, Object> map=udao.getOrderCenterByNumberMap(id);
		if(null!=map&&map.size()>0)
		return ResultUtils.returnSuccess("success",map );
		else
		return ResultUtils.returnError("查询不到订单中心");
	}

}

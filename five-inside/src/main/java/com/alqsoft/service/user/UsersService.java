package com.alqsoft.service.user;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.user.Users;

/**
 * 
 * @Description: TODO
 * @author 时永青
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017年1月23日 下午2:19:50
 * Copyright © 2017 北京易商通公司 All rights reserved.
 * 
 */
public interface UsersService extends BaseService<Users>{
	/**
	 * @Description:根据用户id查询用户信息，并加行锁
	
	 * @author:时永青
	
	 * @time:2017年1月23日 下午3:48:54
	 */
	Users getUsersByIdRowLock(long id);

	/**
	 * @Description:修改用户的返现余额根据用户id
	
	 * @author:时永青
	
	 * @time:2017年1月23日 下午3:49:38
	 */
	void updateUserFanxAccountById(Double fanxAccount, long id);

	void updateUserBdtjrIdById(long BdtjrId, long id);

	Users findOne(long id);

	void updateUserGoodBlById(double goodbl, long id);
	/**
	 * @Description:修改用户的等级
	
	 * @author:时永青
	
	 * @time:2017年2月10日 上午9:47:04
	 */
	void updateUserGradeById(long grade, long id);

}

package com.alqsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.user.Users;
import com.alqsoft.hibernatedao.user.UsersDao;
import com.alqsoft.service.user.UsersService;

/**
 * 
 * @Description: TODO
 * @author 时永青
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017年1月23日 下午2:20:31
 * Copyright © 2017 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDao usersdao;
	
	/**
	 * @Description:根据用户id查询用户信息，并加行锁
	
	 * @author:时永青
	
	 * @time:2017年1月23日 下午3:48:54
	 */
	@Override
	public Users getUsersByIdRowLock(long id){
		return usersdao.getUsersByIdRowLock(id);
	}
	/**
	 * @Description:修改用户的返现余额根据用户id
	
	 * @author:时永青
	
	 * @time:2017年1月23日 下午3:49:38
	 */
	@Override
	@Transactional
	public void updateUserFanxAccountById(Double fanxAccount,long id){
		usersdao.updateUserFanxAccountById(fanxAccount, id);
	}
	/**
	 * @Description:修改订单的推荐人
	
	 * @author:时永青
	
	 * @time:2017年2月6日 下午2:54:47
	 */
	@Override
	@Transactional
	public void updateUserBdtjrIdById(long BdtjrId,long id){
		usersdao.updateUserBdtjrIdById(BdtjrId, id);
	}
	
	@Override
	public Users findOne(long id){
		return usersdao.findOne(id);
	}
	/**
	 * 根据商场余额更新用户
	 */
	@Override
	public void updateUserGoodBlById(double goodbl, long id) {
		usersdao.updateUserGoodBlById(goodbl,id);
		
	}
	/**
	 * @Description:修改用户的等级
	
	 * @author:时永青
	
	 * @time:2017年2月10日 上午9:46:44
	 */
	@Override
	@Transactional
	public void updateUserGradeById(long grade,long id){
		usersdao.updateUserGradeById(grade, id);
	}
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Users get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public Users saveAndModify(Users arg0) {
		return this.usersdao.save(arg0);
	}

}

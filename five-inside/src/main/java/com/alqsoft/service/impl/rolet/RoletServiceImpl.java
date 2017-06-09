package com.alqsoft.service.impl.rolet;

import java.util.List;
import java.util.Map;

import org.alqframework.easyui.EasyuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.resource.Resource;
import com.alqsoft.entity.role.Role;
import com.alqsoft.entity.rolet.Rolet;
import com.alqsoft.hibernatedao.good.GoodDao;
import com.alqsoft.hibernatedao.rolet.RoletDao;
import com.alqsoft.service.role.RoleService;
import com.alqsoft.service.rolet.RoletService;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午9:44:08 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional
public class RoletServiceImpl implements RoletService {
	@Autowired
	private RoletDao roletDao;

	@Override
	public Rolet getRoletByMobileAdPwd(String mobile) {
		return roletDao.getRoletByMobileAdPwd(mobile);
	}

	@Override
	public Rolet updateStandardPassword(Rolet r) {

		return roletDao.save(r);
	}

}

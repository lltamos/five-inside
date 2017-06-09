package com.alqsoft.hibernatedao.rolet;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.rolet.Rolet;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午9:28:10 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface RoletDao extends BaseDao<Rolet> {

	/**
	 * 根据用户密码查询登录角色
	 * 
	 * @return
	 */
	@Query("FROM Rolet where Mobile=:mobile")
	public Rolet getRoletByMobileAdPwd(@Param("mobile") String mobile);

}

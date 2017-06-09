package com.alqsoft.service.rolet;

import com.alqsoft.entity.rolet.Rolet;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午9:41:54 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface RoletService {
	public Rolet getRoletByMobileAdPwd(String mobile);

	public Rolet updateStandardPassword(Rolet r);
}

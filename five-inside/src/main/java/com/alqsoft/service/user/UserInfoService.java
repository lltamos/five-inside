package com.alqsoft.service.user;

import org.alqframework.result.Result;

/**
 * 
 * @Description: TODO
 * @author 时永青
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017年1月23日 上午10:12:50
 * Copyright © 2017 北京易商通公司 All rights reserved.
 * 
 */
public interface UserInfoService {

	/**
	 * @Description:根據訂單中心id查找訂單中心法人
	 * @author:时永青
	 * @time:2017年1月22日 下午3:42:57
	 */
	Result getOrderCenterByNumberMap(long id);
	
}

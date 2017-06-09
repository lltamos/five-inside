package com.alqsoft.service.storageapply;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.utils.ArgsBean;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月12日 上午11:35:43 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface StorageApplyService {

	public List<Map<String, Object>> findStorageApplys(ArgsBean bean);

	Result repeal(Long sid);

	Result allow(Long sid, HttpSession session);

	public Object exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page);

}

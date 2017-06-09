package com.alqsoft.service.impl.cashier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.init.InitParams;
import com.alqsoft.service.cashier.CashierService;
import com.alqsoft.utils.HttpRequestUtils;

@Service
@Transactional(readOnly=true)
public class CashierServiceImpl implements CashierService{
	@Autowired
	private InitParams initParams;
	@Override
	public Result cashierLogin(String mobile, String newvalid, String type, HttpServletRequest request) {
		Result result = new  Result();
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		if(Integer.parseInt(type)==1){
			String bdbd_server = initParams.getProperties().getProperty("bdbd_server");
			list.add(new BasicNameValuePair("servicename","UserLogin"));
			list.add(new BasicNameValuePair("mobile",mobile));
			list.add(new BasicNameValuePair("newvalid",newvalid));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(bdbd_server, list);
			if(null == jdMap || jdMap.size()<=0){
				result.setCode(1);
				result.setMsg("您登陆失败,请重新登陆!");
				return result ;
			}
			result.setCode(Integer.parseInt(jdMap.get("state")+""));
			result.setContent(jdMap.get("data"));
			result.setMsg(jdMap.get("error")+"");
			if(null != jdMap.get("data") && !StringUtils.endsWith("error", jdMap.get("data")+"")){
				request.getSession().setAttribute("cashier", jdMap.get("data"));
				request.getSession().setAttribute("type", 1);
			}
			return result ;
		}
		if(Integer.parseInt(type)==0){
			//TODO:需要跟换服务器地址
			String xyt_server = initParams.getProperties().getProperty("xyt_server");
			list.add(new BasicNameValuePair("servicename","UserLogin"));
			list.add(new BasicNameValuePair("mobile",mobile));
			list.add(new BasicNameValuePair("newvalid",newvalid));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(xyt_server, list);
			if(null == jdMap || jdMap.size()<=0){
				result.setCode(1);
				result.setMsg("您登陆失败,请重新登陆!");
				return result ;
			}
			result.setCode(Integer.parseInt(jdMap.get("state")+""));
			result.setContent(jdMap.get("data"));
			result.setMsg(jdMap.get("error")+"");
			if(null != jdMap.get("data") && !StringUtils.endsWith("error", jdMap.get("data")+"")){
				request.getSession().setAttribute("cashier", jdMap.get("data"));
				request.getSession().setAttribute("type", 0);
			}
			return result ;
		}
		result .setCode(1);
		result .setMsg("操作有误!");
		return result ;
	}

	@Override
	public Map<String, Object> getBalanceByUserId(String userId ,String type) {
		Map<String, Object> map = new HashMap<>();
		if(Integer.parseInt(type)==1){
			String bdbd_server = initParams.getProperties().getProperty("bdbd_server");
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("servicename","GetUserAcc"));
			list.add(new BasicNameValuePair("userid",userId));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(bdbd_server, list);
			return jdMap;
		}
		if(Integer.parseInt(type)==0){
			//TODO: 需要更换到新业态的服务器地址
			String xyt_server = initParams.getProperties().getProperty("xyt_server");
			List<NameValuePair> list=new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("servicename","GetUserAcc"));
			list.add(new BasicNameValuePair("userid",userId));
			Map<String, Object> jdMap = HttpRequestUtils.httpPost(xyt_server, list);
			return jdMap;
		}
		map.put("state", 1);
		return map;
	}



}

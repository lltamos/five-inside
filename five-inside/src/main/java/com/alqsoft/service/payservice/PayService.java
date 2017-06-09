package com.alqsoft.service.payservice;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;

import com.alqsoft.entity.rolet.Rolet;

public interface PayService {
	public Map<String, Object> goToPay(String userid ,String mobile,Double money,String orderno,String type);
	public Result AfterPay(String userid ,String mobile,Double money,String orderno,String type,List<Map<String, Object>> cartList,Rolet rolet);
}

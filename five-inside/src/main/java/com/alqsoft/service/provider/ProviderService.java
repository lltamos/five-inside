package com.alqsoft.service.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.provider.Provider;

public interface ProviderService extends BaseService<Provider>{

	/**
	 * 添加厂商
	 * @param provider
	 * @return
	 */
	Result providerAdd(Provider provider);

	/**
	 * 厂家分页列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> findProviderPage(HashMap<String, Object> param);
	/**
	 * 厂家列表总条数
	 * @param param
	 * @return
	 */
	int getProviderCount(HashMap<String, Object> param);

	/**
	 * 添加关联分页列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> findAssociatedPage(HashMap<String, Object> param);

	/**
	 * 添加关联列表总条数
	 * @param param
	 * @return
	 */
	int getAssociatedCount(HashMap<String, Object> param);

	/**
	 * 添加关联
	 * @param goodsid
	 * @param providerId
	 * @return
	 */
	Result associatedAdd(Long[] goodsid, Long providerId);

	/**
	 * 取消关联列表
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> findAssociatedCancelPage(HashMap<String, Object> param);

	/**
	 * 取消关联总条数
	 * @param param
	 * @return
	 */
	int getAssociatedCancelCount(HashMap<String, Object> param);
	
	/**
	 * 取消关联
	 * @param goodsid
	 * @param providerId
	 * @return
	 */
	Result associatedCancel(Long[] goodsid, Long providerId);

	/**
	 * 修改厂商
	 * @param provider
	 * @return
	 */
	Result providerUpdate(Provider provider);

	/**
	 * 绑定账号
	 * @param provider
	 * @return
	 */
	Result bindingAccount(Provider provider);

}

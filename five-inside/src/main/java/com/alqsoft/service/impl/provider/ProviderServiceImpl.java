package com.alqsoft.service.impl.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.provider.IProviderMapper;
import com.alqsoft.entity.provider.Provider;
import com.alqsoft.entity.relationship.RelationShip;
import com.alqsoft.hibernatedao.provider.ProviderDao;
import com.alqsoft.hibernatedao.relationship.RelationshipDao;
import com.alqsoft.service.provider.ProviderService;
@Service
@Transactional(readOnly=true)
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderDao providerDao;
	@Autowired
	private IProviderMapper providerMapper;
	@Autowired
	private RelationshipDao relationshipDao;
	
	@Override
	public boolean delete(Long arg0) {
		providerDao.delete(arg0);
		return true;
	}

	@Override
	public Provider get(Long arg0) {
		return providerDao.findOne(arg0);
	}

	@Override
	public Provider saveAndModify(Provider arg0) {
		return providerDao.save(arg0);
	}

	@Override
	@Transactional(readOnly=false)
	public Result providerAdd(Provider provider) {
		Provider dbprovider = providerDao.getProviderByName(provider.getName());
		if(dbprovider != null){
			return ResultUtils.returnError("该厂商已存在，禁止重复添加!");
		}
		providerDao.save(provider);
		return ResultUtils.returnSuccess("添加成功");
	}

	@Override
	public List<Map<String, Object>> findProviderPage(HashMap<String, Object> param) {
		return providerMapper.findProviderList(param);
	}

	@Override
	public int getProviderCount(HashMap<String, Object> param) {
		return providerMapper.getProviderCount(param);
	}

	@Override
	public List<Map<String, Object>> findAssociatedPage(HashMap<String, Object> param) {
		return providerMapper.findAssociatedPage(param);
	}

	@Override
	public int getAssociatedCount(HashMap<String, Object> param) {
		return providerMapper.getAssociatedCount(param);
	}

	@Override
	@Transactional(readOnly=false)
	public Result associatedAdd(Long[] goodsid, Long providerId) {
		for (Long goodId : goodsid) {
			RelationShip relationship = new RelationShip();
			relationship.setGoodID(goodId);
			relationship.setProviderID(providerId);
			relationshipDao.save(relationship);
		}
		return ResultUtils.returnSuccess("添加成功");
	}

	@Override
	public List<Map<String, Object>> findAssociatedCancelPage(HashMap<String, Object> param) {
		return providerMapper.findAssociatedCancelPage(param);
	}

	@Override
	public int getAssociatedCancelCount(HashMap<String, Object> param) {
		return providerMapper.getAssociatedCancelCount(param);
	}

	@Override
	@Transactional(readOnly=false)
	public Result associatedCancel(Long[] goodsid, Long providerId) {
		for (Long goodid : goodsid) {
			relationshipDao.deleteRelationshipByParam(goodid,providerId);
		}
		return ResultUtils.returnSuccess("取消成功");
	}

	@Override
	@Transactional(readOnly=false)
	public Result providerUpdate(Provider provider) {
		if(provider != null){
			Provider findOne = providerDao.findOne(provider.getId());
			findOne.setName(provider.getName());
			findOne.setContact(provider.getContact());
			findOne.setEmail(provider.getEmail());
			providerDao.save(findOne);
		}
		return ResultUtils.returnSuccess("修改成功");
	}

	@Override
	@Transactional(readOnly=false)
	public Result bindingAccount(Provider provider) {
		if(provider != null){
			String accountNum = provider.getAccountNum();
			if(accountNum.length() != 16 && accountNum.length() != 19){
				return ResultUtils.returnError("请输入正确收款账号");
			}
			Provider findOne = providerDao.findOne(provider.getId());
			findOne.setBankName(provider.getBankName());
			findOne.setAccountNum(provider.getAccountNum());
			findOne.setAccountOwner(provider.getAccountOwner());
			findOne.setMobile(provider.getMobile());
			findOne.setAccountAttribute(provider.getAccountAttribute());
			findOne.setAccountArea(provider.getAccountArea());
			findOne.setAccountCity(provider.getAccountCity());
			findOne.setBankNum(provider.getBankNum());
			findOne.setAccountType(provider.getAccountType());
			findOne.setBranchName(provider.getBranchName());
			findOne.setOrganization(provider.getOrganization());
			providerDao.save(findOne);
		}
		return ResultUtils.returnSuccess("绑定成功");
	}
	
	

}

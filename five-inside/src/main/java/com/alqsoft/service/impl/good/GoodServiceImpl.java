package com.alqsoft.service.impl.good;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.good.IGoodMapper;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.relationship.RelationShip;
import com.alqsoft.hibernatedao.good.GoodDao;
import com.alqsoft.hibernatedao.relationship.RelationshipDao;
import com.alqsoft.service.good.GoodService;
import com.alqsoft.service.inventory.InventoryService;
import com.alqsoft.tag.core.util.StringUtil;
@Service
@Transactional(readOnly=true)
public class GoodServiceImpl implements GoodService{

	@Autowired
	private GoodDao goodDao;
	@Autowired
	private IGoodMapper goodMapper;
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private RelationshipDao relationshipDao;
	
	@Override
	public boolean delete(Long arg0) {
		goodDao.delete(arg0);
		return true;
	}

	@Override
	@Transactional
	public Good get(Long arg0) {
		return goodDao.getGoodById(arg0);
	}

	@Override
	public Good saveAndModify(Good arg0) {
		return goodDao.save(arg0);
	}

	@Override
	public Good getGoodByBarCode(String barCode) {
		return goodDao.getGoodByBarCode(barCode);
	}

	@Override
	public List<Map<String,Object>> findGoodPage(HashMap<String, Object> param) {
		List<Map<String,Object>> goodList = goodMapper.findGoodList(param);
		return goodList;
	}

	@Override
	public int getGoodCount(HashMap<String, Object> param) {
		
		return goodMapper.getGoodCount(param);
	}

	@Override
	public Result getInventoryByGoodId(Long goodId) {
		Inventory inventory = null;
		try {
			inventory = inventoryService.getInventoryByGoodId(goodId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError("库存重复异常");
		}
		if(inventory == null){
			return ResultUtils.returnSuccess("获取成功",0);
		}else{
			return ResultUtils.returnSuccess("获取成功",inventory.getNum());
		}
	}

	@Override
	@Transactional(readOnly=false)
	public Result removeBarCode(Long goodId) {
		Good goodById = goodDao.getGoodById(goodId);
		if(goodById != null){
			goodById.setBarCode(null);
			goodDao.save(goodById);
		}
		return ResultUtils.returnSuccess("清除成功");
	}

	@Override
	@Transactional(readOnly=false)
	public Result bindingBarCode(Long goodId, String barCode) {
		try {
			String regExp = "^\\d{13}+$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(barCode);
			if (m.find() == false) {
				return ResultUtils.returnError("请输入13位纯数字的条码！");
			}
			Good goodt = goodDao.getGoodByBarCode(barCode);
			if(goodt !=null){
				if(goodt.getBarCode().equals(barCode)){
					return ResultUtils.returnError("输入条码重复，请重新输入");
				}
			}
			Good findOne = goodDao.findOne(goodId);
			findOne.setBarCode(barCode);
			goodDao.save(findOne);
			return ResultUtils.returnSuccess("绑定成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError("绑定失败");
		}
	}

	@Override
	@Transactional(readOnly=false)
	public Result goodAdd(Good good) {
		 if(good != null){
			 goodDao.save(good);
			 return ResultUtils.returnSuccess("保存成功");
		 }else{
			 return ResultUtils.returnError("商品不能为空");
		 }
	}

	@Override
	@Transactional(readOnly=false)
	public Result modifGood(Long goodId, Integer goodStates) {
		Good findOne = goodDao.findOne(goodId);
		if(findOne != null){
			findOne.setStates(goodStates);
			goodDao.save(findOne);
			RelationShip relationShip = relationshipDao.getRelationShipByGoodID(findOne.getId());
			if(null !=relationShip&&goodStates==3){
				relationshipDao.deleteRelationshipByParam(relationShip.getGoodID(), relationShip.getProviderID());
			}
			return ResultUtils.returnSuccess("操作成功");
		}else{
			return ResultUtils.returnError("商品不存在");
		}
	}

	@Override
	@Transactional(readOnly=false)
	public Result goodEdit(Good good,HttpServletRequest request) {
		Good findOne = goodDao.findOne(good.getId());
		if(findOne != null){
			String parameter = request.getParameter("attachmentAddress");
			if(!StringUtil.equals(parameter,null)||!StringUtil.equals(parameter,"")){
				findOne.setGoodImgUrl(parameter);
			}
			findOne.setGoodName(good.getGoodName());
			findOne.setGoodMoney(good.getGoodMoney());
			findOne.setBuyPrice(good.getBuyPrice());
			findOne.setKeyWord(good.getKeyWord());
			findOne.setGoodCate(good.getGoodCate());
			findOne.setDetails(good.getDetails());
			goodDao.save(findOne);
			return ResultUtils.returnSuccess("修改成功");
		}else{
			return ResultUtils.returnError("数据异常");
		}
	}

	@Override
	public Good getGoodByBarCodeBe(String bar_code) {
		return goodMapper.getGoodByBarCodeBe(bar_code);
	}

}

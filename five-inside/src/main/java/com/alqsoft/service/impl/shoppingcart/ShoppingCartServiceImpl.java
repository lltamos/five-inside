package com.alqsoft.service.impl.shoppingcart;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.shoppingcart.IShoppingCartMapper;
import com.alqsoft.entity.good.Good;
import com.alqsoft.entity.inventory.Inventory;
import com.alqsoft.entity.shoppingcart.ShoppingCart;
import com.alqsoft.hibernatedao.good.GoodDao;
import com.alqsoft.hibernatedao.inventory.InventoryDao;
import com.alqsoft.hibernatedao.shoppingcart.ShoppingCartDao;
import com.alqsoft.service.shoppingcart.ShoppingCartService;

@Service
@Transactional(readOnly=false)
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	IShoppingCartMapper iShoppingCartMapper;
	@Autowired
	ShoppingCartDao shoppingCartDao;
	@Autowired
	GoodDao goodDao;
	@Autowired
	InventoryDao inventoryDao;
	@Override
	public boolean delete(Long arg0) {
		shoppingCartDao.delete(arg0);
		return true;
	}
	@Override
	public ShoppingCart get(Long arg0) {
		return shoppingCartDao.findOne(arg0);
	}
	@Override
	@Transactional
	public ShoppingCart saveAndModify(ShoppingCart arg0) {
		return shoppingCartDao.save(arg0);
	}
	
	@Override
	public List<Map<String, Object>> getShoppingCartListByUserId(Long userid,Integer states) {
		//查询购物车
		Map<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("states", states);
		List<Map<String, Object>> shoppingList = iShoppingCartMapper.getCartListByUserId(map);
		//获取购物车中商品的详细信息
		if(shoppingList.size() > 0){
			for(Map<String, Object> shopping :shoppingList){
				String sgoodid=shopping.get("goodid")+"";
				Long goodId =Long.parseLong(sgoodid);
				Good goods = goodDao.getGoodById(goodId);
				Inventory inventory = inventoryDao.getInventoryByGoodsId(goodId);
				shopping.put("goods", goods);
				shopping.put("inventory", inventory);
			}
		}
		return shoppingList;
		
	}
	@Override
	@Transactional
	public Result delShoppingCart(Long id) {
		ShoppingCart shoppingCart = this.get(id);
		if(null != shoppingCart){
			shoppingCart.setStates(2);
			ShoppingCart save = this.saveAndModify(shoppingCart);
			if(null != save){
				return ResultUtils.returnSuccess("删除成功!");
			}
		}
		return ResultUtils.returnError("删除失败!");
	}
	@Override
	@Transactional
	public Result updShoppingCart(Long id, Integer num) {
		if(null == id || null == num ){
			ResultUtils.returnError("数量或商品不能为空!");
		}
		if(0 == num ){
			ResultUtils.returnError("数量不能为0!");
		}
		ShoppingCart one = this.get(id);
		if(null == one){
			ResultUtils.returnError("该订单不存在!");
		}
		if(0 != one.getStates()){
			ResultUtils.returnError("该订单已被删除或已支付!");
		}
		Inventory inventory = inventoryDao.getInventoryByGoodsId(one.getGoodID());
		if(null != inventory){
			if(inventory.getNum()+inventory.getMoreNum()<num){
				ResultUtils.returnError("亲,库存不足哦!");
			}
		}else{
			ResultUtils.returnError("库存不存在该商品!");
		}
		one.setGoodNum(num);
		ShoppingCart save = this.saveAndModify(one);
		if(null != save){
			return ResultUtils.returnSuccess("修改订单数量成功!");
		}
		return ResultUtils.returnError("操作失败!");
	}
	@Override
	@Transactional
	public Result addshoppingcart(String barCode,Long userid) {
		Good good = goodDao.getGoodByBarCode(barCode);
		if(null == good){
			return ResultUtils.returnError("亲,商品不存在!");
		}
		if(2 == good.getStates()){
			return ResultUtils.returnError("亲,商品已下架!");
		}
		if(3 == good.getStates()){
			return ResultUtils.returnError("亲,商品已删除!");
		}
		if(null != good && good.getStates() == 1){
			Long goodid = good.getId();
			Inventory inventory = inventoryDao.getInventoryByGoodsId(goodid);
			ShoppingCart shoppingCart = shoppingCartDao.getCartByUserIdStates(userid, 0, goodid);
			if(null != shoppingCart && shoppingCart.getStates()==0){
				if(null != inventory){
					Integer goodNum = shoppingCart.getGoodNum();
					Integer gnum = goodNum+1;
					if(gnum>inventory.getNum()+inventory.getMoreNum()){
						return ResultUtils.returnError("亲,库存不足哦!");
					}else{
						shoppingCart.setGoodNum(gnum);
						ShoppingCart save = this.saveAndModify(shoppingCart);
						if(null != save){
							Result result = new Result();
							result.setCode(2);//添加成功,在购物车的数量加1
							result.setContent(gnum);
							return result;
						}
					}
				}
			}
			if(null != inventory && inventory.getNum()+inventory.getMoreNum()>=1){
				ShoppingCart cart = new ShoppingCart();
				cart.setCreatedTime(new Date());
				cart.setGoodID(goodid);
				cart.setGoodNum(1);
				cart.setStates(0);
				cart.setUserID(userid);
				ShoppingCart save = this.saveAndModify(cart);
				if(null!=save){
					return ResultUtils.returnSuccess("添加购物车成功!");
				}
				return ResultUtils.returnError("亲,添加商品失败,请重试!");
			}else{
				return ResultUtils.returnError("亲,库存不足哦!");
			}
		}
		return ResultUtils.returnError("亲,添加商品失败,请重试!");
	}
	

}

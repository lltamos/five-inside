package com.alqsoft.service.shoppingcart;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.shoppingcart.ShoppingCart;



public interface ShoppingCartService extends BaseService<ShoppingCart>{
	public List<Map<String, Object>> getShoppingCartListByUserId(Long userid,Integer states);
	public Result delShoppingCart(Long id);
	public Result updShoppingCart(Long id,Integer num);
	public Result addshoppingcart(String barCode,Long userid);
}

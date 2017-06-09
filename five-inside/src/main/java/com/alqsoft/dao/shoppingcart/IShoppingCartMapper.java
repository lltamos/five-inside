package com.alqsoft.dao.shoppingcart;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface IShoppingCartMapper {
	public List<Map<String, Object>> getCartListByUserId(Map<String, Object> map);
}

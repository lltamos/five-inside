package com.alqsoft.hibernatedao.shoppingcart;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.shoppingcart.ShoppingCart;


public interface ShoppingCartDao extends BaseDao<ShoppingCart>{
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM ShoppingCart WHERE UserID=:UserID and States=:States and GoodID=:GoodID")
	public ShoppingCart getCartByUserIdStates(@Param("UserID") Long UserID,@Param("States") Integer States,@Param("GoodID") Long GoodID);
}

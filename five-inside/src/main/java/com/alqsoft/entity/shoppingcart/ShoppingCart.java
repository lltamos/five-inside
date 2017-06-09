package com.alqsoft.entity.shoppingcart;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 购物卡
 */
@Entity
@Table(name = "shoppingcartt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ShoppingCart extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 商品id
	private Long GoodID;
	// 商品数量
	private Integer GoodNum;
	// 0:新加1已下单2删除
	private Integer States;
	// 用户id
	private Long UserID;

	public Long getGoodID() {
		return GoodID;
	}

	public void setGoodID(Long goodID) {
		GoodID = goodID;
	}

	public Integer getGoodNum() {
		return GoodNum;
	}

	public void setGoodNum(Integer goodNum) {
		GoodNum = goodNum;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

}

package com.alqsoft.entity.sincementionshopcart;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表废弃
 */

@Entity
@Table(name = "sincementionshopcartt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SinceMentionShopCart extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Long GoodsId;
	private String BarCode;
	private String GoodName;
	private Double GoodMoney;
	private Integer GoodNum;
	private Long UserId;
	private Long OperatorId;
	private Integer ShopType;

	public Long getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(Long goodsId) {
		GoodsId = goodsId;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public String getGoodName() {
		return GoodName;
	}

	public void setGoodName(String goodName) {
		GoodName = goodName;
	}

	public Double getGoodMoney() {
		return GoodMoney;
	}

	public void setGoodMoney(Double goodMoney) {
		GoodMoney = goodMoney;
	}

	public Integer getGoodNum() {
		return GoodNum;
	}

	public void setGoodNum(Integer goodNum) {
		GoodNum = goodNum;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public Long getOperatorId() {
		return OperatorId;
	}

	public void setOperatorId(Long operatorId) {
		OperatorId = operatorId;
	}

	public Integer getShopType() {
		return ShopType;
	}

	public void setShopType(Integer shopType) {
		ShopType = shopType;
	}

}

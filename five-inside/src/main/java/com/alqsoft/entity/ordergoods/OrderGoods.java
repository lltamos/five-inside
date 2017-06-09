package com.alqsoft.entity.ordergoods;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 订单商品明细
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "ordergoodst")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderGoods extends IdEntity {

	private static final long serialVersionUID = 1L;

	// 商品名称
	private String GoodName;
	// 商品单价
	private Double GoodMoney;
	// 商品数量
	private Integer GoodNum;
	// 商品ID,关联GoodT表
	private Long GoodId;
	// 订单编号
	private String OrderNum;
	// 剩余购买数量（后期扣除用户退货数量后剩余的数量）
	private Integer RemainNum;
	// 购买类型（0：新业态； 1：本地电商）
	private Integer ShopType;
	// 扣除方式（0：扣除库存； 1：扣除只销不结）
	private Integer MinusType;

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

	public Long getGoodId() {
		return GoodId;
	}

	public void setGoodId(Long goodId) {
		GoodId = goodId;
	}

	public String getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	public Integer getRemainNum() {
		return RemainNum;
	}

	public void setRemainNum(Integer remainNum) {
		RemainNum = remainNum;
	}

	public Integer getShopType() {
		return ShopType;
	}

	public void setShopType(Integer shopType) {
		ShopType = shopType;
	}

	public Integer getMinusType() {
		return MinusType;
	}

	public void setMinusType(Integer minusType) {
		MinusType = minusType;
	}

}

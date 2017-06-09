package com.alqsoft.entity.goodsreturnrecord;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * GoodsReturnRecordT 商品退货表目前用于自提
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "goodsreturnrecordt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class GoodsReturnRecord extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 订单商品ID
	private Long OrderGoodsId;
	// 商品名称
	private String GoodName;
	// 商品价格
	private Double GoodMoney;
	// 商品数量
	private Integer GoodNum;
	// 商品ID
	private Long GoodId;
	// 订单号
	private String OrderNo;
	// 备注
	private String Remark;
	// 退货数量
	private Integer ReturnNum;
	// 购买类型  0新业态 1本地报单
	private Integer ShopType;

	public Long getOrderGoodsId() {
		return OrderGoodsId;
	}

	public void setOrderGoodsId(Long orderGoodsId) {
		OrderGoodsId = orderGoodsId;
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

	public Long getGoodId() {
		return GoodId;
	}

	public void setGoodId(Long goodId) {
		GoodId = goodId;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Integer getReturnNum() {
		return ReturnNum;
	}

	public void setReturnNum(Integer returnNum) {
		ReturnNum = returnNum;
	}

	public Integer getShopType() {
		return ShopType;
	}

	public void setShopType(Integer shopType) {
		ShopType = shopType;
	}

}

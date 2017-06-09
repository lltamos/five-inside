package com.alqsoft.entity.stockexchangerecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 通权转让记录表 
 */

@Entity
@Table(name = "stockexchangerecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StockExChangeRecond extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 转换通权订单号
	private String OrderNo;
	// 用户id
	private Long UserId;
	// 支付报单订单号
	private String PayOrderNo;
	// 金额
	private Double Money;
	// 0未关闭,1关闭
	private Integer IsClose;
	// 订单号
	private Long OrderId;
	private Integer SwapType;
	private Integer Flag;

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getPayOrderNo() {
		return PayOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		PayOrderNo = payOrderNo;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Integer getIsClose() {
		return IsClose;
	}

	public void setIsClose(Integer isClose) {
		IsClose = isClose;
	}

	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public Integer getSwapType() {
		return SwapType;
	}

	public void setSwapType(Integer swapType) {
		SwapType = swapType;
	}

	public Integer getFlag() {
		return Flag;
	}

	public void setFlag(Integer flag) {
		Flag = flag;
	}

}

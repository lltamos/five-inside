package com.alqsoft.entity.stockexchangerecondt;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表废弃  债转股表
 */

@Entity
@Table(name = "stockexchangerecondt_t")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StockExChangeRecondt extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String OrderNo;
	private Long UserId;
	private String PayOrderNo;
	private Double Money;
	private Integer IsClose;
	private Long OrderId;

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

}

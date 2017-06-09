package com.alqsoft.entity.orderreturnmoney;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderreturnmoneyt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderReturnMoney extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 订单号
	private String OrderNo;
	// 金额
	private Double Money;
	// 备注
	private String BeiZ;
	// 用户ID
	private Long UserID;

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public String getBeiZ() {
		return BeiZ;
	}

	public void setBeiZ(String beiZ) {
		BeiZ = beiZ;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

}

package com.alqsoft.entity.fmoneyrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fmoneyrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FMoneyRecond extends IdEntity {
	private static final long serialVersionUID = 1L;
	// 用户ID
	private Long UserID;
	// 单笔返现的金额
	private Double MdMoney;
	// 数量
	private Integer Num;
	// 总共的返现金额
	private Double ZMoney;
	// 订单ID
	private Long OrderID;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Double getMdMoney() {
		return MdMoney;
	}

	public void setMdMoney(Double mdMoney) {
		MdMoney = mdMoney;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Double getZMoney() {
		return ZMoney;
	}

	public void setZMoney(Double zMoney) {
		ZMoney = zMoney;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

}

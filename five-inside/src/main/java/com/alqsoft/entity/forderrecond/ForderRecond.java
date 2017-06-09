package com.alqsoft.entity.forderrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ForderRecondT 反润记录表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "forderrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ForderRecond extends IdEntity {
	private static final long serialVersionUID = 1L;

	// 订单ID
	private Long OrderID;
	// 订单数量
	private Integer Num;
	// 第几次返润
	private Integer Fnum;
	// 已返现金额
	private Double YfMoney;
	// 用户ID
	private Long UserID;

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Integer getFnum() {
		return Fnum;
	}

	public void setFnum(Integer fnum) {
		Fnum = fnum;
	}

	public Double getYfMoney() {
		return YfMoney;
	}

	public void setYfMoney(Double yfMoney) {
		YfMoney = yfMoney;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

}

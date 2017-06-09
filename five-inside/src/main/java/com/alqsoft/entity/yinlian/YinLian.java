package com.alqsoft.entity.yinlian;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 表废弃
 * 银联支付表
 */
@Entity
@Table(name = "yinliant")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class YinLian extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 订单数量
	private String OrderNumber;
	// 是否关闭
	private Integer IsClose;
	// 金额
	private Double Money;

	private Integer ApplayStates;
	private Integer ApplayType;
	private String ApplayName;
	// 用户id
	private Long UserID;
	private String SpOrdernum;
	private String ChaXunHaoQn;
	// 支付时间
	private Date PayTime;

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public Integer getIsClose() {
		return IsClose;
	}

	public void setIsClose(Integer isClose) {
		IsClose = isClose;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Integer getApplayStates() {
		return ApplayStates;
	}

	public void setApplayStates(Integer applayStates) {
		ApplayStates = applayStates;
	}

	public Integer getApplayType() {
		return ApplayType;
	}

	public void setApplayType(Integer applayType) {
		ApplayType = applayType;
	}

	public String getApplayName() {
		return ApplayName;
	}

	public void setApplayName(String applayName) {
		ApplayName = applayName;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getSpOrdernum() {
		return SpOrdernum;
	}

	public void setSpOrdernum(String spOrdernum) {
		SpOrdernum = spOrdernum;
	}

	public String getChaXunHaoQn() {
		return ChaXunHaoQn;
	}

	public void setChaXunHaoQn(String chaXunHaoQn) {
		ChaXunHaoQn = chaXunHaoQn;
	}

	public Date getPayTime() {
		return PayTime;
	}

	public void setPayTime(Date payTime) {
		PayTime = payTime;
	}

}

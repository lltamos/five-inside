package com.alqsoft.entity.takeoutcheck;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 确认收货表
 */
@Entity
@Table(name = "takeoutcheckt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TakeOutCheck extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 订单编号
	private String OrderNum;
	// 错误提示
	private String ErrorCode;
	// 0:成功1:失败
	private Integer States;

	public String getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}

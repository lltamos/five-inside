package com.alqsoft.entity.errmoneyinfo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 错误金额信息表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "err_money_infot")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ErrMoneyInfo extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 订单号
	private String OrderNO;
	// 用户ID
	private Long UserID;
	// 变动金额
	private Double ChangeMoney;
	// 变动前金额
	private Double BeforeChangeMoney;
	// 变动后金额
	private Double AfterChangeMoney;
	// 错误信息
	private String ErrInfo;
	// 操作金额类型
	private Integer CateId;

	public Integer getCateId() {
		return CateId;
	}

	public void setCateId(Integer cateId) {
		CateId = cateId;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Double getChangeMoney() {
		return ChangeMoney;
	}

	public void setChangeMoney(Double changeMoney) {
		ChangeMoney = changeMoney;
	}

	public Double getBeforeChangeMoney() {
		return BeforeChangeMoney;
	}

	public void setBeforeChangeMoney(Double beforeChangeMoney) {
		BeforeChangeMoney = beforeChangeMoney;
	}

	public Double getAfterChangeMoney() {
		return AfterChangeMoney;
	}

	public void setAfterChangeMoney(Double afterChangeMoney) {
		AfterChangeMoney = afterChangeMoney;
	}

	public String getErrInfo() {
		return ErrInfo;
	}

	public void setErrInfo(String errInfo) {
		ErrInfo = errInfo;
	}

}

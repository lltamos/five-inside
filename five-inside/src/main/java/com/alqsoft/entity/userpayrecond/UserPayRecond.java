package com.alqsoft.entity.userpayrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 用户支付记录表
 */
@Entity
@Table(name = "userpayrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UserPayRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户id
	private Long UserID;
	// 支付金额
	private Double Money;
	// 支付类型
	private Long CateID;
	// 0:个人账户   1：订单中心账户  跟变动表 3订单中心余额   2:购物账户（商城账户） 跟变动表的 2 商城余额表变动有关      3:返现账户 跟变动表 1返现余额变动有关
	private Integer AccountType;
	// 订单号
	private String OrderNo;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Long getCateID() {
		return CateID;
	}

	public void setCateID(Long cateID) {
		CateID = cateID;
	}

	public Integer getAccountType() {
		return AccountType;
	}

	public void setAccountType(Integer accountType) {
		AccountType = accountType;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

}

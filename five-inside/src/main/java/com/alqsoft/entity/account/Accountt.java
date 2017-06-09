package com.alqsoft.entity.account;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * AccountHzT 互转记录表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "accounthzt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Accountt extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户ID
	private Long UserID;
	// 金额
	private BigDecimal Money;
	// 状态
	private Long States;
	// 0:账户余额转向提现余额1:提现余额转向账户余额2:通权现金余额转入返现余额
	private Integer Type;
	// 第三方支付交易号 只有通权转返现有
	private String TOrderNo;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Long getStates() {
		return States;
	}

	public void setStates(Long states) {
		States = states;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public String getTOrderNo() {
		return TOrderNo;
	}

	public void setTOrderNo(String tOrderNo) {
		TOrderNo = tOrderNo;
	}

	public BigDecimal getMoney() {
		return Money;
	}

	public void setMoney(BigDecimal money) {
		Money = money;
	}

}

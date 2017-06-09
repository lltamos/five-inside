package com.alqsoft.entity.account;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO 账户转让 表 废弃
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "accounthzt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class BDAccount extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long UserID;
	private BigDecimal Money;
	private Long States;
	private Integer Type;
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

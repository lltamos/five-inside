package com.alqsoft.entity.baddata;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO 不存在
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "baddata")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class BadData extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Mobile;
	private BigDecimal incomeTotal;
	private BigDecimal paymenTotal;
	private BigDecimal artificialReviseTotal;
	private BigDecimal shopBalance;
	private BigDecimal balanceDifference;
	private BigDecimal actualShopBalance;
	private String reason;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}

	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	public BigDecimal getPaymenTotal() {
		return paymenTotal;
	}

	public void setPaymenTotal(BigDecimal paymenTotal) {
		this.paymenTotal = paymenTotal;
	}

	public BigDecimal getArtificialReviseTotal() {
		return artificialReviseTotal;
	}

	public void setArtificialReviseTotal(BigDecimal artificialReviseTotal) {
		this.artificialReviseTotal = artificialReviseTotal;
	}

	public BigDecimal getShopBalance() {
		return shopBalance;
	}

	public void setShopBalance(BigDecimal shopBalance) {
		this.shopBalance = shopBalance;
	}

	public BigDecimal getBalanceDifference() {
		return balanceDifference;
	}

	public void setBalanceDifference(BigDecimal balanceDifference) {
		this.balanceDifference = balanceDifference;
	}

	public BigDecimal getActualShopBalance() {
		return actualShopBalance;
	}

	public void setActualShopBalance(BigDecimal actualShopBalance) {
		this.actualShopBalance = actualShopBalance;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}

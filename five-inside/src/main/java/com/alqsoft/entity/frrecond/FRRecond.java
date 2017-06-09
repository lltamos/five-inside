package com.alqsoft.entity.frrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "frrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FRRecond extends IdEntity {
	private static final long serialVersionUID = 1L;
	// 时间
	private String STime;
	// 报单金额
	private Double Money;
	// 报单数量
	private Integer BdNum;

	public String getSTime() {
		return STime;
	}

	public void setSTime(String sTime) {
		STime = sTime;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Integer getBdNum() {
		return BdNum;
	}

	public void setBdNum(Integer bdNum) {
		BdNum = bdNum;
	}

}

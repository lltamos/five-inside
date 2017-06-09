package com.alqsoft.entity.testtt;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 表废弃
 */
@Entity
@Table(name = "testttt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Testtt extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 原订单号
	private String oldorderno;
	// 新订单号
	private String neworderno;
	// 金额
	private Double money;

	public String getOldorderno() {
		return oldorderno;
	}

	public void setOldorderno(String oldorderno) {
		this.oldorderno = oldorderno;
	}

	public String getNeworderno() {
		return neworderno;
	}

	public void setNeworderno(String neworderno) {
		this.neworderno = neworderno;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}

package com.alqsoft.entity.storageapply;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 表废弃
 */
@Entity
@Table(name = "storageapply")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StorageApply extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long GoodId;
	private String Goodname;
	private Integer Num;
	private Double GoodMoney;
	// 状态（0：未审批； 1：已审批；2已撤销 ）
	private Integer States;

	public Long getGoodId() {
		return GoodId;
	}

	public void setGoodId(Long goodId) {
		GoodId = goodId;
	}

	public String getGoodname() {
		return Goodname;
	}

	public void setGoodname(String goodname) {
		Goodname = goodname;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Double getGoodMoney() {
		return GoodMoney;
	}

	public void setGoodMoney(Double goodMoney) {
		GoodMoney = goodMoney;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
